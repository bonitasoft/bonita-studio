# Bonita Studio

This repository contains the source code of the Bonita Studio component from Bonita Platform.  
In order to build this repository it is required to build its dependencies. To help you achieve this there is a dedicated repository [here](https://github.com/Bonitasoft-Community/Build-Bonita).

## Prerequisites

* Java 8

## Run build

`./mvnw package -DskipTests`

### Build profiles

* _default_
* _all-in-one_ : bundle a .zip archive
* _it_ : run integration tests
* _jdk11-tests_ : run tests against jdk 11 (using [maven toolchain](https://maven.apache.org/guides/mini/guide-using-toolchains.html))

By default active profiles are: _default_ and _all-in-one_

### Built artifacts

* Windows and Linux all-in-one archive is located here `all-in-one/target/BonitaStudioCommunity-*.zip`
* MacOs app is located here `all-in-one/target/products/org.bonitasoft.studio.product/macosx/cocoa/x86_64`

## Report issues

If you want to report an issue or a bug use our [official bugtracker](https://bonita.atlassian.net/projects/BBPMC)
