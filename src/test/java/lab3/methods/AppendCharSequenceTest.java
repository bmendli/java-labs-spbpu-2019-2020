package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppendCharSequenceTest {

    @Test
    void testWorkingAppend() {
        StringBuilder builder = new StringBuilder();
        StringBuilder tempBuilder = new StringBuilder();

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        String str = "grgge336brsbbr";

        AppendCharSequence appendCharSequence1 = new AppendCharSequence(builder, str, 0, str.length());
        appendCharSequence1.execute();
        tempBuilder.append(str);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int start = 2;
        int end = 9;

        AppendCharSequence appendCharSequence2 = new AppendCharSequence(builder, str, start, end);
        appendCharSequence2.execute();
        tempBuilder.append(str, start, end);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );
    }
}
