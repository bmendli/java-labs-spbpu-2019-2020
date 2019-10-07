package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppendCharArrayTest {

    @Test
    void testWorkingAppend() {
        StringBuilder builder = new StringBuilder();
        StringBuilder tempBuilder = new StringBuilder();

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        char[] str = {'e', '3', '[', '/'};

        AppendCharArray appendCharArray1 = new AppendCharArray(builder, str, 0, str.length);
        appendCharArray1.execute();
        tempBuilder.append(str);

        assertEquals(
          builder.toString(),
          tempBuilder.toString()
        );

        int offset = 1;
        int len = 3;

        AppendCharArray appendCharArray2 = new AppendCharArray(builder, str, offset, len);
        appendCharArray2.execute();
        tempBuilder.append(str, offset, len);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );
    }
}
