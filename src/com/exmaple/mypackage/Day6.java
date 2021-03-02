package com.exmaple.mypackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day6 {
    public static void main(String[] args) {

        String path = "day6.txt";
        validateAnswersDoNotCountDuplicatedAnswers(getAnswers(path));
        validateAnswersEveryPersonAnswerYes(getAnswers(path));

    }

    private static int validateAnswersDoNotCountDuplicatedAnswers(ArrayList<String> answers) {
        int cnt = 0;

        for (String s : answers) {
            s = s.replaceAll("\\s+", "");                       //remove empty space character !!!
            cnt += s.chars().distinct().count();
        }

        System.out.println("Result : " + cnt);
        return cnt;
    }

    private static int validateAnswersEveryPersonAnswerYes(ArrayList<String> answers) {
        int cnt = 0;

        for (String s : answers) {                                                                     //lines

            String[] tab = s.split(" ");
            String tmpStringPattern = tab[0];

            for (int i = 1; i < tab.length; i++) {                                                  // words
                char[] first_string_to_charTab = tmpStringPattern.toCharArray();
                tmpStringPattern = "";
                for (int j = 0; j < tab[i].length(); j++) {                                         // characters
                    for (char c : first_string_to_charTab) {                                        // check if specific character is on of our pattern character!!! if it it then we add it to pattern
                        if (tab[i].charAt(j) == c) {                                                // at beginning our pattern is whole first word
                            tmpStringPattern = tmpStringPattern.concat(String.valueOf(c));
                        }
                    }
                }
            }
            cnt += tmpStringPattern.length();
        }
        System.out.println("Result : " + cnt);
        return cnt;

    }

    private static ArrayList<String> getAnswers(String path) {
        ArrayList<String> arrayList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String str = "";
            String line = br.readLine();
            while (line != null) {
                if (line.isEmpty()) {
                    arrayList.add(str);
                    str = "";
                } else {
                    if (!str.isEmpty())
                        str = str.concat(" ");
                    str = str.concat(line);
                }

                line = br.readLine();
                //   if (line == null) {                        // cause last line is a eof not a empty line !!
                //     System.out.println(str);
                //
                //    }
            }
            arrayList.add(str);                                // second solution of eof problem


        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;


    }

}
