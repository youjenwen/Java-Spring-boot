package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Search {
    public static void main(String[] args) {
        File doc = new File("C:\\Users\\jouven.liao\\Desktop\\Harper.wen\\java\\items.txt");
        String[] strArray = new String[0];
        try (FileInputStream fis = new FileInputStream(doc)) {
            InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);
            String line;
            StringBuilder longStr = new StringBuilder();
            while ((line = br.readLine()) != null) { //每行進行
                String str = line.replaceAll(" ", "");
                longStr.append(str);
            }

            strArray = longStr.toString().split(",");
            System.out.println(Arrays.toString(strArray));
            br.close();
            reader.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

        Scanner scan = new Scanner(System.in);
        String keyword;
        System.out.println("Enter keyword: ");
        keyword = scan.nextLine();
        //取得keyword 丟進products裡去尋找
        findProducts(strArray, keyword);
    }

    static void findProducts(String[] items, String itemName) {
        Stack<String> conformProducts = new Stack<String>();
        System.out.println("item name: " + itemName);
        try {
            for (String item : items) { // improve readability:  for (int i = 0; i < items.size(); i++) {
                if (item.contains(itemName)) {
                    conformProducts.push(item);
                }
            }
            if (!conformProducts.isEmpty()) {
                System.out.println("find it! " + conformProducts);
            } else {
                System.out.println("Sorry, no conform products");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Index is out of bounds.");
        }


    }
}
