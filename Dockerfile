# Use Tomcat 10.1 with JDK 20 as the base image
FROM tomcat:10.1-jdk17-openjdk

# Set the working directory inside the container
WORKDIR /usr/local/tomcat/webapps/ROOT

# Copy the WAR file from the location 'CURD_Jdbc/src/target/CURD_Jdbc.war' 
# into Tomcat's webapps directory as ROOT.war (default web application)
COPY target/CURD_Jdbc.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port Tomcat runs on (default is 8080)
EXPOSE 8080

# Start Tomcat in the foreground
CMD ["catalina.sh", "run"]
