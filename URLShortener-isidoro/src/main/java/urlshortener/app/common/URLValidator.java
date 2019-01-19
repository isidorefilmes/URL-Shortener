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
package urlshortener.app.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class URLValidator {
    public static final URLValidator INSTANCE = new URLValidator();
    private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    private URLValidator() {
    }

    public boolean validateURL(String url) {
        Matcher m = URL_PATTERN.matcher(url);
        return m.matches();
    }
}
