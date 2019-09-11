
## Simple combination of producer and consumer to demonstrate data pipeline.  
  
### Producer  
Producer gets events in JSON format via REST API, transform them into Kafka messages and publishes in corresponding topics.  
REST endpoint is `/activity/`.   
JSON must be sent as a body for the POST request.  
*[Postman collection for API calls](https://github.com/santik/adidas-producer-consumer/blob/master/adidas_activity.postman_collection.json)*
  
Application supports following events:  
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
Resulting Kafka events you can see in [Commercial Kafka Tool](http://www.kafkatool.com/) or [Opensource Kafka Tool](https://github.com/santik/kafkatool) made by me.

### Subscriber   
Subscriber listens for Kafka topics with activity events and saves information from them into NoSQL database. 

Chosen database is Redis.
There are 2 reasons for this choice: simplicity of setting up in Spring Boot application and existing example in another system.
  
Events from different topics are saved in a different database namespaces.  
Because of missing information how saved data is going to be used models were designed in a very simple way:

 - Key is concatenation of userId and timestamp when event occurred
 -  Value is the whole object. 
 
 *Assuming the chance of user performing different actions the same time is low keys should not collide.*  

For monitoring Redis cluster [RDBTools](https://rdbtools.com)  can be used. 

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
 
 Kafka and Redis clusters for the local running created with [docker-compose](https://github.com/santik/adidas-producer-consumer/blob/master/docker-compose.yml) 

### Pipeline proposal

![](https://github.com/santik/adidas-producer-consumer/blob/master/pipeline.svg)

