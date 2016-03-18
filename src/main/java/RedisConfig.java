import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author hugh
 */
@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jcf = new JedisConnectionFactory();
        jcf.setDatabase(1);
        jcf.setHostName("127.0.0.1");
        jcf.setPort(6379);
        jcf.setPassword("");
        return jcf;
    }

    @Bean
    public StringRedisTemplate redisTemplate(JedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

}
