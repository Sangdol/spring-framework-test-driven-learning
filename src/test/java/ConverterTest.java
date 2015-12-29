import org.junit.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author hugh
 */
public class ConverterTest {
    GenericConversionService conversionService = new DefaultConversionService();

    /**
     * http://www.javabeat.net/introduction-to-spring-converters-and-formatters/
     *
     * ConversionServiceFactory.createDefaultConversionService is deprecated
     */
    @Test
    public void forStringTypeConvertersTest() throws Exception {
        String csv = "One,Two,Three";

        String[] arr = conversionService.convert(csv, String[].class);
        assertThat(arr[0], is("One"));
        assertThat(arr.length, is(3));

        List<String> list = conversionService.convert(csv, List.class);
        assertThat(list.get(2), is("Three"));
        assertThat(list.size(), is(3));
    }
}
