package injectionTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author hugh
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = InjectionConfig.class)
public class InjectionConfigTest {

    @Autowired
    private InjectionBean bean;

    @Test
    public void injectTest() throws Exception {
        assertThat(bean.component.id, is(1));
    }

}
