package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppendCodePointTest {

    @Test
    void testWorkingAppendCodePoint() {
        StringBuilder builder = new StringBuilder();
        StringBuilder tempBuilder = new StringBuilder();

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int codePoint = 48;

        AppendCodePoint appendCodePoint = new AppendCodePoint(builder, codePoint);
        appendCodePoint.execute();
        tempBuilder.appendCodePoint(codePoint);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );
    }
}
