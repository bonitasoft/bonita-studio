####################################
# Bonita generated web application #
####################################
#
# Table of contents
#  1. Contents and Installation
#  2. Startup Properties
#  3. Customization
#  4. Troubleshooting
####################################


1. Contents and Installation
----------------------------

The Bonita War Exporter provides you with the Bonita related resources you need to build your environment.
Along with this readme file you should have several folders:
- web,
- processes (if any),
- runtime (if export runtime was checked)

The web folder contains all content relative to the UserXP :

- web/bonita.war is the war containing the userXP application.

- The web/conf folder contains the default configuration files used by
 Bonita Open Solution (BOS). You should put them into the "conf" directory of 
 your Web Container.
Examples:
 * Tomcat 5.5+: <tomcat home>/conf
 * JBoss 5:  <jboss home>/server/default/conf
 

- The web/lib folder contains all the libraries needed by BOS at runtime if the export mode uses light mode. You should put them into the lib directory of 
 your Web Container. Generally, you should not keep subfolders, and prefer copy all jars flatten in the target folder. 
Examples:
 * Tomcat 5.5: <tomcat home>/common/lib
 * Tomcat 6: <tomcat home>/lib
 * JBoss 5:  <jboss home>/server/default/lib
Among the libraries you can find the jar archives for hibernate and of course the jars of Bonita engine.


The processes folder contains the processes' web applications. You should put them into the webapp directory of your Web Container.
Examples:
 * Tomcat 5.5+: <tomcat home>/webapps
 * JBoss 5:  <jboss home>/server/web/deploy
Like the web folder it contains wars of your exported processes. If export mode was light, there is a processes/lib folder containing all the 
libraries needed by your processes applications. Put them in the lib directory of your Web Container.
Examples:
 * Tomcat 5.5: <tomcat home>/common/lib
 * Tomcat 6: <tomcat home>/lib
 * JBoss 5:  <jboss home>/server/default/lib
With Tomcat 6, you need to remove jdtcore jar as there is a conflict with the jasper-jdt jar embeded in tomcat.

2. Startup properties
---------------------

Here are the system properties to set to use provided configuration files and get Bonita working.
They must be set before you start your Web Container.

On Windows:

> set BONITA_OPTS="-Dorg.ow2.bonita.environment=<path to bonita-environment.xml>"
> set LOG_OPTS="-Djava.util.logging.config.file=<path to logging.properties>"
> set SECURITY_OPTS="-Djava.security.auth.login.config=<path to jaas-standard.cfg>"
> set JAVA_OPTS=%JAVA_OPTS% %LOG_OPTS% %SECURITY_OPTS% %BONITA_OPTS%

On Linux or Mac:

> BONITA_OPTS="-Dorg.ow2.bonita.environment=<path to bonita-environment.xml>"
> LOG_OPTS="-Djava.util.logging.config.file=<path to logging.properties>"
> SECURITY_OPTS="-Djava.security.auth.login.config=<path to jaas-standard.cfg>"

> export JAVA_OPTS="$JAVA_OPTS $LOG_OPTS $SECURITY_OPTS $BONITA_OPTS"

The "java.security.auth.login.config" is mandatory if you want to execute 
Bonita Open Solution with its default configuration.


3. Customization
----------------

War files provided follow standard War files layout. Then you can find inside of it all CSS,
pictures or other resource files you may want to customize.


4. Troubleshooting
------------------

For further information:
 - Bonita Open Solution documentation: http://www.bonitasoft.org/wiki/doku.php
 - Bonita Open Solution forum: http://www.bonitasoft.org/forum/



