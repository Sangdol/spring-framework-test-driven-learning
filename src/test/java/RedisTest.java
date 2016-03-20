import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author hugh
 */
@ContextConfiguration(classes = RedisConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {

    @Autowired
    private RedisOperations<String, String> redis;

    @After
    public void tearDown() {
        redis.execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });
    }

    @Test
    public void hashOperationsTest() throws Exception {
        BoundHashOperations<String, String, String> bho = redis.boundHashOps("HASH");
        bho.put("key1", "val1");
        bho.put("key2", "val2");
        bho.put("key3", "val3");

        assertThat(bho.keys().size(), is(3));

        LinkedHashSet<String> orderdKeys = new LinkedHashSet<>(bho.keys());
        assertThat(orderdKeys.iterator().next(), is("key1"));
    }

    /**
     * http://redis.io/commands/ttl
     */
    @Test
    public void expireTest() throws Exception {
        BoundHashOperations<String, String, String> bho = redis.boundHashOps("HASH");

        // Need to set TTL after put data as the hash is not created yet.
        boolean set = bho.expireAt(DateHelper.toDate(DateHelper.now().plusDays(1)));
        assertThat(set, is(false));

        bho.put("ttl", "1day");
        set = bho.expireAt(DateHelper.toDate(DateHelper.now().plusDays(1)));
        assertThat(set, is(true));

        long ttl = redis.execute((RedisCallback<Long>) connection ->
                connection.ttl("HASH".getBytes(StandardCharsets.UTF_8)));
        long day = 3600 * 24;
        assertThat(ttl, is(both(greaterThanOrEqualTo(day - 1)).and(lessThanOrEqualTo(day))));
    }
}
