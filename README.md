[![Java CI with Maven](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/actions/workflows/maven.yml/badge.svg)](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/actions/workflows/maven.yml)
[![CodeQL](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/actions/workflows/codeql-analysis.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=remisbaima_license-checker-cyclonedx-maven-plugin&metric=alert_status)](https://sonarcloud.io/summary/overall?id=remisbaima_license-checker-cyclonedx-maven-plugin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.remisbaima/license-checker-cyclonedx-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.remisbaima/license-checker-cyclonedx-maven-plugin)

[![License](https://img.shields.io/badge/license-Apache%202.0-brightgreen.svg)][LICENSE]
[![OpenSSF Best Practices](https://www.bestpractices.dev/projects/8628/badge)](https://www.bestpractices.dev/projects/8628)

# license-checker-cyclonedx-maven-plugin
Maven plugin to check if dependencies in CycloneDX BOM files use only allowed licenses.


## Quick start guide
### 1. Add plugins below to the `<plugins>` section of your pom.xml (the sequence of plugin declaration is important)
```xml
  <plugin>
    <groupId>org.cyclonedx</groupId>
    <artifactId>cyclonedx-maven-plugin</artifactId>
    <version>2.5.3</version>
    <executions>
      <execution>
        <phase>package</phase>
        <goals>
          <goal>makeAggregateBom</goal>
        </goals>
      </execution>
    </executions>
    <configuration>
      <outputFormat>json</outputFormat>
    </configuration>
  </plugin>
  <plugin>
    <groupId>io.github.remisbaima</groupId>
    <artifactId>license-checker-cyclonedx-maven-plugin</artifactId>
    <version>0.2.1</version> <!-- x-release-please-version -->
    <executions>
      <execution>
        <phase>package</phase>
        <goals>
          <goal>check</goal>
        </goals>
      </execution>
    </executions>
    <configuration>
      <!-- VALUES BELOW MUST BE CUSTOMISED FOR EACH COMPANY/TEAM/PROJECT -->
      <allowedLicenses>MIT,https://www.apache.org/licenses/LICENSE-1.1</allowedLicenses>
      <allowedLicensesJson>${project.basedir}/licenses.json</allowedLicensesJson>
      <allowedLicensesJsonPath>$[?(@.License_Conflicts=='No')].License_SPDX</allowedLicensesJsonPath>
      <ignoredDependencies>org.codehaus.woodstox:stax2-api:4.2.1</ignoredDependencies>
    </configuration>
  </plugin>
```
See [${project.basedir}/licenses.json](src/test/resources/complex-project/licenses.json)


### 2. Configure plugin options
| Option  | Description |
| ------- | ----------- |
| allowedLicenses         | Comma separated list of SPDX licenses (ID, URL or name) allowed to be used |
| allowedLicensesJson     | URL or file path of a JSON content containing the list of SPDX licenses |
| allowedLicensesJsonPath | Semicolon separated list of JSONPath expressions to extract from the `allowedLicensesJson` the licenses allowed to be used |
| ignoredDependencies     | Comma separated list of dependencies to ignore in the format `<groupId>:<artifactId>:<version>`. This is useful when the CycloneDX Maven Plugin cannot identify the license ID, URL or name of a dependency. If any entry from this list is found in the BOM, it will be ignored and logged with `[WARNING]` since dependencies without a clear license are generally old or not well maintained and should be updated or replaced. |

##### Note
To quickly develop and test a JSONPath expression you can use e.g.: http://jsonpath.herokuapp.com/


### 3. Run Maven
```
mvn package -DskipTests
```


## Requirements
- CycloneDX Maven Plugin v2.5.3+ to generate the BOM files: https://github.com/CycloneDX/cyclonedx-maven-plugin
- Maven v3.5.0+: https://maven.apache.org/
- Java SE 8+: https://www.oracle.com/java/technologies/downloads/#java8


## Disclaimer
This is **not** a compliance tool. There are other tools for this purpose e.g. https://dependencytrack.org/

This plugin is more of a development tool to help developers check as soon as possible if a dependency they want to use has a license allowed by their company policies.


## License
Permission to modify and redistribute is granted under the terms of the Apache 2.0 license. See the [LICENSE] file for the full license.

[LICENSE]: https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/blob/main/LICENSE


## To contribute
Just create a PR :-)

Useful resources:
- https://maven.apache.org/plugin-developers/index.html
- https://www.baeldung.com/maven-plugin
- https://www.baeldung.com/maven-goals-phases
- https://github.com/takari/takari-plugin-testing-project
- https://github.com/cko/predefined_maven_properties/blob/master/README.md
- https://github.com/json-path/JsonPath
- https://github.com/google-github-actions/release-please-action
- https://github.com/actions/setup-java/blob/main/docs/advanced-usage.md#Publishing-using-Apache-Maven
- https://lift.sonatype.com/results/github.com/remisbaima/license-checker-cyclonedx-maven-plugin
- https://central.sonatype.org/publish/publish-guide/
