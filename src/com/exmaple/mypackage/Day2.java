package com.exmaple.mypackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {
    public static void main(String[] args) {
        String filename = "day2.txt";
        ArrayList<String> passwords = getPasswords(filename);
        System.out.println(getNumberOfValidPassword(passwords));

        System.out.println(getNumberOfValidPassword_part2(passwords));
    }


    public static int getNumberOfValidPassword(ArrayList<String> passwords) {
        /*
        For example, suppose you have the following list:

        1-3 a: abcde
        1-3 b: cdefg
        2-9 c: ccccccccc
        Each line gives the password policy and then the password. The password policy indicates the lowest and highest number of times a given letter must appear for
         the password to be valid. For example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.

        In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b, but needs at least 1. The first and third passwords
        are valid: they contain one a or nine c, both within the limits of their respective policies.

        How many passwords are valid according to their policies?
         */
        int count = 0;

        for (String password : passwords) {
            String[] str = password.split(" ");
            String[] range = str[0].split("-");
            char character = str[1].charAt(0);
            int char_count = 0;
            for (char ch : str[2].toCharArray()) {
                if (ch == character) {
                    char_count++;
                }
            }
            if (char_count >= Integer.parseInt(range[0]) && char_count <= Integer.parseInt(range[1]))
                count++;

        }


        return count;
    }

    public static int getNumberOfValidPassword_part2(ArrayList<String> passwords) {
        /*
       Given the same example list from above:

1-3 a: abcde is valid: position 1 contains a and position 3 does not.
1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
How many passwords are valid according to the new interpretation of the policies?
         */
        int count = 0;

        for (String password : passwords) {
            String[] str = password.split(" ");
            String[] range = str[0].split("-");
            char character = str[1].charAt(0);
            int char_count = 0;
//            for (char ch : str[2].toCharArray()) {
////                if (ch == character) {
////                    char_count++;
////                }
////            }

            if (str[2].charAt(Integer.parseInt(range[0]) - 1) == character && str[2].charAt(Integer.parseInt(range[1]) - 1) != character)
                count++;
            else if (str[2].charAt(Integer.parseInt(range[0]) - 1) != character && str[2].charAt(Integer.parseInt(range[1]) - 1) == character) {
                count++;
            }
        }


        return count;
    }

    private static ArrayList<String> getPasswords(String path) {
        ArrayList<String> arrayList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                arrayList.add(br.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}
