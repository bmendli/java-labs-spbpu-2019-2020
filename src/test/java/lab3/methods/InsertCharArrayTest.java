package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertCharArrayTest {

    @Test
    void testWorkingInsert() {
        String str = "fthyjujyhtgrhj";
        StringBuilder builder = new StringBuilder(str);
        StringBuilder tempBuilder = new StringBuilder(str);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int offset = 1;
        char[] chars = {'r', '5', 'h', '\''};

        InsertCharArray insertCharArray = new InsertCharArray(builder, offset, chars, 0, chars.length);
        insertCharArray.execute();
        tempBuilder.insert(offset, chars);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int index = 4;
        int len = 2;
        InsertCharArray insertCharArray1 = new InsertCharArray(builder, index, chars, offset, len);
        insertCharArray1.execute();
        tempBuilder.insert(index, chars, offset, len);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );
    }
}