package lab3.methods;

import lab3.ExecuteException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractMethodsOwnStringBuilderTest {

    @Test
    void testThrows() {

        AbstractMethodsOwnStringBuilder builder = new AbstractMethodsOwnStringBuilder(new StringBuilder()) {};

        assertThrows(
            NullPointerException.class,
            () -> new AbstractMethodsOwnStringBuilder(null) {}
        );
        assertThrows(
            ExecuteException.class,
            () -> {
                builder.setStatusIsExecute();
                builder.execute();
            }
        );
        assertThrows(
            ExecuteException.class,
            () -> {
                builder.setStatusIsUnExecute();
                builder.undo();
            }
        );
        assertThrows(
            ExecuteException.class,
            () -> {
                builder.setStatusIsUnExecute();
                builder.execute();
                builder.undo();
            }
        );
        assertThrows(
            ExecuteException.class,
            () -> {
                builder.setStatusIsExecute();
                builder.undo();
                builder.execute();
            }
        );

    }
}
