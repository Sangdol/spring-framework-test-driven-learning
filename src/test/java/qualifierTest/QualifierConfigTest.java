package qualifierTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author hugh
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QualifierConfig.class)
public class QualifierConfigTest {

    @Autowired
    @Qualifier("QualifierBean")
    private QualifierInterface bean;

    @Test
    public void injectTest() throws Exception {
        assertThat(bean.id(), is(1));
    }

}
