#####################################
# Bonita generated web application  #
#####################################
#
# Table of contents
#  1. Contents and Installation
#  2. Startup Properties
#  3. Configuration and multi-tenancy
#  3. Customization
#  4. Troubleshooting
#####################################


1. Contents and Installation
----------------------------

The Bonita War Exporter provides you with the Bonita related resources you need to build your environment.
Along with this readme file you should have several folders:
- conf,
- web,
- processes (if any),
- runtime (if export runtime was checked)

The conf folder contains all content relative to the configuration of client, server and application server:the default configuration files used by

- conf/bonita is the folder to point with BONITA_HOME. You can for example put this folder in the main
 directory of your Web Container.
Examples:
 * Tomcat 5.5+: <tomcat home>/bonita
 * JBoss 5:  <jboss home>/bonita
 
- conf/external contains files relative to the configuration of the application server. You can find for instance a jaas-standard.cfg and logging.properties


The web folder contains all content relative to the UserXP :

- web/bonita.war is the war containing the userXP application.

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

BONITA_HOME must point to the bonita folder you just copy-pasted
It must be set before you start your Web Container.

On Windows:

> set BONITA_OPTS="-DBONITA_HOME=<path to bonita you just copy-paste>"
> set LOG_OPTS="-Djava.util.logging.config.file=<path to logging.properties>"
> set SECURITY_OPTS="-Djava.security.auth.login.config=<path to jaas-standard.cfg>"

> set JAVA_OPTS=%JAVA_OPTS% %LOG_OPTS% %SECURITY_OPTS% %BONITA_OPTS%

On Linux or Mac:

> BONITA_OPTS="-DBONITA_HOME=<path to bonita you just copy-paste>"
> LOG_OPTS="-Djava.util.logging.config.file=<path to logging.properties>"
> SECURITY_OPTS="-Djava.security.auth.login.config=<path to jaas-standard.cfg>"

> export JAVA_OPTS="$JAVA_OPTS $LOG_OPTS $SECURITY_OPTS $BONITA_OPTS

The "java.security.auth.login.config" is mandatory if you want to execute 
Bonita Open Solution with its default configuration.


3. Configuration and multi-tenancy
----------------------------------

All configuration for usage of Bonita Runtime can be found in $BONITA_HOME/default/server/conf.
Here you can customize your environment and also the databases you want to use.

To leverage multi-tenancy, simply create a copy of folder "default" in $BONITA_HOME, and set the
right values for environment and databases.
The tenantId will be the name of the folder you created. Then default tenant name is "default". 

exo-configuration.xml contains repositories configuration. 
You should add one repository per tenant and configure the $BONITA_HOME/server/tenantId/conf/bonita-server.xml to point its repository

4. Customization
----------------

War files provided follow standard War files layout. Then you can find inside of it all CSS,
pictures or other resource files you may want to customize.


5. Troubleshooting
------------------

For further information:
 - Bonita Open Solution documentation: http://www.bonitasoft.org/wiki/doku.php
 - Bonita Open Solution forum: http://www.bonitasoft.org/forum/

