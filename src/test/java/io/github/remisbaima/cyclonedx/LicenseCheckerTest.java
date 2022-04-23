package io.github.remisbaima.cyclonedx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import io.github.remisbaima.cyclonedx.LicenseChecker;

class LicenseCheckerTest {
  private static final URL JSON_FILE_URL =
      LicenseCheckerTest.class.getResource("/complex-project/allowedLicenses.json");
  private static final String JSON_PATH = "$[?(@.License_Conflicts=='No')].License_SPDX";
  private static final File TEMP_FILE = new File("target/licensechecker.json");

  private final LicenseChecker licenseChecker = new LicenseChecker();

  @Test
  void lowercaseAndRemoveWhitespacesFromList() {
    // GIVEN
    List<String> input = Arrays.asList("", " ", "MIT", " MIT ", " Apache-2.0 ");
    Set<String> expected = asSet("apache-2.0", "mit");
    // WHEN
    Set<String> actual = licenseChecker.lowercaseList(input);
    // THEN
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({"'',''", "' ',' '", "invalid-path,''", "http://abc.com,''", "' '," + JSON_PATH})
  void parseJsonThrowsIOException(String jsonFileUri, String jsonPath) {
    // WHEN + THEN
    assertThrows(IOException.class, () -> licenseChecker.parseJson(jsonFileUri, jsonPath, null));
  }

  @ParameterizedTest
  @MethodSource
  void parseJson(String jsonFileUri, String jsonPath, File destFile, List<String> expected)
      throws IOException {
    // WHEN
    List<String> actual = licenseChecker.parseJson(jsonFileUri, JSON_PATH, destFile);
    // THEN
    assertEquals(expected, actual);
  }

  static Stream<Arguments> parseJson() {
    List<String> expected = Arrays.asList("Apache-2.0", "BSD-4-Clause");
    return Stream.of(
        // json from local file
        arguments(JSON_FILE_URL.getFile(), JSON_PATH, TEMP_FILE, expected),
        // json from URL
        arguments(JSON_FILE_URL.toString(), JSON_PATH, TEMP_FILE, expected));
  }

  @ParameterizedTest
  @MethodSource
  void checkIgnoredDependencies(
      Set<String> ignoredDependencies,
      Set<String> nonCompliantDependencies,
      Set<String> ignoredDependenciesExpected,
      Set<String> nonCompliantDependenciesExpected) {
    // WHEN
    licenseChecker.checkIgnoredDependencies(ignoredDependencies, nonCompliantDependencies);
    // THEN
    assertEquals(
        ignoredDependenciesExpected, ignoredDependencies, "ignoredDependencies check failed");
    assertEquals(
        nonCompliantDependenciesExpected,
        nonCompliantDependencies,
        "nonCompliantDependencies check failed");
  }

  static Stream<Arguments> checkIgnoredDependencies() {
    return Stream.of(
        // empty ignoredDependencies
        arguments(asSet(), asSet("a:a:1"), asSet(), asSet("a:a:1")),
        // empty nonCompliantDependencies
        arguments(asSet("a:a:1"), asSet(), asSet(), asSet()),
        // empty ignoredDependencies and nonCompliantDependencies
        arguments(asSet(), asSet(), asSet(), asSet()),
        // ignoredDependencies > nonCompliantDependencies
        arguments(asSet("a:a:1", "b:b:2"), asSet("a:a:1"), asSet("a:a:1"), asSet()),
        // ignoredDependencies < nonCompliantDependencies
        arguments(asSet("a:a:1"), asSet("a:a:1", "b:b:2"), asSet("a:a:1"), asSet("b:b:2")),
        // ignoredDependencies != nonCompliantDependencies
        arguments(asSet("a:a:1"), asSet("b:b:2"), asSet(), asSet("b:b:2")));
  }

  private static Set<String> asSet(String... str) {
    return new HashSet<>(Arrays.asList(str));
  }
}
