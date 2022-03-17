import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil

println "Update runtime bundle configuration"

def tomcatBundleDir =  new File(properties['tomcat.bundle.dir'])

def contextFile = new File(tomcatBundleDir, 'server/conf/context.xml')
def loggersConfigFile = new File(tomcatBundleDir, 'server/conf/log4j2-loggers.xml')
def loggerAppendersConfigFile = new File(tomcatBundleDir, 'server/conf/log4j2-appenders.xml')

assert contextFile.exists() : 'Tomcat bundle context.xml file not found'
assert loggersConfigFile.exists() : 'Tomcat bundle log4j2-loggers.xml file not found'
assert loggerAppendersConfigFile.exists() : 'Tomcat bundle log4j2-appenders.xml file not found'

println "Set tomcat session cookie path to '/' for uid integration"

def contextNode = new XmlSlurper().parse(contextFile)
contextNode.@sessionCookiePath = '/'
contextFile.write(XmlUtil.serialize(contextNode))

println success()

print "Set hibernate slow query log level to WARN"
def configurationNode = new XmlSlurper().parse(loggersConfigFile)
def slowQueryLogger = configurationNode.'Loggers'.'Logger'.find { it.'@name' == 'org.hibernate.SQL_SLOW' }
assert slowQueryLogger : "org.hibernate.SQL_SLOW logger not found in $loggerConfigFile"
slowQueryLogger.'@level' = 'WARN'
loggersConfigFile.write(XmlUtil.serialize(configurationNode))

println success()

def static String color(String text, String ansiValue) {
        ansiValue + text + '\u001B[0m'
}

def static String success() {
    '....' + color('✓','\u001B[32m')
}
