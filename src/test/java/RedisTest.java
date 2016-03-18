import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
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

        assertThat(bho.keys().size(), is(2));
    }

}
