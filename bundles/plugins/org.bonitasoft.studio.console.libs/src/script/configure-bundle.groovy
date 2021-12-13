println "Configure engine bundle"

def tomcatBundleDir =  new File(properties['tomcat.bundle.dir'])

def contextFile = new File(tomcatBundleDir, 'server/conf/context.xml')
def loggerConfigFile = new File(tomcatBundleDir, 'server/conf/log4j2-loggers.xml')

assert contextFile.exists() : 'Tomcat bundle context.xml file not found'
assert loggerConfigFile.exists() : 'Tomcat bundle log4j2-loggers.xml file not found'

println "Set tomcat session cookie path to '/' for uid integration"
def contextNode = new XmlSlurper().parse(contextFile)
contextNode.@sessionCookiePath = '/'
contextFile.write(XmlUtil.serialize(contextNode))

println "Set hibernate slow query log level to WARN"
def configurationNode = new XmlSlurper().parse(loggerConfigFile)
def slowQueryLogger = configurationNode.'Loggers'.'Logger'.find { it.'@name' == 'org.hibernate.SQL_SLOW'}
assert slowQueryLogger : "org.hibernate.SQL_SLOW logger not found in $loggerConfigFile"
slowQueryLogger.'@level' = 'WARN'
loggerConfigFile.write(XmlUtil.serialize(configurationNode))
