FROM openjdk
WORKDIR userdata/lib
ADD ./target/Product-0.0.1-SNAPSHOT.jar /userdata/lib/Product-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Product-0.0.1-SNAPSHOT.jar"]