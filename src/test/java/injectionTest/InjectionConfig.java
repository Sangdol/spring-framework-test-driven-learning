package injectionTest;

import org.springframework.context.annotation.*;

/**
 * @author hugh
 */
@Configuration
@ComponentScan(basePackages = {"injectionTest"})
public class InjectionConfig {

    @Bean
    public InjectionBean inject(InjectionComponent component) {
        InjectionBean bean = new InjectionBean();
        bean.component = component;
        return bean;
    }

}
