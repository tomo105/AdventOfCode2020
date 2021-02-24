package com.exmaple.mypackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4 {
    public static void main(String[] args) {

        String filename = "day4.txt";
        ArrayList<String> list = getPassports(filename);
        System.out.println(validPassportsRequiredFields(list));


    }

    /*
    byr (Birth Year) - four digits; at least 1920 and at most 2002.
iyr (Issue Year) - four digits; at least 2010 and at most 2020.
eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
hgt (Height) - a number followed by either cm or in:
If cm, the number must be at least 150 and at most 193.
If in, the number must be at least 59 and at most 76.
hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
pid (Passport ID) - a nine-digit number, including leading zeroes.
cid (Country ID) - ignored, missing or not.
*/
    private static int validPassportsRequiredFields(ArrayList<String> passports) {
        int count = 0;
        int cnt = 0;
        for (String pass : passports) {
            String[] data = pass.split(" ");
            if (data.length == 8 && validFieldsValue(data)) {
                count++;
            } else if (data.length == 7 && validFieldsValue(data) && !pass.contains("cid")) {
                count++;
            }
        }
        return count;
    }

    private static boolean validFieldsValue(String[] data) {

        for (String str : data) {
            String[] value = str.split(":");
            switch (value[0]) {
                case "byr":
                    if (Integer.parseInt(value[1]) > 2002 || Integer.parseInt(value[1]) < 1920)
                        return false;

                    break;
                case "iyr":
                    if (Integer.parseInt(value[1]) > 2020 || Integer.parseInt(value[1]) < 2010)
                        return false;

                    break;

                case "eyr":
                    if (Integer.parseInt(value[1]) > 2030 || Integer.parseInt(value[1]) < 2020)
                        return false;
                    break;

                case "hgt":
                    int height = Integer.parseInt(value[1].replaceAll("\\D+", ""));
                    if (!value[1].contains("cm") && !value[1].contains("in"))
                        return false;

                    else if (value[1].contains("cm") && (height < 150 || height > 193))
                        return false;

                    else if (value[1].contains("in") && (height < 59 || height > 76))
                        return false;
                    else
                        break;

                case "hcl":
                    if (value[1].charAt(0) != '#' && value[1].length() != 7)
                        return false;
                    else {
                        for (int i = 1; i < value[1].length(); i++)
                            if (!Character.isLetterOrDigit(value[1].charAt(i)))
                                return false;
                        break;
                    }

                case "ecl":
                    if ((!value[1].equals("amb")) && (!value[1].equals("blu")) && (!value[1].equals("brn")) && (!value[1].equals("gry"))
                            && (!value[1].equals("grn")) && (!value[1].equals("hzl")) && (!value[1].equals("oth")))
                        return false;

                    break;
                case "pid":
                    if (value[1].length() == 9 && value[1].matches("[0-9]+"))
                        break;
                    else
                        return false;
                default:
                    break;
            }
        }

        return true;
    }

    private static ArrayList<String> getPassports(String path) {
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
