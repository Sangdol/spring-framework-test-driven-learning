import org.junit.Test;
import org.springframework.web.util.UriComponentsBuilder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UriComponentsBuilderTest {

    /**
     * This doesn't encode uri component
     * https://stackoverflow.com/questions/18138011/url-encoding-using-the-new-spring-uricomponentsbuilder
     */
    @Test
    public void testUriComponentsBuilder() {
        String url = UriComponentsBuilder
                .fromUriString("http://abc.com")
                .queryParam("name", "abc")
                .queryParam("surname", "?=& ä")
                .build().toUriString();

        assertThat(url, is("http://abc.com?name=abc&surname=?=& ä"));
    }
}
