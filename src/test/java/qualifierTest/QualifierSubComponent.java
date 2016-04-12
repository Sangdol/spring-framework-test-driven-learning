package qualifierTest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author hugh
 */
@Component
@Qualifier("QualifierSubComponent")
public class QualifierSubComponent extends QualifierComponent {

    public int id = 2;

    @Override
    public int id() {
        return id;
    }

}
