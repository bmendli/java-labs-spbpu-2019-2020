package lab1.task2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SortParserTest {

    private static Parser parser;

    @BeforeAll
    static void createInstance() {
        parser = new SortParser();
    }

    @Test
    void testParse() {
        String temp = "123456789";
        String[] result = parser.parse(temp);
        assertEquals(
            result.length,
            temp.length() / 3
        );
        for (int i = 0; i < result.length; i++) {
            assertEquals(
                result[i].charAt(0),
                temp.charAt(i * 3)
            );
            assertNotEquals(
                result[i].charAt(1),
                temp.charAt(i * 3 + 1)
            );
            assertEquals(
                result[i].charAt(2),
                temp.charAt(i * 3 + 2)
            );
        }

        temp = "12345678";
        result = parser.parse(temp);
        assertEquals(
            result.length,
            temp.length() / 3 + 1
        );
        for (int i = 0; i < result.length; i++) {
            assertEquals(
                result[i].charAt(0),
                temp.charAt(i * 3)
            );
            assertNotEquals(
                result[i].charAt(1),
                temp.charAt(i * 3 + 1)
            );
            if (i == result.length - 1) {
                break;
            }
            assertEquals(
                result[i].charAt(2),
                temp.charAt(i * 3 + 2)
            );
        }

        temp = "1234567";
        result = parser.parse(temp);
        assertEquals(
            result.length,
            temp.length() / 3 + 1
        );
        for (int i = 0; i < result.length; i++) {
            assertEquals(
                result[i].charAt(0),
                temp.charAt(i * 3)
            );
            if (i == result.length - 1) {
                break;
            }
            assertNotEquals(
                result[i].charAt(1),
                temp.charAt(i * 3 + 1)
            );
            assertEquals(
                result[i].charAt(2),
                temp.charAt(i * 3 + 2)
            );
        }
    }

    @Test
    void testSorting() {
        String temp = "987654321";
        String[] result = parser.parse(temp);
        for (int i = 0; i < result.length; i++) {
            assertEquals(
                result[i].charAt(0),
                temp.charAt((3 - i - 1) * 3)
            );
            assertEquals(
                result[i].charAt(2),
                temp.charAt((3 - i - 1) * 3 + 2)
            );
        }
    }
}
