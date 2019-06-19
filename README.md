# Bonita Studio

Development environment to build your applications with Bonita.
//TODO screenshot studio global

## Installation

[Download][download]

Run the installer
* Linux
  `BonitaStudioCommunity-[version].run`
* Windows
  `BonitaStudioCommunity-[version].exe`
* MacOs
  `BonitaStudioCommunity-[version].dmg`

And you're good to go!
Just follow the [documentation](documentation) to start designing your applications 

## To go further


### How does it work

//TODO @Romain @Adrien


Bonita Studio is an Eclipse RCP. It relies on a custom Eclipse **target platform**.  
Those target platforms are available here [http://update-site.bonitasoft.com/p2](http://update-site.bonitasoft.com/p2).


### Structure

//TODO @Romain @Adrien





### Build it

Run
`./mvnw package -DskipTests -Pmirrored -Dp2MirrorUrl=http://update-site.bonitasoft.com/p2/4.10`

This will produce the binary
* for Windows and Linux all-in-one archive is located here `all-in-one/target/BonitaStudioCommunity-*.zip`
* for MacOs app is located here `products/org.bonitasoft.studio.product/macosx/cocoa/x86_64`



### Run tests
//TODO @Romain @Adrien


## Contribute


### Report issues
If you want to report an issue or a bug use our [official bugtracker](https://bonita.atlassian.net/projects/BBPMC)


### How to contribute
Before contributing, read the [guidelines][contributing.md]


## Resources

//TODO Javadoc, doc, download


[download]: https://www.bonitasoft.com/downloads
[documentation]: https://documentation.bonitasoft.com
[contributing.md]: todo.com
