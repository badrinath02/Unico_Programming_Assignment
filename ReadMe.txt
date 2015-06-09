This application has been developed with the following assumptions:
------------------------------------------------------------------
1) A database table with the below given structure is available to store the numbers
 CREATE TABLE  "UNICO_NUMBERS" 
   (	"NUMBER_ONE" NUMBER NOT NULL ENABLE, 
	"NUMBER_TWO" NUMBER NOT NULL ENABLE, 
	"GCD_OF_NUMBERS" NUMBER NOT NULL ENABLE, 
	"CRT_DATE" TIMESTAMP (6) NOT NULL ENABLE
   )
2) An Oracle weblogic 10.3.6 server domain is available with the JAX-RS shared library for RESTful Jersy has been shipped with it.
3) A JMS connection factory with the JNDI name "jms/UnicoConnectionFactory" and JMS Queue with the JNDI name "jms/UnicoJMSQueue" is configured.
4) A weblogic connection pool with the JNDI name "jdbc/UnicoDS" is configured by setting the fine tuning options.
5) Copy environment.properties file to desired file system location and append -Denv_home=<desired-file-system-path> (for example -Denv_home=D:\Oracle_11.1.7.0\UNICO) entry to %JAVA_OPTIONS% within setDomainEnv.cmd file of the weblogic domain.
 

--------- After successful deployment ----------------

RESTful Service Actions
-----------------------
1) Access the URL http://<host-name>:<port-num>/unico/rest/unico/restful/13/15 (numbers are being sent as path params) to push into the Queue.
2) Access the URL http://<host-name>:<port-num>/unico/rest/unico/restful/list to get the list of numbers ever added to the Queue as a JSON response.

SOAP Service Actions
--------------------
1) WSDL for SOAP service would be http://<host-name>:<port-num>/unico/UnicoSOAPApplicationService?WSDL

Based on this WSDL either we can generate the JAX-WS client API using the wsimport command or we can use the SoapUI tool to invoke the service operations.

Note: TestHarness Client is also provided to test the application for concurrent access.

 
 

  