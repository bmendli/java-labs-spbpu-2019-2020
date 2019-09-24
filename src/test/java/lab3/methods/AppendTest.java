package lab3.methods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppendTest {

    @Test
    void testWorkingAppend() {
        StringBuilder builder = new StringBuilder();
        StringBuilder tempBuilder = new StringBuilder();

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        boolean bool = true;
        Append<Boolean> appendBooleanTrue = new Append<>(builder, bool);
        appendBooleanTrue.execute();
        tempBuilder.append(bool);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        bool = false;
        Append<Boolean> appendBooleanFalse = new Append<>(builder, bool);
        appendBooleanFalse.execute();
        tempBuilder.append(bool);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        char c = '/';
        Append<Character> appendChar = new Append<>(builder, c);
        appendChar.execute();
        tempBuilder.append(c);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        double d = 2.45d;
        Append<Double> appendDouble = new Append<>(builder, d);
        appendDouble.execute();
        tempBuilder.append(d);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        float f = 7.34f;
        Append<Float> appendFloat = new Append<>(builder, f);
        appendFloat.execute();
        tempBuilder.append(f);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        int i = 4;
        Append<Integer> appendInteger = new Append<>(builder, i);
        appendInteger.execute();
        tempBuilder.append(i);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        long l = 5L;
        Append<Long> appendLong = new Append<>(builder, l);
        appendLong.execute();
        tempBuilder.append(l);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        Object obj = new Object();
        Append<Object> appendObject = new Append<>(builder, obj);
        appendObject.execute();
        tempBuilder.append(obj);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        String str = "t5tgtrf";
        Append<String> appendString = new Append<>(builder, str);
        appendString.execute();
        tempBuilder.append(str);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );

        StringBuffer stringBuffer = new StringBuffer("gthh");
        Append<StringBuffer> appendStringBuffer = new Append<>(builder, stringBuffer);
        appendStringBuffer.execute();
        tempBuilder.append(stringBuffer);

        assertEquals(
            builder.toString(),
            tempBuilder.toString()
        );
    }
}
