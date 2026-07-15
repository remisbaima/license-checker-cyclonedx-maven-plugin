# Changelog

## [0.0.4](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/compare/v0.2.3...v0.0.4) (2026-07-15)


### Features

* add distributionManagement ([b79297e](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/b79297e9406f83a0777705de231a8f78c2092f3c))
* add support for semicolon separated list of JSONPath expressions ([3460c6a](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/3460c6a61b56f78ac98abb5e95b2930e09e28f38))
* allowed licenses can also contain URL and name (besides ID) ([f5e4326](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/f5e4326553feb99ff53e3cedba9e4763b45f7aed))
* log more details of invalid licenses ([c49bcbf](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/c49bcbf92184a97cea6025ef5bead4b1e062576e))


### Bug Fixes

* add -DskipTests to Maven build ([69c1e0f](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/69c1e0f7e7882f027648cad575a91e5f27ba0b39))
* add test for NPE when license does not exist ([48fffe6](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/48fffe633df992f214e25229ae02c475403377fa))
* add token name ([4815f71](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/4815f71ada05cdd4d5a4b6d1d6568526488b5829))
* bump jackson-databind to fix sonatype lift critical finding ([7c0db44](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/7c0db44e8e140fa69fd6df3e6237f147cd188101))
* change secret name ([ba215bc](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/ba215bc1c728dd2de1691b969ca1021970631d98))
* cleanup changelog ([1ae0a66](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/1ae0a666e2be8fe4f0affc58285391f593261d1a))
* downgrade minimum Maven version from 3.6.3 to 3.5.0 ([5e920b5](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/5e920b5e5e48938738965f9f268d94cbb959d8d0))
* fix mvn deploy ([078da49](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/078da497395b057666da2f76a0f22bfff5b3ca04))
* fix NPE when license does not exist ([d21246e](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/d21246e92e73fe10d0b33e799bfc1aeeb93de697))
* fix sonarcloud finding ([d57f7f4](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/d57f7f4699a84fe28be357476f709159813ccef1))
* fix Sonarcloud findings ([a1de9fd](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/a1de9fdf025de47f254d48a3528e5a697047ef03))
* listen to release created ([8a156dd](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/8a156dd333c21071d75192daf5779dd5685d36b1))
* pass PAT instead of GH token ([8fe2b69](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/8fe2b69fd531f4aaecbce461f699225e3fb5027c))
* remove echo ([36f0056](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/36f0056983d1cdf5a550c75fd4fdf5f8ad126e47))
* Sonarcloud finding ([fbc9b16](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/fbc9b16bf92f12b835fea831ceb6a2311491df4a))
* step msg ([8e8ffd4](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/8e8ffd47fbfbc79d1a6304c55c3afd66e679132f))
* test release ([2b787cb](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/2b787cb91f62db694a9378d57dffa1b3e119b00f))
* test release ([0e28a13](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/0e28a137f14c67a19e1a01c69a8f1a03c1a5eef0))
* update dependency to fix test case ([070cb0f](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/070cb0fed053a1eb24a6c4de1813b6bb2f80bb40))
* upgrade CI Java version from 17 to 21 for checkstyle 13.x compatibility ([fbbdb51](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/fbbdb5150ca87c1c5fb17a3849418f7eca481452))


### Documentation

* add "-DskipTests" to doc so user can build faster ([6608be7](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/6608be7cf0d80882009b89d42e7975697245cbdf))
* add comments in readme to update version ([64b1490](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/64b14904dc721d1b72aa6801b65aa11dd1cc4f33))
* add more badges to readme ([7691491](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/769149150145d22893d082d8a1ad15b5391be8a3))
* add quick start guide ([b05dc92](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/b05dc92ef7055e8def50848b1d8c36034788333d))
* add sonatype lift link ([9a690c5](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/9a690c5285aff14b522f553a25dd117e5002fe9a))
* add x-release-please-version to auto update version in readme ([baa3edb](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/baa3edb5df456f4a09e87ac7a47a661e97eb4f0b))
* change sonarcloud badge to overall code ([f797930](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/f7979305e1cba08ee46c8dc3eaf79706e900ecbd))
* cleanup changelog ([fac3cda](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/fac3cdaee91452bdb76996f98219e2f2325a14a6))
* delete &lt;plugins&gt; tag to facilitate copying plugin config ([67c238a](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/67c238ae0ca746cde1f4f3cc1fb5d24df340ae74))
* delete changelog of v0.0.2 as it was not released ([b815679](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/b81567908db39cc746b66b281a4c5eaf6282e2c1))
* doc usage of license ID, URL or name ([5415002](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/54150023d322854d2f054930a971fbf5bd0e7bd9))
* downgrade minimum Maven version from 3.6.3 to 3.5.0 ([bf10e03](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/bf10e03994a0559d11896bee49965286836412d0))
* fix typo ([5f0fbc5](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/5f0fbc5dac3ea7860b72cd2503da44fee890d24b))
* fix URLs in pom.xml ([8b27c43](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/8b27c43720d9c709837e46c54e27e069c20d9bfa))
* remove release v0.0.4 as release build failed ([d1a50fe](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/d1a50feb22aebf2425d29605dceffcb8988aace7))
* update link to check JSONPath ([fb752d1](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/fb752d1b3e9e93219764b5dc177b015371e77f85))
* update location of comment ([2e606dd](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/2e606dd8539b48286630e44ba8ed22a890246625))
* update location of hidden comment ([d718a21](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/d718a213d6a76377f4baa4d93df0b78247511c7e))
* update location of hidden comment ([da0b328](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/da0b3284a100c6055b1e3e99002fa0be3b58d517))
* update version in pom.xml ([c2570ad](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/c2570ad420205c71ad81450792bc8c9d685a92de))
* update version in pom.xml ([28f54d5](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/28f54d585ca676417af4349ce74c879d89e0a2b3))
* update version in readme ([3bd6972](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/3bd69727a925ba4d5816c34bc0c837774cf91a81))
* update version in readme ([37fb340](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/37fb340419dc8e87e6d6a3f2d47d240798a04320))
* update version in readme ([a117fb7](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/a117fb72e23cfdf196bd1836af7437419c4a5c98))


### Miscellaneous Chores

* release 0.0.3 ([c658a87](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/c658a87812dcb7b076d0b006263c9c25fc56b54a))
* release 0.0.4 ([d01198a](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/d01198a8d481a07de9f1de22bb2f0ef5ab1fa361))

## [0.2.3](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/compare/v0.2.2...v0.2.3) (2026-07-15)


### Bug Fixes

* update dependency to fix test case ([070cb0f](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/070cb0fed053a1eb24a6c4de1813b6bb2f80bb40))
* upgrade CI Java version from 17 to 21 for checkstyle 13.x compatibility ([fbbdb51](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/fbbdb5150ca87c1c5fb17a3849418f7eca481452))

## [0.2.2](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/compare/v0.2.1...v0.2.2) (2024-03-03)


### Documentation

* downgrade minimum Maven version from 3.6.3 to 3.5.0 ([bf10e03](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/bf10e03994a0559d11896bee49965286836412d0))

### [0.2.1](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/compare/v0.2.0...v0.2.1) (2022-05-02)


### Bug Fixes

* downgrade minimum Maven version from 3.6.3 to 3.5.0 ([5e920b5](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/5e920b5e5e48938738965f9f268d94cbb959d8d0))

## [0.2.0](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/compare/v0.1.0...v0.2.0) (2022-05-02)


### Features

* add support for semicolon separated list of JSONPath expressions ([3460c6a](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/3460c6a61b56f78ac98abb5e95b2930e09e28f38))


### Bug Fixes

* add test for NPE when license does not exist ([48fffe6](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/48fffe633df992f214e25229ae02c475403377fa))
* fix NPE when license does not exist ([d21246e](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/d21246e92e73fe10d0b33e799bfc1aeeb93de697))
* Sonarcloud finding ([fbc9b16](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/fbc9b16bf92f12b835fea831ceb6a2311491df4a))


### Documentation

* update link to check JSONPath ([fb752d1](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/fb752d1b3e9e93219764b5dc177b015371e77f85))

## [0.1.0](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/compare/v0.0.5...v0.1.0) (2022-05-01)


### Features

* allowed licenses can also contain URL and name (besides ID) ([f5e4326](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/f5e4326553feb99ff53e3cedba9e4763b45f7aed))
* log more details of invalid licenses ([c49bcbf](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/c49bcbf92184a97cea6025ef5bead4b1e062576e))


### Documentation

* add "-DskipTests" to doc so user can build faster ([6608be7](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/6608be7cf0d80882009b89d42e7975697245cbdf))
* doc usage of license ID, URL or name ([5415002](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/54150023d322854d2f054930a971fbf5bd0e7bd9))

### [0.0.5](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/compare/v0.0.4...v0.0.5) (2022-04-27)


### Documentation

* add more badges to readme ([7691491](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/769149150145d22893d082d8a1ad15b5391be8a3))
* add quick start guide ([b05dc92](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/b05dc92ef7055e8def50848b1d8c36034788333d))
* fix URLs in pom.xml ([8b27c43](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/8b27c43720d9c709837e46c54e27e069c20d9bfa))

### [0.0.4](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/compare/v0.0.3...v0.0.4) (2022-04-24)


### Miscellaneous Chores

* release 0.0.4 ([d01198a](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/d01198a8d481a07de9f1de22bb2f0ef5ab1fa361))


### Documentation

* add comments in readme to update version ([64b1490](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/64b14904dc721d1b72aa6801b65aa11dd1cc4f33))
* add x-release-please-version to auto update version in readme ([baa3edb](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/baa3edb5df456f4a09e87ac7a47a661e97eb4f0b))

### [0.0.3](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/compare/v0.0.2...v0.0.3) (2022-04-24)


### Bug Fixes

* bump jackson-databind to fix sonatype lift critical finding ([7c0db44](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/7c0db44e8e140fa69fd6df3e6237f147cd188101))
* fix sonarcloud finding ([d57f7f4](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/d57f7f4699a84fe28be357476f709159813ccef1))
* fix Sonarcloud findings ([a1de9fd](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/a1de9fdf025de47f254d48a3528e5a697047ef03))


### Documentation

* add sonatype lift link ([9a690c5](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/9a690c5285aff14b522f553a25dd117e5002fe9a))
* change sonarcloud badge to overall code ([f797930](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/f7979305e1cba08ee46c8dc3eaf79706e900ecbd))
* delete <plugins> tag to facilitate copying plugin config ([67c238a](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/67c238ae0ca746cde1f4f3cc1fb5d24df340ae74))
* fix typo ([5f0fbc5](https://github.com/remisbaima/license-checker-cyclonedx-maven-plugin/commit/5f0fbc5dac3ea7860b72cd2503da44fee890d24b))
