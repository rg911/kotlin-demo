Kotlin feedMe tech test
-----------------------

### Applications

This solution contains two applications:

1. Kotlin Server Side (Spring Boot)
    
   Three components running on the serer side.
   1. Rest service providing restful API endpoints for the UI
   2. Transformer service read & parse incoming packets into type models and Json
   3. Transporter service sends Json to Kafka and from Kafka to MongoDB
   
2. Web UI - ReactJS + Redux. Displaying processed event data 

### Tech Stacks

Kotlin, Spring Boot, Kafka, MongoDB, Node.js, ReactJS, Redux

### Running the app

1. Start all prerequisites and build UI by running `HOST_IP_ADDR=$(ipconfig getifaddr en0) docker-compose up`
   
2. Run Kotlin App.
  
- UI: exposed to port 3002 - [http://localhost:3002](http://localhost:3002)
- API Endpoint: [http://localhost:8080/events](http://localhost:8080/events)
- Kafka: Topic `demo2` created with 5 partitions
- mongodb  - `mongodb://localhost:27017/techTest`

 To run the apps in isolation and in development mode simply comment out the relevant section of `docker-compose.yml` and run that application as described below. 

#### Kafka consumer & producer

Instead of creating separate apps, the server app used Thread Pools (5 threads) to consume on each Kafka topic partition

Producer is also running on a separate thread 


