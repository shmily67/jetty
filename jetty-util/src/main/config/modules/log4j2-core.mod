[description]
Provides a Log4j v2 implementation. 
To receive jetty logs enable the jetty-slf4j, slf4j-log4j and log4j-log4j2 modules.

[depends]
log4j2-api 

[provides]
log4j2-impl

[files]
basehome:modules/log4j2/log4j2.properties|resources/log4j2.properties
maven://org.apache.logging.log4j/log4j-core/2.6.1|lib/log4j/log4j-core-2.6.1.jar

[lib]
lib/log4j/log4j-core-2.6.1.jar

