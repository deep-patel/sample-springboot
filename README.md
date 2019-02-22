# sample-springboot
Spring Boot App

## Build and Run

```
git clone https://github.com/deep-patel/sample-springboot.git
cd sample-springboot
mvn clean package
java -jar springbootsample-core/target/springbootsample-app-main.jar
```

After running the service try hitting url: http://127.0.0.1:8080/actuator/

There are pretty interesting insights which spring actuator gives like all the dependency health, system metrics, cache info, scheduledtasks, httptrace, environment varible.

Also have enabled prometheus so it will give http://127.0.0.1:8080/actuator/prometheus metrics here as well


