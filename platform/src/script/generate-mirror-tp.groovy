import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil

def baseDir = properties['project.basedir']
def sourceTarget = properties['source.target.filename']
def mirroredTarget = properties['mirror.target.filename']
def mirrorLocation = properties['mirror.location']
def sourceTargetFile = new File(basedir, sourceTarget)
def mirroredTargetFile = new File(basedir, mirroredTarget)

assert sourceTargetFile.exists() : "$sourceTargetFile not found"

def targetPlatform = new XmlSlurper(false,false).parseText(sourceTargetFile.text)
def mavenLocation = targetPlatform.locations.location.find{ it.'@type' == 'Maven' }
def units = targetPlatform.locations.location.unit

targetPlatform.locations.replaceNode { 
    locations {
        location (includeAllPlatforms: false, includeConfigurePhase: true, includeMode: 'planner', includeSource: true, type: 'InstallableUnit'){
            repository(location: mirrorLocation)
            units.collect { u ->
                unit(id: u.@id, version: u.@version)
            }
        }
        mkp.yield(mavenLocation)
    }
}

mirroredTargetFile.write(XmlUtil.serialize(targetPlatform))



