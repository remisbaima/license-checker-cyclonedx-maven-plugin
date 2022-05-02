package io.github.remisbaima.cyclonedx;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.cyclonedx.exception.ParseException;
import org.cyclonedx.model.Bom;
import org.cyclonedx.model.Component;
import org.cyclonedx.model.License;
import org.cyclonedx.model.LicenseChoice;
import org.cyclonedx.parsers.JsonParser;
import org.cyclonedx.parsers.Parser;
import org.cyclonedx.parsers.XmlParser;

/**
 * Class with the business logic to perform the license check.
 *
 * <p>No Maven dependencies are used, so this class can be reused in e.g. a Gradle plugin.
 */
public class LicenseChecker {
  private static final String CYCLONEDX_OUTPUT_NAME = "outputName";
  private static final String CYCLONEDX_OUTPUT_FORMAT = "outputFormat";
  private static final String CYCLONEDX_BOM = "bom";
  private static final String CYCLONEDX_JSON = "json";
  private static final String CYCLONEDX_XML = "xml";

  private static final int TIMEOUT_MILLIS = 10000; // 10sec

  protected Map<String, License> checkBom(Bom bom, Set<String> allowedLicenses) {
    Map<String, License> nonCompliantDependencies = new HashMap<>();
    for (Component component : bom.getComponents()) {
      String dependencyId = getDependencyId(component);
      LicenseChoice licenseChoice = component.getLicenseChoice();
      if (licenseChoice == null || licenseChoice.getLicenses() == null) {
        nonCompliantDependencies.put(dependencyId, null);
        continue;
      }

      List<License> licenses = licenseChoice.getLicenses();
      for (License license : licenses) {
        String id = StringUtils.lowerCase(license.getId());
        String url = StringUtils.lowerCase(license.getUrl());
        String name = StringUtils.lowerCase(license.getName());
        Set<String> itemsToCheck = new HashSet<>(Arrays.asList(id, url, name));

        // check if license ID, URL and name are NOT present in allowedLicenses
        if (Collections.disjoint(allowedLicenses, itemsToCheck)) {
          nonCompliantDependencies.put(dependencyId, license);
        }
      }
    }
    return nonCompliantDependencies;
  }

  /**
   * Both inputs can be modified.
   *
   * @param ignoredDependencies modifies this set so that its value is the intersection of this set
   *     and the map.keySet()
   * @param nonCompliantDependenciesKeys modifies this map so that its value is the asymmetric set
   *     difference of this map.keySet() and the ignoredDependencies set
   */
  protected void checkIgnoredDependencies(
      Set<String> ignoredDependencies, Set<String> nonCompliantDependenciesKeys) {
    // keep only ignoredDependencies that exist in BOM
    ignoredDependencies.retainAll(nonCompliantDependenciesKeys);
    // remove from nonCompliantDependencies all ignoredDependencies
    nonCompliantDependenciesKeys.removeAll(ignoredDependencies);
  }

  protected Bom parseBom(Map<String, String> cycloneDxConfig, String bomDir) throws ParseException {
    String bomFormat = cycloneDxConfig.getOrDefault(CYCLONEDX_OUTPUT_FORMAT, CYCLONEDX_JSON);
    String bomFileExtension;
    Parser bomParser;
    if (bomFormat.equalsIgnoreCase(CYCLONEDX_XML)) {
      bomFileExtension = CYCLONEDX_XML;
      bomParser = new XmlParser();
    } else {
      bomFileExtension = CYCLONEDX_JSON;
      bomParser = new JsonParser();
    }

    String bomFilename = cycloneDxConfig.getOrDefault(CYCLONEDX_OUTPUT_NAME, CYCLONEDX_BOM);
    File bomFile = new File(bomDir, String.join(".", bomFilename, bomFileExtension));
    return bomParser.parse(bomFile);
  }

  protected List<String> parseJson(String jsonFileUri, String jsonPath, File destFile)
      throws IOException {
    try {
      File file;
      if (isUrl(jsonFileUri)) {
        file = destFile;
        FileUtils.copyURLToFile(new URL(jsonFileUri), file, TIMEOUT_MILLIS, TIMEOUT_MILLIS);
      } else {
        file = new File(jsonFileUri);
      }

      DocumentContext json = JsonPath.parse(file);
      List<String> allowedList = new ArrayList<>();
      for (String part : StringUtils.split(jsonPath, ";")) {
        allowedList.addAll(json.read(StringUtils.trim(part)));
      }
      return allowedList;
    } catch (Exception e) {
      throw new IOException(e);
    }
  }

  protected Set<String> lowercaseList(List<String> list) {
    return list.parallelStream()
        .filter(StringUtils::isNotBlank)
        .map(e -> e.trim().toLowerCase())
        .collect(Collectors.toSet());
  }

  private String getDependencyId(Component component) {
    return String.join(":", component.getGroup(), component.getName(), component.getVersion());
  }

  private boolean isUrl(String uri) {
    try {
      new URL(uri).toURI();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
