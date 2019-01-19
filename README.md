# URL-Shortener
<h1>These are the tests made for the vacancy opened by VANHACK (Ireland - Neueda).<h1>

There are two projects:
The first in JAVA
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



The second in Javascript
Files not covered in the video:
* `apache.conf` contains the configuration I used to make `localhost:8088/api` go to `localhost:8989`
* `database.sql` contains the SQL code to create the database so you don't have to just type it all out
