# CityConnections
#Purpose
The Application CityConnections reads the city pairs from file city.txt, and find out that the two cities provided as input in the request, if present in the city.txt file,
  are connected or not. 

The application accepts the url "http://localhost:8080/connected?origin=New York&destination=Newark" with the HTTP Method "GET" and respond with "yes" or "no" 
depending upon the cities are connected or not.

----------------------------------------------------------------------------------------------------------------------------
For e.g the city.txt  contains

Newark, New York
Boston, Newark,
Albany, Trenton

The requests

http://localhost:8080/connected?origin=New York&destination=Newark  will return yes.

http://localhost:8080/connected?origin=New York&destination=Boston will return yes.

http://localhost:8080/connected?origin=New York&destination=Trenton will return no.

----------------------------------------------------------------------------------------------------------------------------

#Application Installation Procedure.
Download the application from github https://github.com/vivmaar/CityConnections.git
Run the following commands
1) mvn clean install
2) mvn spring-boot:run

The requests can be made from Postman or any other REST api client tool after the second command is executed.

#Design of the application
The application is designed and developed as a Spring Boot application. 
On the start of the application using spring boot at port 8080, the application reads the "city.txt", "exception.json", and log.xml  
files from the classpath directory "CityConnections\src\main\resources".

The city.txt contains the city pairs.
The exception.json contains the HTTP error codes and messages.
The log.xml contains the logging configuration.

The application then creates a Adjacency List graph (refer CityGraph.java). The graph is represented as LinkedList of LinkedList to ensure that the data structure can be
extended for extension at runtime if required.

The application also executes the test cases.
All these steps are done during application startup.

Making a Get request for a pair of cities will do a Breadth First Search  in order to search if the cities are connected or not.

Customized exception handling is done to handle exceptional scenarios.
For e.g. http://localhost:8080/connected?origin=SanMatio&destination=Sanfransisco will return the response message "The origin or desination or both pair cannot be found." and 
the HTTP Status of the response will show up as 404. For success scenarios, it is 200 OK. (refer CityConnectionException.java)




