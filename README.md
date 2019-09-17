
## Simple combination of producer and subscriber to demonstrate data pipeline.  

Repository contains
- [Chellenge description]()
- [Code for Producer service](https://github.com/santik/adidas-producer-consumer#producer)
- [Code for Subscriber service](https://github.com/santik/adidas-producer-consumer#subscriber)
- [Docker compose  file for running services locally](https://github.com/santik/adidas-producer-consumer/blob/master/docker-compose.yml)
- [Pipeline proposal](https://github.com/santik/adidas-producer-consumer#pipeline-proposal)
- [Proposal of the whole system (part 1 of chellenge)]()
  
### Producer  
Producer gets events in JSON format via REST API, transforms them into Kafka messages and publishes in corresponding topics.  
REST endpoint is `/activity/`.   
JSON must be sent as a body for the POST request.  
*[Postman collection for API calls](https://github.com/santik/adidas-producer-consumer/blob/master/adidas_activity.postman_collection.json)*
  
Producer application supports following events:  
**Product Viewed**  
```
{
	"userId": "userId",
	"type": "product_viewed",
	"payload": { 
		"productId" : "productId"
	},
	"created": "2019-08-09"
}  
``` 
**Category Viewed**  
```
{
	"userId": "userId",
	"type": "category_viewed",
	"payload": { 
		"categoryId" : "categoryId"
	},
	"created": "2019-08-09"
}  
``` 
**Product Added To Cart**  
```
{
	"userId": "userId",
	"type": "product_added_to_cart",
	"payload": { 
		"productId" : "productId"
	},
	"created": "2019-08-09"
}  
``` 
Resulting Kafka events can be seen in [Commercial Kafka Tool](http://www.kafkatool.com/) or [Opensource Kafka Tool](https://github.com/santik/kafkatool) from my account.

For publishing Kafka messages retry mechanism was set up. 

### Subscriber   
Subscriber listens for Kafka topics with activity events and saves information from them into NoSQL database. 

Chosen database is Redis.
There are 2 reasons for this choice: simplicity of setting up in Spring Boot application and previous experience with that.
  
Events from different topics are saved in a different database namespaces.  
Because of missing information how saved data is going to be used models were designed in a very simple way:

 - Key is concatenation of userId and timestamp when event occurred
 - Value is the whole object. 
 *Assuming the chance of user performing different actions the same time is low keys should not collide.*  
 
Database adapter can be easily replaced as well as repository implementation. 

For monitoring Redis cluster [RDBTools](https://rdbtools.com)  can be used. 

For saving data to the database retry mechanism was set up. 

### Dependencies
The full dependency list can be seen in [producer pom.xml](https://github.com/santik/adidas-producer-consumer/blob/master/producer/pom.xml)  and [subscriber pom.xml](https://github.com/santik/adidas-producer-consumer/blob/master/subscriber/pom.xml)
Bellow listed the main set of dependencies

 - Java11
 - Maven
 - SpringBoot
 - SpringCloudStream
 - JUnit
 - Mockito
 - JBehave
 - Serenity
 
 Kafka and Redis clusters for the local running of application created with [docker-compose](https://github.com/santik/adidas-producer-consumer/blob/master/docker-compose.yml) 

### Pipeline proposal

![](https://github.com/santik/adidas-producer-consumer/blob/master/pipeline.svg)

### Running

For running both applications in the time port for subscriber was changed from the default **8080** to **8081**

 1. Checkout repository
 2. Run `docker-compose up -d` to create Kafka and Redis containers
 3. Run `mvn clean install package`

The easiest way is to run it inside [IntelliJ IDEA](https://www.jetbrains.com/idea/). 
Steps :

 4. Run `producer/src/main/java/com/adidas/producer/Producer.java`
 5. Run `subscriber/src/main/java/com/adidas/subscriber/Subscriber.java`

If there is no IDEA. 

 4. Run `java -jar publisher/target/publisher-DEVELOP-SNAPSHOT.jar` 
 5. Run `java -jar subscriber/target/subscriber-DEVELOP-SNAPSHOT.jar`
 
 ### Testing
 
 Majority of classes are covered with unittests.
 
 Functional flows are covered with functional tests. BDD approach was used to have tests as documentation. 
 - [Story for producer](https://github.com/santik/adidas-producer-consumer/blob/master/producer/src/test/resources/com/adidas/prs/functional/stories/publishes_messages_with_data_from_api.story)
 - [Story for subscriber](https://github.com/santik/adidas-producer-consumer/blob/master/subscriber/src/test/resources/com/adidas/prs/functional/stories/listens_to_topics_and_saves_data_from_messages.story) 
 
 ### Known issues and possible improvements
  - Separate endpoints and separate event schemas can be used in publisher application.
  - Invalid messages can be sent to the separate topic. Not done because it requires much more code for validation. For simplicity validation in current implementation is very strict. On invalid message error is logged.
  - Extract Kafka contracts into library and use them in both applications.
  - Cover negative flows in functional tests.
  - Make it possible to run functional tests as blackbox test in Subscriber. *In Producer it is possible.*