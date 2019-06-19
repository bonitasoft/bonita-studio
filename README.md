# Bonita Studio

Development environment to build your applications with Bonita.
//TODO screenshot studio global

## Quick start

### Installation

[Download](download)

Run the installer
* Linux
  `BonitaStudioCommunity-[version].run`
* Windows
  `BonitaStudioCommunity-[version].exe`
* MacOs
  `BonitaStudioCommunity-[version].dmg`

And you're good to go!
Just follow the [documentation](https://documentation.bonitasoft.com) to start designing your applications 

## To go thu

Bonita Studio is an Eclipse RCP. It relies on a custom Eclipse **target platform**.  
Those target platforms are available here [http://update-site.bonitasoft.com/p2](http://update-site.bonitasoft.com/p2).

`./mvnw package -DskipTests -Pmirrored -Dp2MirrorUrl=http://update-site.bonitasoft.com/p2/4.10`

### Built artifacts

* Windows and Linux all-in-one archive is located here `all-in-one/target/BonitaStudioCommunity-*.zip`
* MacOs app is located here `products/org.bonitasoft.studio.product/macosx/cocoa/x86_64`

## Report issues

If you want to report an issue or a bug use our [official bugtracker](https://bonita.atlassian.net/projects/BBPMC)



[download]:https://www.bonitasoft.com/downloads
