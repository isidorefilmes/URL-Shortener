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
package urlshortener.app.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import urlshortener.app.common.URLValidator;
import urlshortener.app.service.URLConverterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;

//We want our URLController to do 2 things:
//1-Receive requests to shorten URL and respond with a shortened URL
//2-Receive a shortened URL and automatically redirect the user to the original website


@RestController
public class URLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);
    private final URLConverterService urlConverterService;

    public URLController(URLConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }

    @RequestMapping(value = "/shortener", method=RequestMethod.POST, consumes = {"application/json"})
    public String shortenUrl(@RequestBody @Valid final ShortenRequest shortenRequest, HttpServletRequest request) throws Exception {
        LOGGER.info("Received url to shorten: " + shortenRequest.getUrl());
        String longUrl = shortenRequest.getUrl();
        if (URLValidator.INSTANCE.validateURL(longUrl)) {
            String localURL = request.getRequestURL().toString();
            String shortenedUrl = urlConverterService.shortenURL(localURL, shortenRequest.getUrl());
            LOGGER.info("Shortened url to: " + shortenedUrl);
            return shortenedUrl;
        }
        throw new Exception("Please enter a valid URL");
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
        LOGGER.info("Received shortened url to redirect: " + id);
        String redirectUrlString = urlConverterService.getLongURLFromID(id);
        LOGGER.info("Original URL: " + redirectUrlString);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://" + redirectUrlString);
        return redirectView;
    }
}

class ShortenRequest{
    private String url;

    @JsonCreator
    public ShortenRequest() {

    }

    @JsonCreator
    public ShortenRequest(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


