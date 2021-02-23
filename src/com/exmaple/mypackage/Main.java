package com.exmaple.mypackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        ArrayList<Integer> arrayList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("liczby.txt"))) {
            while (br.ready()) {
                arrayList.add(Integer.parseInt(br.readLine()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(arrayList);
        System.out.println(findIntegerToSum2020(arrayList));

    }

    public static int findIntegerToSum2020(ArrayList<Integer> array) {
        for (int i : array) {
            for (int j : array) {
                if (i + j == 2020) {
                    System.out.println("Two numbers to sum up to 2020 are: " + i + " and " + j);
                    return (i * j);
                }
            }
        }

        return -1;
    }

}
