import lombok.Getter;
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

    @Getter
    public static class Person {
        private int id;

        public Person(int id) {
            this.id = id;
        }

        // Used by IdToEntityConverter
        @SuppressWarnings("unused")
        public static Person findPerson(int id) {
            return new Person(id);
        }
    }

    @Test
    public void idToEntityConverterTest() throws Exception {
        String id = "1";

        Person person = conversionService.convert(id, Person.class);
        assertThat(person.getId(), is(1));
   }
}
