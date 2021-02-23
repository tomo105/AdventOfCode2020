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
        System.out.println(findTwoIntegerToSum2020(arrayList));
        System.out.println(findThreeIntegerToSum2020(arrayList));
    }

    public static int findTwoIntegerToSum2020(ArrayList<Integer> array) {
        /*
        In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.
         */
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

    public static int findThreeIntegerToSum2020(ArrayList<Integer> array) {
        /*
        Using the above example again, the three entries that sum to 2020 are 979, 366, and 675. Multiplying them together produces the answer, 241861950.
        In your expense report, what is the product of the three entries that sum to 2020?
   */
        for (int i : array) {
            for (int j : array) {
                for (int k : array) {
                    if (i + j + k == 2020) {
                        System.out.println("Two numbers to sum up to 2020 are: " + i + ", " + j + " and " + k);
                        return (i * j * k);
                    }
                }
            }
        }
        return -1;
    }


}
