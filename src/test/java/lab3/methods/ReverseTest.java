package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseTest {

    @Test
    void testWorkingReverse() {
        String str = "fthyjujyhtgrhj";
        StringBuilder builder = new StringBuilder(str);
        StringBuilder tempBuilder = new StringBuilder(str);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        Reverse reverse = new Reverse(builder);
        reverse.execute();
        tempBuilder.reverse();

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );
    }
}