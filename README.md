#     Backoffice API Service (Maven Spring Boot Application)

## What is this for?
 
* Provide extension to consume Reatil.Net backoffice components through REST End Points meant for consumed by any thin client.
* Facilitate code reuse of 'rnet-domain' components through rest end point.
* Rnet-Domain backbone components are supposed to be up & running in order to fulfill any incoming request.
* API call hit backoffice core components to get data and then wrap this data according to client requirement .
* Rest wrapper arround existing rnet-domain components , which exposed existing back-office business logic through Rest endpoint.
* These api endpoints are only accessible for authorize user through token mechanism.(udToken).
* This udToken can be get from Security api which internally hit IDM tool to authenticate user.
* These token are stored in Redis Server for a particular period of time and validated before actual service processing.
* Without valid udtoken api cannot be accessible.

## Prerequisites
- Java
- Maven
- Server Certificate (Only when run over https )
- Redis server (Configure Redis server on local or point existing redis server master & sentinel nodes to application configuration file.)
- Postman /any Rest Client
- IDE
- Lombok Plugin


## Configuration File

- **application.properties**

## Building the Application (IDE Setup)

 1. Checkout latest code from repo.
 2. Import project into your IDE as per your choice.
 3. Configure your work-space (JDK ,Mavan, GIT etc).
 4. Checkout /create your local develop branch from remote develop branch.
 5. Execute Mavan goal for root module.  **Clean** → **Compile** → **Install** This will create deployable artifacts of application i.e . jar file 
 6. Set profile in application.properties
 7. Edit application-{profile}.properties file & set all required configuration.
 8. Create Run Configuration in IDE.
 9. Start Run Configuration or Run As SpringBoot application - RnetDomainApiApplication
 10. Once Server is up & running.
 11. Use any Rest client to access api endpoint.
 12. Deep dive into code & understand each endpoint url & required params as well as method type.
 13. Get udtoken & satoken from Security api
 14. Use these token & access various Rest end point 
    
     Example:-
     
     - https://localhost:9070/backoffice/items
     
     Swagger UI
     
     - https://localhost:9070/backoffice/swagger-ui.html?udtoken={UdToken}
     Example- http://10.2.2.159:9070/swagger-ui.html?udtoken=0-BAB43E4C515C550914D534A82CA4236EDF77EC381014819C-1


     http://localhost:9070/swagger-ui.html?udtoken=0-50219F2D95FC75B6018914389E4A6953B36483D1460B8905-1
     Get udToken by through authneticate/login api
     Use this udToken to get User login Details
     Use udToken to access allbackoffice api

     Get saToken for Service account Key
     Get this udToken from security-api for saapkey,IDM username & pwd.
     Use Udtoken to access any API end point as per API definition.
 
## Running the Application

1. Running though IDE

              Run as Springboot -RnetDomainApiApplication

2. Run as service

              java -jar target/backoffice-api-core-1.0.0-SNAPSHOT.jar
