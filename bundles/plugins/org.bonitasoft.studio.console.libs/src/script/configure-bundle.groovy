println "Configure engine bundle"

def tomcatBundleDir =  new File(properties['tomcat.bundle.dir'])

def securityConfigFile = new File(tomcatBundleDir, 'setup/platform_conf/initial/tenant_template_portal/security-config.properties')
def contextFile = new File(tomcatBundleDir, 'server/conf/context.xml')
def loggerConfigFile = new File(tomcatBundleDir, 'server/conf/log4j2-loggers.xml')

assert securityConfigFile.exists() : 'Console Securtiy Configruation file not found'
assert contextFile.exists() : 'Tomcat bundle context.xml file not found'
assert loggerConfigFile.exists() : 'Tomcat bundle log4j2-loggers.xml file not found'

def securityConfig = new Properties() 
securityConfigFile.withInputStream {
    securityConfig.load(it)
}
println 'Enable rest api authorizations check debug mode'
assert securityConfig.containsKey('security.rest.api.authorizations.check.debug') : "security.rest.api.authorizations.check.debug property not found in $securityConfigFile"
securityConfig."security.rest.api.authorizations.check.debug" = 'true'
securityConfig.store(securityConfigFile.newWriter(), null)

println "Set tomcat session cookie path to '/' for uid integration"
def contextNode = new XmlParser().parse(contextFile)
contextNode.@sessionCookiePath = '/'
contextFile.write(XmlUtil.serialize(contextNode))

println "Set hibernate slow query log level to WARN"
def configurationNode = new XmlParser().parse(loggerConfigFile)
def slowQueryLogger = configurationNode.'Loggers'.'Logger'.find { it.'@name' == 'org.hibernate.SQL_SLOW.level'}
assert slowQueryLogger : "org.hibernate.SQL_SLOW.level logger not found in $loggerConfigFile"
slowQueryLogger.'@level' = 'WARN'
loggerConfigFile.write(XmlUtil.serialize(configurationNode))