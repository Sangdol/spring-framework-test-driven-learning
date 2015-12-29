import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc-uri-building
 *
 * @author hugh
 */
public class BuildingUrisTest {
    @Test
    public void buildingUrisTest() throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(
                "http://example.com/hotels/{hotel}/bookings/{booking}").build();
        
        URI uri = uriComponents.expand("42", "21").encode().toUri();
        assertThat(uri.toString(), is("http://example.com/hotels/42/bookings/21"));

        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("example.com").path("/hotels/{hotel}/bookings/{booking}").build()
                .expand("42", "21")
                .encode();
        assertThat(uriComponents.toUri().toString(), is("http://example.com/hotels/42/bookings/21"));
    }

    @Test
    public void servletUriComponentsBuilderTest() throws Exception {
        Controller con = new Controller();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(con).build();

        mockMvc.perform(get("/con/building-urls1"))
                .andExpect(content().string("http://localhost/con/building-urls1?accountId=123"));
        mockMvc.perform(get("/con/building-urls2"))
                .andExpect(content().string("http://localhost/con/building-urls2?accountId=123"));
        mockMvc.perform(get("/con/building-urls-path"))
                .andExpect(content().string("http://localhost/accounts"));
        mockMvc.perform(get("/con/building-urls-mapping"))
                .andExpect(content().string("http://localhost/accounts"));
    }

    @RequestMapping("/con")
    public class Controller {
        @RequestMapping("building-urls1")
        @ResponseBody
        public String buildingUrls1(HttpServletRequest request) {
            UriComponents ucb = ServletUriComponentsBuilder.fromRequest(request)
                    .replaceQueryParam("accountId", "123").build();

            return ucb.toUriString();
        }

        @RequestMapping("building-urls2")
        @ResponseBody
        public String buildingUrls2(HttpServletRequest request) {
            UriComponents ucb = ServletUriComponentsBuilder.fromRequest(request)
                    .replaceQueryParam("accountId", "{id}").build()
                    .expand("123")
                    .encode();

            return ucb.toUriString();
        }

        @RequestMapping("building-urls-path")
        @ResponseBody
        public String buildingUrlsPath(HttpServletRequest request) {
            UriComponents ucb = ServletUriComponentsBuilder.fromContextPath(request)
                    .path("/accounts").build();

            return ucb.toUriString();
        }

        @RequestMapping("building-urls-mapping")
        @ResponseBody
        public String buildingUrlsMapping(HttpServletRequest request) {
            UriComponents ucb = ServletUriComponentsBuilder.fromServletMapping(request)
                    .path("/accounts").build();

            return ucb.toUriString();
        }
    }
}
