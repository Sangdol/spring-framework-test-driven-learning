package qualifierTest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author hugh
 */
@Component
@Qualifier("QualifierComponent")
public class QualifierComponent implements QualifierInterface {

    public int id = 1;

    @Override
    public int id() {
        return id;
    }

}
