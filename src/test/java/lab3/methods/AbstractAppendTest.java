package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractAppendTest {

    @Test
    void testThrows() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new AbstractAppend(new StringBuilder(), -2) {}
        );
    }
}
