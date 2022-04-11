package org.remis.cyclonedx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class LicenseCheckerTest {
  private static final URL JSON_FILE_URL =
      LicenseCheckerTest.class.getResource("/complex-project/allowedLicenses.json");
  private static final String JSON_PATH = "$[?(@.License_Conflicts=='No')].License_SPDX";

  private final LicenseChecker licenseChecker = new LicenseChecker();

  // TODO: test parseBom
  // TODO: test checkIgnoredDependencies
  // TODO: test checkLicenses

  @Test
  void parseJsonFromLocalFile() throws IOException {
    // GIVEN
    String jsonFileUri = JSON_FILE_URL.getFile();
    List<String> expected = Arrays.asList("Apache-2.0", "BSD-4-Clause");
    // WHEN
    List<String> actual = licenseChecker.parseJson(jsonFileUri, JSON_PATH, null);
    // THEN
    assertEquals(expected, actual);
  }

  @Test
  void parseJsonFromURL() throws IOException {
    // GIVEN
    String jsonFileUri = JSON_FILE_URL.toString();
    File destFile = new File("target/licensechecker.json");
    List<String> expected = Arrays.asList("Apache-2.0", "BSD-4-Clause");
    // WHEN
    List<String> actual = licenseChecker.parseJson(jsonFileUri, JSON_PATH, destFile);
    // THEN
    assertEquals(expected, actual);
  }

  @Test
  void parseJsonThrowsIOException() {
    // GIVEN
    String path = "invalid-path";
    // WHEN + THEN
    Exception e = assertThrows(IOException.class, () -> licenseChecker.parseJson(path, null, null));
    assertTrue(e.getMessage().startsWith(path));
  }

  @Test
  void lowercaseAndRemoveWhitespacesFromList() {
    // GIVEN
    List<String> input = Arrays.asList("", " ", "MIT", " MIT ", " Apache-2.0 ");
    Set<String> expected = new HashSet<>(Arrays.asList("apache-2.0", "mit"));
    // WHEN
    Set<String> actual = licenseChecker.lowercaseList(input);
    // THEN
    assertEquals(expected, actual);
  }
}
