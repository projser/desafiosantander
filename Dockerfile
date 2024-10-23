# Use an appropriate base image for your application
FROM openjdk:21-jdk

# Set the working directory
WORKDIR /app

# Copy the wait-for-it.sh script into the image
COPY wait-for-it.sh /wait-for-it.sh

# Give execution permission to the script
RUN chmod +x /wait-for-it.sh

# Copy your application JAR file
COPY build/libs/santander-0.0.1-SNAPSHOT.jar santander.jar

# Specify the command to run your application
CMD ["/wait-for-it.sh", "mysql-db:3306", "--", "java", "-jar", "santander.jar"]
