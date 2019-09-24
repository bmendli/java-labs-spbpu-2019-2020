package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractInsertTest {

    @Test
    void testThrows() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new AbstractInsert(new StringBuilder(), -1, 3){}
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new AbstractInsert(new StringBuilder(), -5, -3){}
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new AbstractInsert(new StringBuilder(), 1, -3){}
        );
    }
}
