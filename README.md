# sample-springboot
Spring Boot App

## Stuff Implemented
- Rest Endpoint (Controller)
- Scheduled Task
- Cache
- Auto configuration properties
- Prometheus
- Service sterotype

## Build and Run

```
git clone https://github.com/deep-patel/sample-springboot.git
cd sample-springboot
mvn clean package
java -jar springbootsample-core/target/springbootsample-app-main.jar
```

It starts embedded server there are following options:
- Tomcat (by default)
- Jetty
- Undertow

We can also override default configuration of this servers by setting properties in application.properties

Test: http://localhost:8080/test?id=1

After running the service try hitting url: http://127.0.0.1:8080/actuator/

There are pretty interesting insights which spring actuator gives like all the dependency health, system metrics, cache info, scheduledtasks, httptrace, environment varible.

Also have enabled prometheus so it will give http://127.0.0.1:8080/actuator/prometheus metrics here as well

Metrics emitted by this above URL can be used for telegraf prometheus input: 
https://github.com/influxdata/telegraf/tree/master/plugins/inputs/prometheus