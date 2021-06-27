==============================================================================================================

Pre-requisites
---------------
	1. Java 8 or above
	2. maven
	3. Rest client for e.g. Postman

==============================================================================================================

Overview
----------

The application is intented for the usage of managing the information of stocks in the market

==============================================================================================================

Hpw to run
------------
After browsing to the project's main directory, kindly follow the following steps:

1. Execute all the queries from DB_script.sql under src/main/resources. Build the 
   application using following command
	mvn clean install 
	
2. Run the application using the following command
	mvn spring-boot:run

3. In the rest client configure Authentication to No Auth and add the header of application/json

4. Follow the document for API urls and sample request body JSONs. DB tables creation script is present in the
	resources folder.

==============================================================================================================
	
Scope
------
Due to time & external APIs usage constraints the application holds the potential to be boosted with some of 
the following additional features: 

1. Dockerizing/Containerizing the services and then accessing them through docker server.
	 Auto scaling of the containers is helpful in managing the varying traffic to the application

2. A vivid UI to make application more interactive and explain the messages in extremely user friendly manner

==============================================================================================================