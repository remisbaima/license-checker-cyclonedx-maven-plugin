package org.remis.cyclonedx;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.cyclonedx.exception.ParseException;
import org.cyclonedx.model.Bom;

/** Goal which checks CycloneDX BOM licenses used by dependencies */
@Mojo(name = "check", defaultPhase = LifecyclePhase.PACKAGE)
public class LicenseCheckerMojo extends AbstractMojo {
  @Parameter(property = "ignoredDependencies")
  private String[] ignoredDependencies;

  @Parameter(property = "allowedLicenses")
  private String[] allowedLicenses;

  @Parameter(property = "allowedLicensesJson")
  private String allowedLicensesJson;

  @Parameter(property = "allowedLicensesJsonPath")
  private String allowedLicensesJsonPath;

  @Parameter(property = "project", readonly = true, required = true)
  private MavenProject project;

  @Parameter(property = "tempFile", defaultValue = "${project.build.directory}/licensechecker.json")
  private File tempFile;

  private static final String CYCLONEDX_PLUGIN = "org.cyclonedx:cyclonedx-maven-plugin";

  protected static final String MSG_ERROR_MINIMUM_CONFIG =
      "Invalid configuration: at least <allowedLicensesJson> or <allowedLicenses> must be set";
  protected static final String MSG_ERROR_INVALID_JSON_CONFIG =
      "Invalid configuration: if <allowedLicensesJson> is set, <allowedLicensesJsonPath> must also be set";
  protected static final String MSG_ALLOWED_LICENSES = "List of allowed licenses: ";
  protected static final String MSG_ERROR_NOT_ALLOWED = "Not allowed license <%s> used by <%s>";
  protected static final String MSG_SUCCESS = "Success: all used licenses are allowed";
  protected static final String MSG_SKIPING_DEPENDENCY = "Skipping license check for dependency: ";

  @Override
  public void execute() throws MojoExecutionException {
    // validate configuration
    if (StringUtils.isBlank(allowedLicensesJson) && StringUtils.isAllBlank(allowedLicenses)) {
      throw new MojoExecutionException(MSG_ERROR_MINIMUM_CONFIG);
    }
    if (StringUtils.isNotBlank(allowedLicensesJson)
        && StringUtils.isBlank(allowedLicensesJsonPath)) {
      throw new MojoExecutionException(MSG_ERROR_INVALID_JSON_CONFIG);
    }

    // initialisations
    LicenseChecker licenseChecker = new LicenseChecker();
    Bom bom;
    Set<String> ignoredDependenciesSet, allowedLicensesSet;
    try {
      Xpp3Dom cycloneDxConfigDom = (Xpp3Dom) project.getPlugin(CYCLONEDX_PLUGIN).getConfiguration();
      Map<String, String> cycloneDxConfig = new HashMap<>();
      if (cycloneDxConfigDom != null) {
        cycloneDxConfig =
            Arrays.asList(cycloneDxConfigDom.getChildren()).stream()
                .collect(Collectors.toMap(Xpp3Dom::getName, Xpp3Dom::getValue));
      }
      bom = licenseChecker.parseBom(cycloneDxConfig, project.getBuild().getDirectory());
      ignoredDependenciesSet = licenseChecker.lowercaseList(Arrays.asList(ignoredDependencies));
      allowedLicensesSet = licenseChecker.lowercaseList(Arrays.asList(allowedLicenses));
      if (StringUtils.isNotBlank(allowedLicensesJson)) {
        allowedLicensesSet.addAll(
            licenseChecker.lowercaseList(
                licenseChecker.parseJson(allowedLicensesJson, allowedLicensesJsonPath, tempFile)));
      }
    } catch (IOException | ParseException e) {
      throw new MojoExecutionException(e);
    }

    // check licences
    Map<String, String> nonCompliantDependenciesMap =
        licenseChecker.checkLicenses(bom, allowedLicensesSet);

    // check dependencies to ignore
    licenseChecker.checkIgnoredDependencies(ignoredDependenciesSet, nonCompliantDependenciesMap);

    // print results
    getLog().info(MSG_ALLOWED_LICENSES + allowedLicensesSet);
    ignoredDependenciesSet.stream().forEach(e -> getLog().warn(MSG_SKIPING_DEPENDENCY + e));
    if (nonCompliantDependenciesMap.isEmpty()) {
      getLog().info(MSG_SUCCESS);
    } else {
      for (Entry<String, String> e : nonCompliantDependenciesMap.entrySet()) {
        getLog().error(String.format(MSG_ERROR_NOT_ALLOWED, e.getValue(), e.getKey()));
      }
      throw new MojoExecutionException("");
    }
  }
}
