
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



Consumer consumes events from kafka and save them into database.