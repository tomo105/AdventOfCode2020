package com.exmaple.mypackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day5 {

    public static void main(String[] args) {
        String filename = "day5.txt";
        ArrayList<String> list = getPasswords(filename);
        greatestIDNumber(list);

    }

    public static void greatestIDNumber(ArrayList<String> list) {
        int res = Integer.MIN_VALUE;
        ArrayList<Integer> id = new ArrayList<>();
        int value = 128;
        for (String line : list) {
            int row = 0;
            int col = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'B' && line.length() - 3 < 10) {
                    row += value / (Math.pow(2, i + 1));
                } else if (line.charAt(i) == 'R') {
                    col += value / (Math.pow(2, i - 2));
                }
            }
            id.add(row * 8 + col);
            if (res < row * 8 + col) {
                res = row * 8 + col;

            }

        }
        System.out.println("sum of row *8 + col = " + res);
        findYourSeat(id);
    }

    private static void findYourSeat(ArrayList<Integer> id) {
        Collections.sort(id);
        int min = id.get(0);
        for (int i = 0; i < id.size(); i++) {
            if (min - id.get(i) != 0) {
                System.out.println(id.get(i - 1) + " " + id.get(i) + " so you seat is  between!");
                break;
            }
            min++;
        }
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
