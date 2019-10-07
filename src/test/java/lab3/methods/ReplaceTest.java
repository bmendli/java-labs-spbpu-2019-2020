package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceTest {

    @Test
    void testWorkingReplace() {
        String str = "fthyjujyhtgrhj";
        StringBuilder builder = new StringBuilder(str);
        StringBuilder tempBuilder = new StringBuilder(str);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int start = 2;
        int end = 6;
        String string = "hgfds";

        Replace replace = new Replace(builder, start, end, string);
        replace.execute();
        tempBuilder.replace(start, end, string);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );
    }
}