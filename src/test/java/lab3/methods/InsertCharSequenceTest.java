package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertCharSequenceTest {

    @Test
    void testWorkingInsert() {
        String str = "fthyjujyhtgrhj";
        StringBuilder builder = new StringBuilder(str);
        StringBuilder tempBuilder = new StringBuilder(str);

        int offset = 1;
        String string = "y54rfg";

        InsertCharSequence insertCharSequence = new InsertCharSequence(
            builder,
            offset,
            string,
            0,
            string.length()
        );
        insertCharSequence.execute();
        tempBuilder.insert(offset, string);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int start = 2;
        int end = 4;

        InsertCharSequence insertCharSequence1 = new InsertCharSequence(
            builder,
            offset,
            string,
            start,
            end
        );
        insertCharSequence1.execute();
        tempBuilder.insert(offset, string, start, end);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );
    }
}