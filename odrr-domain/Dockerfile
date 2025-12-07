# Use official Tomcat image
FROM tomcat:10.1-jdk21

# Remove default ROOT app
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR into webapps
COPY target/odrr-domain.war /usr/local/tomcat/webapps/odrr-domain.war

# Expose port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
