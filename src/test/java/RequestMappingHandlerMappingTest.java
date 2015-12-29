import org.junit.Test;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author hugh
 */
public class RequestMappingHandlerMappingTest {

    /**
     * Hierarchy
     *
     * @see HandlerMapping
     * @see AbstractHandlerMethodMapping
     * @see RequestMappingInfoHandlerMapping
     */
    RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();

    @Test
    public void requestMappingInfoTest() throws Exception {

    }

    @Test
    public void requestMappingInfoHandlerMethodMappingNamingStrategyTest() throws Exception {

    }
}
