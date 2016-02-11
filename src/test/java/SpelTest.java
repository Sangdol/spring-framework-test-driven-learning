import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/expressions.html
 * @author hugh
 */
public class SpelTest {

    @Test
    public void parser() throws Exception {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'");
        assertThat(exp.getValue(), is("Hello World"));
    }


}
