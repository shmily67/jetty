[description]
Provides a SLF4J implementation that logs to the Java Commons Logging API.  
Requires another module that provides an JCL implementation.
To receive jetty logs enable the jetty-slf4j module.

[depend]
slf4j-api
jcl-api

[provide]
slf4j-impl

[files]
maven://org.slf4j/slf4j-jcl/1.7.21|lib/slf4j/slf4j-jcl-1.7.21.jar

[lib]
lib/slf4j/slf4j-jcl-1.7.21.jar

[exec]
-Dorg.eclipse.jetty.util.log.class=org.eclipse.jetty.util.log.Slf4jLog
