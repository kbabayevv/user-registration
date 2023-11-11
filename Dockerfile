FROM adoptopenjdk/openjdk11
COPY build/libs/user-registration-0.0.1-SNAPSHOT.jar /user-registration-app/
ENTRYPOINT ["java"]
CMD ["-jar", "/user-registration-app/user-registration-0.0.1-SNAPSHOT.jar"]
