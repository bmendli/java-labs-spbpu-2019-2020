package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertTest {

    @Test
    void testWorkingInsert() {
        String str = "fthyjujyhtgrhj";
        StringBuilder builder = new StringBuilder(str);
        StringBuilder tempBuilder = new StringBuilder(str);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int offset = 6;

        boolean bool = false;
        Insert<Boolean> insertBooleanFalse = new Insert<>(builder, offset, bool);
        insertBooleanFalse.execute();
        tempBuilder.insert(offset, bool);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        bool = true;
        Insert<Boolean> insertBooleanTrue = new Insert<>(builder, offset, bool);
        insertBooleanTrue.execute();
        tempBuilder.insert(offset, bool);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        char c = 'r';
        Insert<Character> insertChar = new Insert<>(builder, offset, c);
        insertChar.execute();
        tempBuilder.insert(offset, c);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        double d = 3445.345d;
        Insert<Double> insertDouble = new Insert<>(builder, offset, d);
        insertDouble.execute();
        tempBuilder.insert(offset, d);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        float f = 5665.6543f;
        Insert<Float> insertFloat = new Insert<>(builder, offset, f);
        insertFloat.execute();
        tempBuilder.insert(offset, f);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int i = 45;
        Insert<Integer> insertInteger = new Insert<>(builder, offset, i);
        insertInteger.execute();
        tempBuilder.insert(offset, i);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        Object obj = new Object();
        Insert<Object> insertObject = new Insert<>(builder, offset, obj);
        insertObject.execute();
        tempBuilder.insert(offset, obj);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        String string = "ytreefg";
        Insert<String> insertString = new Insert<>(builder, offset, string);
        insertString.execute();
        tempBuilder.insert(offset, string);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );
    }
}