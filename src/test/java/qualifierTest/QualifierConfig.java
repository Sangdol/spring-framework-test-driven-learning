package qualifierTest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

/**
 * @author hugh
 */
@Configuration
@ComponentScan(basePackages = {"qualifierTest"})
public class QualifierConfig {

    @Bean(name = "QualifierBean")
    public QualifierInterface inject(@Qualifier("QualifierComponent") QualifierComponent component,
        @Qualifier("QualifierSubComponent") QualifierSubComponent subComponent) {

        QualifierInterface bean = component;
        return bean;
    }

}
