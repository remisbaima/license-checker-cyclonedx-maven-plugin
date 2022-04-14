[![Build Status](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/actions?workflows=Java+CI+with+Maven)

# license-checker-cyclonedx-maven-plugin
Maven plugin to check if dependencies in CycloneDX BOM files use only allowed licenses.


## Maven usage (the sequence of plugin declaration is important)
```xml
<plugins>
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
    <groupId>org.remis</groupId>
    <artifactId>license-checker-cyclonedx-maven-plugin</artifactId>
    <version>0.0.1</version>
    <executions>
      <execution>
        <phase>package</phase>
        <goals>
          <goal>check</goal>
        </goals>
      </execution>
    </executions>
    <configuration>
      <!-- values below must be customised for each company/team/project -->
      <allowedLicenses>MIT</allowedLicenses>
      <allowedLicensesJson>${project.basedir}/allowedLicenses.json</allowedLicensesJson>
      <allowedLicensesJsonPath>$[?(@.License_Conflicts=='No')].License_SPDX</allowedLicensesJsonPath>
      <ignoredDependencies>org.codehaus.woodstox:stax2-api:4.2.1</ignoredDependencies>
    </configuration>
  </plugin>
</plugins>
```
See [${project.basedir}/allowedLicenses.json](src/test/resources/complex-project/allowedLicenses.json)


## Maven configuration options
| Option  | Description |
| ------- | ----------- |
| allowedLicenses         | Comma separated list of SPDX licenses allowed to be used |
| allowedLicensesJson     | URL or file path of a JSON content containing the list of SPDX licenses allowed to be used |
| allowedLicensesJsonPath | JSONPath expression to extract the licenses from the JSON file containing the list of SPDX licenses allowed to be used |
| ignoredDependencies     | Comma separated list of dependencies to ignore in the format `<groupId>:<artifactId>:<version>`. This is useful when the CycloneDX Maven Plugin cannot identify the license of a dependency. If any entry from this list is found in the BOM, it will be ignored and logged with `[WARNING]` since dependencies without a clear license are generally old or not well maintained and should be updated or replaced. |

##### Note
To quickly develop and test a JSONPath expression you can use e.g.: https://jsonpath.com/


## Requirements
- CycloneDX Maven Plugin v2.5.3+ to generate the BOM files: https://github.com/CycloneDX/cyclonedx-maven-plugin
- Maven v3.6.3+: https://maven.apache.org/
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
