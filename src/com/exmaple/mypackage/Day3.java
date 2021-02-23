package com.exmaple.mypackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {
    public static void main(String[] args) {

        String filename = "day3.txt";
        ArrayList<String> forest = getMap(filename);
        System.out.println(getTreesNumber(forest, 3));

        System.out.println(getTreesNumber_part2(forest));
    }


    private static int getTreesNumber(ArrayList<String> forest, int right) {
        int count = 0;
        int position = 0;
        for (String line : forest) {
            int length = line.length();
            if (line.charAt(position % length) == '#')
                count++;

            position += right;
        }

        return count;
    }

    private static int getTreesNumber_part2(ArrayList<String> forest) {
       /*
        Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left corner and traverse the map all the way to the bottom:

        Right 1, down 1.
        Right 3, down 1. (This is the slope you already checked.)
        Right 5, down 1.
        Right 7, down 1.
        Right 1, down 2.
        In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively; multiplied together, these produce the answer 336.

        What do you get if you multiply together the number of trees encountered on each of the listed slopes?

        Your puzzle answer was 2122848000.
                */
        int count_1 = 0;
        int count_2 = 0;
        int count_3 = 0;
        int count_4 = 0;
        int count_5 = 0;
        int position_1 = 0;
        int position_2 = 0;
        int position_3 = 0;
        int position_4 = 0;
        int position_5 = 0;
        int temp = 0;

        for (String line : forest) {
            int length = line.length();
            if (line.charAt(position_1 % length) == '#')
                count_1++;
            if (line.charAt(position_2 % length) == '#')
                count_2++;
            if (line.charAt(position_3 % length) == '#')
                count_3++;
            if (line.charAt(position_4 % length) == '#')
                count_4++;

            if (temp % 2 == 0) {
                if (line.charAt(position_5 % length) == '#') {
                    count_5++;
                }

                position_5 += 1;
            }


            position_1 += 1;
            position_2 += 3;
            position_3 += 5;
            position_4 += 7;
            temp++;
        }
        System.out.println(count_1 + " " + count_2 + " " + count_3 + " " + count_4 + " " + count_5);
        return count_1 * count_2 * count_3 * count_4 * count_5;
    }

    private static ArrayList<String> getMap(String path) {
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
