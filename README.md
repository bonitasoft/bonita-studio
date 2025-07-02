# Bonita Studio

This repository contains the source code of the Bonita Studio component from Bonita Platform.  
In order to build this repository it is required to build its dependencies. To help you achieve this there is a dedicated repository [here](https://github.com/Bonitasoft-Community/Build-Bonita).

## Prerequisites

* Java 17

## Run build

```shell
./mvnw package -DskipTests
```

### Build profiles

* _default_
* _all-in-one_: build product .zip archives
* _it_: run integration tests

By default active profiles are: _default_ and _all-in-one_

### Built artifacts

* Products archive are located here `all-in-one/target/products`

## :earth_africa: Include additional localization 

It is possible to include additional localizations but it requires a rebuild of the Studio for now. Above are the instructions to update the source code to include a new localization. Additional languages will be automaticaly detected at runtime.

> :blue_book: Only the Studio part of the Bonita platform is invovled here. UI Designer and applications use other mechanisms.

### Bonita localization from Crowdin 

It is possible to include other locale available on [Crowdin](https://crowdin.com/project/bonita) using the following maven command from the root of the project:

```shell
./mvnw groovy:execute@fetch-l10n -Dcrowdin.locale=<LOCALE_CODE> -Dcrowdin.branch=<BRANCH> -Dtycho.mode=maven
```

Where:

* `LOCALE_CODE` must be an available locale in the Bonita crowdin project (eg: `tr`, `vi`...etc)  
* `BRANCH` must be one of the available branch in the crowdin project (eg: `dev`, `master`, `7.13.x`...etc)

### Eclipse localization

Eclipse l10n are provided by the [Babel project](https://www.eclipse.org/babel/). You may include additional eclipse languages in your build. To do so, you must modify the `platform/platform.target` file and add the babel update site location like this (using the `tr` locale):

```xml
 <location includeAllPlatforms="false" includeConfigurePhase="true" includeMode="planner" includeSource="true" type="InstallableUnit">
            <unit id="org.eclipse.babel.nls_eclipse_tr.feature.group" version="0.0.0"/>
            <unit id="org.eclipse.babel.nls_technology.egit_tr.feature.group" version="0.0.0"/>
            <unit id="org.eclipse.babel.nls_technology.jgit_tr.feature.group" version="0.0.0"/>
            <repository location="https://download.eclipse.org/technology/babel/update-site/latest/"/>
</location>
```

Then you must include the `tr` localization features in the `studio-feature` in `bundles/features/studio-feature/feature.xml`

## Report issues

If you want to report an issue or a bug use our [official bugtracker](https://bonita.atlassian.net/projects/BBPMC)
