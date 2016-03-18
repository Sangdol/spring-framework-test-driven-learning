import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author hugh
 */
public class BcryptTest {

    @Test
    public void encodeAndCheckTest() throws Exception {
        String text = "text";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashed = encoder.encode(text);

        assertTrue(hashed.startsWith("$2a$10$"));
        assertTrue(encoder.matches(text, hashed));

        BCryptPasswordEncoder encoder11 = new BCryptPasswordEncoder(11);
        hashed = encoder11.encode(text);
        assertTrue(hashed.startsWith("$2a$11$"));
    }

    @Test
    public void notMatchingTest() throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Log - WARNING: Encoded password does not look like BCrypt
        assertThat(encoder.matches("abc", "hash"), is(false));
    }
}
