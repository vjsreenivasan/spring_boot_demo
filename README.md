

In a basic Dockerized Springboot Maven application, build a single REST API endpoint that returns a filtered set of products from the provided data in the data.csv file

GET /product
Query Parameter			Description
type					The product type. (String. Can be 'phone' or 'subscription')
min_price				The minimum price in SEK. (Number)
max_price				The maximum price in SEK. (Number)
city					The city in which a store is located. (String)
property				The name of the property. (String. Can be 'color' or 'gb_limit')
property:color			The color of the phone. (String)
property:gb_limit_min 	The minimum GB limit of the subscription. (Number)
property:gb_limit_max 	The maximum GB limit of the subscription. (Number)

The expected response is a JSON array with the products in a 'data' wrapper. 

Example: GET /product?type=subscription&max_price=1000&city=Stockholm
{
	data: [ 
		{
		    type: 'subscription',
		    properties: 'gb_limit:10',
		    price: '704.00',
		    store_address: 'Dana gärdet, Stockholm'
	  	},
	  	{
		    type: 'subscription',
		    properties: 'gb_limit:10',
		    price: '200.00',
		    store_address: 'Octavia gränden, Stockholm'
	  	}
	]
}

Your solution should correctly filter any combination of API parameters and use some kind of a datastore.
All parameters are optional, all minimum and maximum fields should be inclusive (e.g. min_price=100&max_price=1000 should return items with price 100, 200... or 1000 SEK). 
The applications does not need to support multiple values (e.g. type=phone,subscription or property.color=green,red).

We should be able to:
- build the application with Maven
- build the Docker image and run it
- make requests to verify the behavior

Please provide an archive with the source code and a list of the terminal commands to build and run the application.

In this app, I used H2 in-memory database

**To Run without Docker**

```
> mvn clean install
> java -jar target/TelenorAssignment-0.0.1-SNAPSHOT.jar
```

**To Run with Docker**
```
> mvn clean install
> docker build -t springio/gs-spring-boot-docker .
> docker run -p 8080:8080 -t springio/gs-spring-boot-docker

> docker stop <image-name>
```
