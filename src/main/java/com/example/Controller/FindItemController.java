package com.example.Controller;

import com.example.Model.FindItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@RestController
@RequestMapping("/api")
public class FindItemController {
    private static final String FILENAME = "C:\\Users\\jouven.liao\\Desktop\\Harper.wen\\java\\items.txt";

    //用 get 方式取得欲搜尋詞組
    @GetMapping("/findItem")
    public FindItem getFindItem(@RequestParam(value = "name") String itemName) {
        FindItem findItem = new FindItem(); //帶入findItem module

        //讀取文件
        BufferedReader reader;
        List<String> conformProducts = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(FILENAME));
            String line;
            StringBuilder longStr = new StringBuilder();

            //每行文字去掉空格，然後串成 長字串
            while ((line = reader.readLine()) != null) { //每行進行
                String str = line.replaceAll(" ", "");
                longStr.append(str);
            }

            //進入比對
            conformProducts = kmpSearch(String.valueOf(longStr), itemName);

            reader.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

        findItem.setCount(conformProducts.size());
        findItem.setItemList(conformProducts);
        return findItem;
    }

    public static List<String> kmpSearch(String text, String pattern) {

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

        List<String> currentSameWorld = new ArrayList<>();
        List<String> samePatternList = new ArrayList<>();
        while (textIndex < text.length()) {
            int x = textIndex;
            int y = textIndex - 1;
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                //pattern index 跑完歸零後回來要將存起來比對用的陣列清空
                if (patternIndex == 0) {
                    currentSameWorld.clear();

                    //往後
                    while (x < text.length()) {
                        if (String.valueOf(text.charAt(x)).equals(",")) {
                            break;
                        }
                        currentSameWorld.add(String.valueOf(text.charAt(x)));
                        x++;
                    }

                    //往前
                    while (y >= 0) {
                        if (String.valueOf(text.charAt(y)).equals(",")) {
                            break;
                        }
                        currentSameWorld.add(0, String.valueOf(text.charAt(y)));
                        y--;
                    }
                }
                patternIndex++;
                textIndex++;

            } else {
                if (patternIndex != 0) {
                    if(!String.valueOf(currentSameWorld.get(patternIndex)).equals(String.valueOf(pattern.charAt(patternIndex)))){
                        textIndex++;
                    }

                    patternIndex = next.get(patternIndex - 1);
                } else {
                    textIndex++;
                }
            }
            //如果patternIndex不歸0 會停在找到第一個符合子字串的位置
            //patternIndex 有跑到最後才會進來這裡
            if (textIndex < text.length() && patternIndex == pattern.length()) {
                String same = String.join("", currentSameWorld);
                samePatternList.add(same);
                patternIndex = 0;
            }
        }
        return samePatternList;
    }
}