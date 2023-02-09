import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil

def baseDir = properties['project.basedir']
def dryRun = properties['dryRun']?.toBoolean()
def fileName = properties['target.filename']
def targetFile = new File(basedir, fileName)
def pomFile = new File(basedir, 'pom.xml')
def pomFileDryRun = new File(basedir, 'pom.xml.dryRun')

assert pomFile.exists() : "$pomFile not found"
assert targetFile.exists() : "$targetFile not found"

def targetPlatform = new XmlSlurper(false,false).parseText(targetFile.text)
def locations = targetPlatform.locations.location.repository.@location
def units = targetPlatform.locations.location.unit


def projectModel = new XmlSlurper(false,false).parseText(pomFile.text)
// Collect source repositories
projectModel.profiles.profile[0].build.plugins.plugin[0].configuration.source.replaceNode {
    source {
        locations.collect { location ->
                repository {
                    url location
                    layout 'p2'
                }
        }
    }
}
// Collect installation units
projectModel.profiles.profile[0].build.plugins.plugin[0].configuration.ius.replaceNode {
    ius {
        units.collect { unit ->
            iu {
                id unit.@id
                version  unit.@version
            }
        }
    }
}


if(dryRun) {
    pomFileDryRun.write(XmlUtil.serialize(projectModel))
}else {
    pomFile.write(XmlUtil.serialize(projectModel))
}



