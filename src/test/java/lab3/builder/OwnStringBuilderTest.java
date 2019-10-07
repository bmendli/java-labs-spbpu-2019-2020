package lab3.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OwnStringBuilderTest {

    @Test
    void testWorkingConstructor() {

        assertDoesNotThrow(OwnStringBuilderTest::new);

        OwnStringBuilder ownStringBuilder = new OwnStringBuilder();
        StringBuilder stringBuilder = new StringBuilder();

        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        String string = "ddfgg3";
        ownStringBuilder = new OwnStringBuilder((CharSequence)string);
        stringBuilder = new StringBuilder((CharSequence)string);

        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        int i = 8;
        ownStringBuilder = new OwnStringBuilder(i);
        stringBuilder = new StringBuilder(i);

        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        string = "rty654er";
        ownStringBuilder = new OwnStringBuilder(string);
        stringBuilder = new StringBuilder(string);

        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );
    }

    @Test
    void testWorkingMethods() {
        OwnStringBuilder ownStringBuilder = new OwnStringBuilder();
        StringBuilder stringBuilder = new StringBuilder();

        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        boolean b = false;
        ownStringBuilder.append(b);
        stringBuilder.append(b);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        char c = '5';
        ownStringBuilder.append(c);
        stringBuilder.append(c);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        char[] string = {'4', 'r', 'd', 'd', '.', 'g'};
        ownStringBuilder.append(string);
        stringBuilder.append(string);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        int offset = 1;
        int len = 3;
        ownStringBuilder.append(string, offset, len);
        stringBuilder.append(string, offset, len);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        String string1 = "hy54egrt";
        ownStringBuilder.append((CharSequence) string1);
        stringBuilder.append((CharSequence) string1);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        int start = 2;
        int end = 4;
        ownStringBuilder.append((CharSequence) string1, start, end);
        stringBuilder.append((CharSequence) string1, start, end);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        double d = 56.565d;
        ownStringBuilder.append(d);
        stringBuilder.append(d);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        float f = 5535.5432f;
        ownStringBuilder.append(f);
        stringBuilder.append(f);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        int i = 56432156;
        ownStringBuilder.append(i);
        stringBuilder.append(i);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        long l = 543256L;
        ownStringBuilder.append(l);
        stringBuilder.append(l);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        Object obj = new Object();
        ownStringBuilder.append(obj);
        stringBuilder.append(obj);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.append(string1);
        stringBuilder.append(string1);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        StringBuffer stringBuffer = new StringBuffer(string1);
        ownStringBuilder.append(stringBuffer);
        stringBuilder.append(stringBuffer);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        int codePoint = 45;
        ownStringBuilder.appendCodePoint(codePoint);
        stringBuilder.appendCodePoint(codePoint);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        assertEquals(
            ownStringBuilder.capacity(),
            stringBuilder.capacity()
        );
        assertEquals(
            ownStringBuilder.charAt(5),
            stringBuilder.charAt(5)
        );
        assertEquals(
            ownStringBuilder.codePointAt(5),
            stringBuilder.codePointAt(5)
        );
        assertEquals(
            ownStringBuilder.codePointBefore(5),
            stringBuilder.codePointBefore(5)
        );
        assertEquals(
            ownStringBuilder.codePointCount(2, 5),
            stringBuilder.codePointCount(2, 5)
        );
        assertEquals(
            ownStringBuilder.compareTo(new OwnStringBuilder("       ")),
            stringBuilder.compareTo(new StringBuilder("       "))
        );
        assertEquals(
            ownStringBuilder.delete(5, 9).toString(),
            stringBuilder.delete(5, 9).toString()
        );
        assertEquals(
            ownStringBuilder.deleteCharAt(5).toString(),
            stringBuilder.deleteCharAt(5).toString()
        );
        assertEquals(
            ownStringBuilder.indexOf("dd"),
            stringBuilder.indexOf("dd")
        );
        assertEquals(
            ownStringBuilder.indexOf("rt", 10),
            stringBuilder.indexOf("rt", 10)
        );

        ownStringBuilder.insert(offset, b);
        stringBuilder.insert(offset, b);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.insert(offset, c);
        stringBuilder.insert(offset, c);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.insert(offset, string);
        stringBuilder.insert(offset, string);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        int index = 2;
        ownStringBuilder.insert(index, string, offset, len);
        stringBuilder.insert(index, string, offset, len);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.insert(offset, (CharSequence)string1);
        stringBuilder.insert(offset, (CharSequence)string1);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.insert(offset, (CharSequence)string1, start, end);
        stringBuilder.insert(offset, (CharSequence)string1, start, end);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.insert(offset, d);
        stringBuilder.insert(offset, d);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.insert(offset, f);
        stringBuilder.insert(offset, f);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.insert(offset, i);
        stringBuilder.insert(offset, i);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.insert(offset, l);
        stringBuilder.insert(offset, l);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.insert(offset, obj);
        stringBuilder.insert(offset, obj);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.insert(offset, string1);
        stringBuilder.insert(offset, string1);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        assertEquals(
            ownStringBuilder.lastIndexOf(string1),
            stringBuilder.lastIndexOf(string1)
        );
        assertEquals(
            ownStringBuilder.lastIndexOf(string1, ownStringBuilder.capacity() - string1.length() * 2),
            stringBuilder.lastIndexOf(string1, stringBuilder.capacity() - string1.length() * 2)
        );
        assertEquals(
            ownStringBuilder.length(),
            stringBuilder.length()
        );
        assertEquals(
            ownStringBuilder.offsetByCodePoints(index, codePoint),
            stringBuilder.offsetByCodePoints(index, codePoint)
        );

        ownStringBuilder.replace(start, end, string1);
        stringBuilder.replace(start, end, string1);
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        ownStringBuilder.reverse();
        stringBuilder.reverse();
        assertEquals(
            ownStringBuilder.toString(),
            stringBuilder.toString()
        );

        assertEquals(
            ownStringBuilder.subSequence(start, end),
            stringBuilder.subSequence(start, end)
        );
        assertEquals(
            ownStringBuilder.substring(start),
            stringBuilder.substring(start)
        );
        assertEquals(
            ownStringBuilder.substring(start, end),
            stringBuilder.substring(start, end)
        );
    }

    @Test
    void testThrowException() {
        OwnStringBuilder ownStringBuilder = new OwnStringBuilder();
        assertThrows(
            NullPointerException.class,
            ownStringBuilder::undo
        );

        ownStringBuilder.append(5);
        ownStringBuilder.undo();
        assertThrows(
            NullPointerException.class,
            ownStringBuilder::undo
        );
    }
}
