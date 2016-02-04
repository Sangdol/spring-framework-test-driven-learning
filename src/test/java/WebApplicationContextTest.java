import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.event.DefaultEventListenerFactory;
import org.springframework.context.event.EventListenerMethodProcessor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.util.SocketUtils;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author hugh
 */
public class WebApplicationContextTest {

    WebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

    @Before
    public void setup() {
        ServletContextListener listener = new ContextLoaderListener(ctx);
        ServletContextEvent servletContextEvent = new ServletContextEvent(new MockServletContext());
        listener.contextInitialized(servletContextEvent);
    }


    @Test
    public void beans() throws Exception {
        int beanCount = ctx.getBeanDefinitionCount();
        ApplicationContext parentCtx = ctx.getParent();

        assertThat(beanCount, is(8));
        assertThat(parentCtx, is(nullValue()));

        // Print all beans
//        getInstantiatedSigletons(ctx).forEach(System.out::println);

        List<Object> beans = getInstantiatedSigletons(ctx);
        assertTrue(beans.get(0) instanceof ConfigurationClassPostProcessor);
        assertTrue(beans.get(1) instanceof AutowiredAnnotationBeanPostProcessor);
        assertTrue(beans.get(2) instanceof RequiredAnnotationBeanPostProcessor);
        assertTrue(beans.get(3) instanceof CommonAnnotationBeanPostProcessor);
        assertTrue(beans.get(4) instanceof EventListenerMethodProcessor);
        assertTrue(beans.get(5) instanceof DefaultEventListenerFactory);

        // Not testable as these are private inner classes.
        // ConfigurationClassPostProcessor$ImportAwareBeanPostProcessor
        // ConfigurationClassPostProcessor$EnhancedConfigurationBeanPostProcessor
    }

    /**
     * http://stackoverflow.com/questions/14829258/how-can-i-get-a-list-of-instantiated-beans-from-spring
     */
    public static List<Object> getInstantiatedSigletons(ApplicationContext ctx) {
        List<Object> singletons = new ArrayList<>();

        String[] all = ctx.getBeanDefinitionNames();

        ConfigurableListableBeanFactory clbf = ((AbstractApplicationContext) ctx).getBeanFactory();
        for (String name : all) {
            Object s = clbf.getSingleton(name);
            if (s != null)
                singletons.add(s);
        }

        return singletons;
    }

}
