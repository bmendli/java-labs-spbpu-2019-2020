package semesters.third.lab1.task2;

import java.util.Random;

public class SortParser implements Parser {

    private static Random random = new Random();

    public String[] parse(String sequence) {
        String[] fragments = new String[sequence.length() % 3 == 0 ? sequence.length() / 3 : sequence.length() / 3 + 1];
        for (int i = 0; i < fragments.length - 1; i++) {
            fragments[i] = replace(sequence.substring(i * 3, i * 3 + 3));
        }
        fragments[fragments.length - 1] = replace(
            sequence.substring(
                (fragments.length - 1) * 3,
                (fragments.length - 1) * 3 + sequence.length() - (fragments.length - 1) * 3
            )
        );

        return sort(fragments);
    }

    private String replace(String str) {
        char c;
        switch (str.length()) {
            case 3 : {

                do {
                    c = (char) (random.nextInt(26) + 'a');
                } while ((c == str.charAt(0)) || (c == str.charAt(2)));
                return str.replace(str.charAt(1), c);
            }
            case 2 : {
                do {
                    c = (char) (random.nextInt(26) + 'a');
                } while ((c == str.charAt(0)));
                return str.replace(str.charAt(1), c);

            }
            case 1 : {
                return str;
            }
            default : {
                throw new IllegalArgumentException("String is wrong");
            }
        }
    }

    private String[] sort(String[] fragments) {
        for (int i = 0; i < fragments.length - 1; i++) {
            for (int j = i + 1; j < fragments.length; j++) {
                if (fragments[i].compareTo(fragments[j]) > 0) {
                    String tmp = fragments[i];
                    fragments[i] = fragments[j];
                    fragments[j] = tmp;
                }
            }
        }
        return fragments;
    }

    public static void main(String[] args) {
        SortParser parser = new SortParser();
        String[] result = parser.parse("ahst']js15rt]j2sr6[ thstg4hsth4sht");
        for (String s : result) {
            System.out.println(s);
        }
    }
}
