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
package urlshortener.app.repository;

import ai.grakn.redismock.RedisServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class URLRepositoryTest {
    private static RedisServer server;
    @BeforeClass public static void setupServer() throws IOException {
        server = RedisServer.newRedisServer(6789);
        server.start();
    }

    @AfterClass public static void shutdownServer() throws IOException {
        server.stop();
    }

    @Test
    public void test_incrementID_StartsAt0AndIncrements() {
        URLRepository urlRepository = new URLRepository(new Jedis(server.getHost(), server.getBindPort())
                , "id", "url:");
        for (long expectedId = 0L; expectedId < 50L; ++expectedId) {
            long actualId = urlRepository.incrementID();
            assertEquals(expectedId, actualId);
        }
    }
}
