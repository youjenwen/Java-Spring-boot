package com.example.Controller;

import com.example.Model.FindItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ArrayKMPController {
    @Value("${FILEPATH}")
    String FILEPATH;

    @GetMapping("/arraykmp")
    public FindItem getArrayKmp(@RequestParam(value = "name") String itemName) {
        FindItem findItem = new FindItem(); //帶入findItem module
        //讀取文件
        String line;
        StringBuilder longStr = new StringBuilder();
        List<String> conformProducts = new ArrayList<>();
        List<String> strArray = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILEPATH));

            //每行文字去掉空格，然後串成 長字串
            while ((line = reader.readLine()) != null) { //每行進行
                String str = line.replaceAll(" ", "");
                longStr.append(str);
            }

            //字串切成陣列
            strArray = List.of(longStr.toString().split(","));

            reader.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

        //利用陣列進行比對
        int i = 0;
        while (i < strArray.size()) {
            if (!Objects.equals(kmpSearch(strArray.get(i), itemName), "")){
                conformProducts.add(kmpSearch(strArray.get(i), itemName));
            }

            i++;
        }

        findItem.setCount(conformProducts.size());
        findItem.setItemList(conformProducts);
        return findItem;
    }

    public static String kmpSearch(String text, String pattern) {

        //計算 next
        List<Integer> next = new ArrayList<>(List.of(0));
        int prefix_len = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(prefix_len) == pattern.charAt(i)) {
                prefix_len += 1;
                next.add(prefix_len);
                i += 1;
            } else {
                if (prefix_len == 0) {
                    next.add(0);
                    i += 1;
                } else {
                    prefix_len = next.get(prefix_len - 1);
                }
            }
        }

        //匹配
        int textIndex = 0;
        int patternIndex = 0;
        String findIt = "";

        while (textIndex < text.length()) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                patternIndex++;
                textIndex++;

            } else {
                if (patternIndex != 0) {
                    patternIndex = next.get(patternIndex - 1);
                } else {
                    textIndex++;
                }
            }

            if (patternIndex == pattern.length()) {
                findIt = text;
                patternIndex = 0;
            }
        }
        return findIt;
    }
}

