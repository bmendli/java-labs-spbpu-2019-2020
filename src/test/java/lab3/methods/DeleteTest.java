package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteTest {

    @Test
    void testWorkingDelete() {
        String str = "5fvdgeyh5yu4y8yt212yj6kj";
        StringBuilder builder = new StringBuilder(str);
        StringBuilder tempBuilder = new StringBuilder(str);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int start = 6;

        Delete delete1 = new Delete(builder, start, start + 1);
        delete1.execute();
        tempBuilder.deleteCharAt(start);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int end = start + 3;

        Delete delete2 = new Delete(builder, start, end);
        delete2.execute();
        tempBuilder.delete(start, end);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );
    }

}