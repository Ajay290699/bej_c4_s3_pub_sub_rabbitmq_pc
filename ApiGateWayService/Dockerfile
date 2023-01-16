FROM openjdk
WORKDIR api/lib
ADD ./target/ApiGatewayServiceApp-0.0.1-SNAPSHOT.jar /api/lib/ApiGatewayServiceApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ApiGatewayServiceApp-0.0.1-SNAPSHOT.jar"]