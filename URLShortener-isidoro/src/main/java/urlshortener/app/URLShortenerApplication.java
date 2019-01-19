/*
Code by Peterson Isidoro
To run:
1. Start up Redis' Server
redis-server
Build the project
gradle build
Run the project
gradle run

By default the Server will run on localhost:8080/shortener
To test, send POST Request to localhost:8080/shortener with a body of type application/json with body
{
  'url' : '<INSERT URL>'
}
*/
package urlshortener.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class URLShortenerApplication {
    public static void main(final String[] args) {
        SpringApplication.run(URLShortenerApplication.class, args);
    }
}
