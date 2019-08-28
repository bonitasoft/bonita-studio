# Bonita Studio

This repository contains the source code of the Bonita Studio component from Bonita Platform.  
In order to build this repository it is required to build its dependencies. To help you achieve this there is a dedicated repository [here](https://github.com/Bonitasoft-Community/Build-Bonita).

## Run build

Bonita Studio is an Eclipse RCP. It relies on a custom Eclipse **target platform**.  
Those target platforms are available here [http://update-site.bonitasoft.com/p2](http://update-site.bonitasoft.com/p2).
To setup the target platform properly you need to add the following mirror in the `settings.xml` of your maven instance (`<USER_HOME>/.m2/settings.xml` by default):

```xml
<mirrors>
        <mirror>
             <id>mirrored-studio-p2-repo</id>
			  <mirrorOf>http://repositories.rd.lan/p2/4.10.1</mirrorOf>
			  <url>http://update-site.bonitasoft.com/p2/4.10/</url>
			  <layout>p2</layout>
			  <mirrorOfLayouts>p2</mirrorOfLayouts>
		  </mirror>
		  ...
   </mirrors>
```

Then you can run:

`./mvnw package -DskipTests`

### Built artifacts

* Windows and Linux all-in-one archive is located here `all-in-one/target/BonitaStudioCommunity-*.zip`
* MacOs app is located here `products/org.bonitasoft.studio.product/macosx/cocoa/x86_64`

## Report issues

If you want to report an issue or a bug use our [official bugtracker](https://bonita.atlassian.net/projects/BBPMC)
