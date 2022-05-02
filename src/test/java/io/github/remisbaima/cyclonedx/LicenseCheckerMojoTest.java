package io.github.remisbaima.cyclonedx;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.takari.maven.testing.TestResources;
import io.takari.maven.testing.executor.MavenExecutionResult;
import io.takari.maven.testing.executor.MavenRuntime;
import io.takari.maven.testing.executor.MavenRuntime.MavenRuntimeBuilder;
import io.takari.maven.testing.executor.MavenVersions;
import io.takari.maven.testing.executor.junit.MavenJUnitTestRunner;

@RunWith(MavenJUnitTestRunner.class)
@MavenVersions({"3.6.3", "3.8.5"})
public class LicenseCheckerMojoTest {

  @Rule
  public final TestResources resources =
      new TestResources("target/test-classes", "target/test-classes");

  public final MavenRuntime maven;

  public LicenseCheckerMojoTest(MavenRuntimeBuilder runtimeBuilder) throws Exception {
    ResourceBundle props = ResourceBundle.getBundle("test");
    String projectVersion = props.getString("project.version");
    this.maven =
        runtimeBuilder.withCliOptions("-B", "-e", "-Dtest.input.version=" + projectVersion).build();
  }

  @Test
  public void testBasic() throws IOException, Exception {
    checkHappyPath("basic-project");
  }

  @Test
  public void testComplex() throws IOException, Exception {
    checkHappyPath("complex-project").assertLogText("apache license 2.0");
  }

  @Test
  public void testEmptyConfig() throws IOException, Exception {
    File baseDir = resources.getBasedir("empty-config-project");
    maven
        .forProject(baseDir)
        .execute("clean", "package")
        .assertLogText("[ERROR] ")
        .assertLogText(LicenseCheckerMojo.MSG_ERROR_MINIMUM_CONFIG);
  }

  @Test
  public void testError() throws IOException, Exception {
    File baseDir = resources.getBasedir("error-project");
    maven
        .forProject(baseDir)
        .execute("clean", "package")
        .assertLogText("[ERROR] ")
        .assertLogText("org.codehaus.woodstox:stax2-api:4.2.1")
        .assertLogText("org.openapitools:jackson-databind-nullable:0.2.2")
        .assertLogText("- ID: null")
        .assertLogText("- URL: null")
        .assertLogText("- Name: null");
  }

  private MavenExecutionResult checkHappyPath(String projectName) throws IOException, Exception {
    File baseDir = resources.getBasedir(projectName);
    return maven
        .forProject(baseDir)
        .execute("clean", "package")
        .assertLogText(LicenseCheckerMojo.MSG_ALLOWED_LICENSES)
        .assertLogText("[WARNING] " + LicenseCheckerMojo.MSG_SKIPING_DEPENDENCY)
        .assertLogText(LicenseCheckerMojo.MSG_SUCCESS)
        .assertErrorFreeLog();
  }
}
