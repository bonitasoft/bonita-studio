println "Configure engine bundle"

def tomcatBundleDir =  new File(properties['tomcat.bundle.dir'])

def securityConfigFile = new File(tomcatBundleDir, 'setup/platform_conf/initial/tenant_template_portal/security-config.properties')
def contextFile = new File(tomcatBundleDir, 'server/conf/context.xml')
//def loggingPropertiesFile = new File(tomcatBundleDir, 'server/conf/logging.properties')

assert securityConfigFile.exists() : 'Console Securtiy Configruation file not found'
assert contextFile.exists() : 'Tomcat bundle context.xml file not found'
//assert loggingPropertiesFile.exists() : 'Tomcat bundle logging.properties file not found'

def securityConfig = new Properties() 
securityConfigFile.withInputStream {
    securityConfig.load(it)
}
println 'Enable rest api authorizations check debug mode'
assert securityConfig.containsKey('security.rest.api.authorizations.check.debug') : "security.rest.api.authorizations.check.debug property not found in $securityConfigFile"
securityConfig."security.rest.api.authorizations.check.debug" = 'true'
securityConfig.store(securityConfigFile.newWriter(), null)

println "Set tomcat session cookie path to '/' for uid integration"
def contextNode = new XmlSlurper().parse(contextFile)
contextNode.@sessionCookiePath = '/'
contextFile.write(XmlUtil.serialize(contextNode))

//println "Set hibernate slow query log level to WARN"
//def loggingProperties = new Properties()
//loggingPropertiesFile.withInputStream {
//  loggingProperties.load(it)
//}
//assert loggingProperties.containsKey('org.hibernate.SQL_SLOW.level') : "org.hibernate.SQL_SLOW.level property not found in $loggingProperties"
//loggingProperties."org.hibernate.SQL_SLOW.level" = 'WARN'
//loggingProperties.store(loggingPropertiesFile.newWriter(), null)