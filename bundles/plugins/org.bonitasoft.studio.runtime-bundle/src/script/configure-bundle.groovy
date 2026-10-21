import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil

import java.util.zip.ZipFile

println "Update runtime bundle configuration"

def tomcatBundleDir =  new File(properties['tomcat.bundle.dir'])

def contextFile = new File(tomcatBundleDir, 'server/conf/context.xml')
def loggersConfigFile = new File(tomcatBundleDir, 'server/conf/log4j2-loggers.xml')
def loggerAppendersConfigFile = new File(tomcatBundleDir, 'server/conf/log4j2-appenders.xml')

assert contextFile.exists() : 'Tomcat bundle context.xml file not found'
assert loggersConfigFile.exists() : 'Tomcat bundle log4j2-loggers.xml file not found'
assert loggerAppendersConfigFile.exists() : 'Tomcat bundle log4j2-appenders.xml file not found'
def libExtDir = new File(tomcatBundleDir, 'server/lib/ext')
// BOSWebServerManager.configureLaunchConfiguration fails the launch without these jars: a
// bundle shipped without them would loop users through a delete-and-re-extract of the same
// defective bundle
assert libExtDir.isDirectory() && libExtDir.listFiles().any { it.name.endsWith('.jar') } \
        : 'Tomcat bundle server/lib/ext contains no jar (log4j must be on the launch classpath)'
assert libExtDir.listFiles().any { it.name.startsWith('slf4j-api-') && it.name.endsWith('.jar') } \
        : 'Tomcat bundle server/lib/ext ships no slf4j-api jar: the studio-watchdog listener cannot load and no watchdog would run'
// An slf4j-api jar alone is not enough: slf4j 2 resolves its binding through the ServiceLoader, so a
// bundle pairing slf4j-api 2.x with the 1.7 log4j-slf4j-impl (StaticLoggerBinder only) finds no
// provider and silently NO-OPs every webapp log line - an armed but completely silent watchdog, which
// is what the bundle shipped until log4j-slf4j2-impl was added. Asserted by provider presence rather
// than by jar name, because the name is not the contract: any jar declaring the service satisfies it
assert libExtDir.listFiles().any { jar ->
    jar.name.endsWith('.jar') && new ZipFile(jar).withCloseable { zip ->
        zip.getEntry('META-INF/services/org.slf4j.spi.SLF4JServiceProvider') != null
    }
} : 'Tomcat bundle server/lib/ext declares no org.slf4j.spi.SLF4JServiceProvider: slf4j 2 would find no binding and every watchdog log line would be silently discarded'

// Must match BOSWebServerManager.TOMCAT_MAJOR_MINOR (org.bonitasoft.studio.engine): the WTP
// adapter validates the installed Tomcat version, so a bundle Tomcat bump must fail here first.
def expectedTomcatVersionPrefix = properties['tomcat.expected.version.prefix']
assert expectedTomcatVersionPrefix : 'tomcat.expected.version.prefix property is not set'
def catalinaJar = new File(tomcatBundleDir, 'server/lib/catalina.jar')
assert catalinaJar.exists() : 'Tomcat bundle catalina.jar not found'
def serverInfo = new ZipFile(catalinaJar).withCloseable { zip ->
    def entry = zip.getEntry('org/apache/catalina/util/ServerInfo.properties')
    assert entry : 'ServerInfo.properties not found in catalina.jar'
    // parsed with Properties like TomcatInstall.matchesAdapter: one rule,
    // one implementation on both sides of the check
    def props = new Properties()
    zip.getInputStream(entry).withCloseable { props.load(it) }
    props.getProperty('server.info', '')
}
def expectedBareVersion = expectedTomcatVersionPrefix.endsWith('.') ? expectedTomcatVersionPrefix[0..-2] : expectedTomcatVersionPrefix
assert serverInfo.startsWith(expectedTomcatVersionPrefix) || serverInfo == expectedBareVersion : "The bundle ships '$serverInfo', which does not match the $expectedTomcatVersionPrefix WTP adapter expected by BOSWebServerManager"

print "Set tomcat session cookie path to '/' for uid integration"
def contextNode = new XmlSlurper().parse(contextFile)
contextNode.@sessionCookiePath = '/'
contextFile.write(XmlUtil.serialize(contextNode))

println success()

print "Set hibernate slow query log level to WARN"
def configurationNode = new XmlSlurper().parse(loggersConfigFile)
def slowQueryLogger = configurationNode.'Loggers'.'Logger'.find { it.'@name' == 'org.hibernate.SQL_SLOW' }
assert slowQueryLogger : "org.hibernate.SQL_SLOW logger not found in $loggersConfigFile"
slowQueryLogger.'@level' = 'WARN'

println success()

print "Set REST API authorization API log level to DEBUG"
configurationNode.'Loggers'.appendNode{
    Logger(level:'DEBUG', name:'org.bonitasoft.engine.authorization.PermissionServiceImpl')
}

loggersConfigFile.write(XmlUtil.serialize(configurationNode))

println success()

def static String color(String text, String ansiValue) {
        ansiValue + text + '\u001B[0m'
}

def static String success() {
    '....' + color('✓','\u001B[32m')
}
