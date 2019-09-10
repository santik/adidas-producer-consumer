Simple combination of producer and consumer.

Producer gets events JSON from REST API and transform them into kafka messages.

REST endpoint is `/activity/`. JSON must be sent as a body for the POST request.
Postman project attached.

Supporting following events:
Product Viewed
Example json
`
{
  "userId": "userId",
  "type": "product_viewed",
  "payload": {
  	"productId" : "product_id"
  },
  "created": "2019-08-09"
}
`


Consumer consumes events from kafka and save them into database.


