package com.exmaple.mypackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07
{
    private Map<String, List<BagInfo>> bags;
    private Map<String, Boolean> bagCache;

    public static void main(String[] args) {
        Day07 day07 = new Day07();
        day07.solve(getLines("day7.txt"));
    }
    public void solve(List<String> input)
    {
        bags = new HashMap<>();
        bagCache = new HashMap<>();
        for(String s : input)
        {
            String[] split1 = s.substring(0, s.length() - 1).split("bags contain");
            String outterBag = split1[0].trim();
            String[] innerbags = split1[1].split(",");
            List<BagInfo> bagInfos = new ArrayList<>();
            for(String innerbag : innerbags)
            {
                innerbag = innerbag.replace("bags", "").replace("bag", "").trim();
                int index = innerbag.trim().indexOf(" ");
                String quantityStr = innerbag.substring(0, index);
                if(!quantityStr.equalsIgnoreCase("no"))
                {
                    BagInfo bagInfo = new BagInfo();
                    bagInfo.quantity = Integer.parseInt(quantityStr);
                    bagInfo.bagName = innerbag.substring(index + 1);
                    bagInfos.add(bagInfo);
                }
            }

            bags.put(outterBag, bagInfos);
        }
        System.out.print("Bags Parsed, ");
        int count = 0;
        for(String bag : bags.keySet())
        {
            if(isGoldBagInBag(bag))
            {
                count++;
                System.out.println(count);
            }
        }

    }

    private boolean isGoldBagInBag(String bag)
    {
        if(bagCache.containsKey(bag))
            return bagCache.get(bag);

        for(BagInfo subBag : bags.get(bag))
        {
            if(subBag.bagName.equalsIgnoreCase("shiny gold"))
            {
                bagCache.put(bag, true);
                return true;
            }
        }

        for(BagInfo subBag : bags.get(bag))
        {
            if(isGoldBagInBag(subBag.bagName))
            {
                bagCache.put(bag, true);
                return true;
            }
        }

        bagCache.put(bag, false);
        return false;
    }

    private int countBagsInGoldBag(String bag)
    {
        List<BagInfo> bagList = bags.get(bag);
        int count = 1;
        for(BagInfo bagInfo : bagList)
            count += (bagInfo.quantity * countBagsInGoldBag(bagInfo.bagName));

        return count;
    }

    public static class BagInfo
    {
        public int quantity;
        public String bagName;
    }

    private static ArrayList<String> getLines(String path) {
        ArrayList<String> arrayList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                arrayList.add(line);
                line = br.readLine();

            }
            //  arrayList.add(str);                                // second solution of eof problem


        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;


    }
}