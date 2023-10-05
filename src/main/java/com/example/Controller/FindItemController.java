package com.example.Controller;

import com.example.Model.FindItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api")
public class FindItemController {
    //用 get 方式取得欲搜尋詞組
    @GetMapping("/findItem")
    public FindItem getFindItem(@RequestParam(value = "name") String itemName) {
        FindItem findItem = new FindItem();

        //讀取文件
        File doc = new File("C:\\Users\\jouven.liao\\Desktop\\Harper.wen\\java\\items.txt");
        String[] strArray = new String[0];
        try (FileInputStream fis = new FileInputStream(doc)) {
            InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);
            String line;
            StringBuilder longStr = new StringBuilder();

            //每行文字去掉空格，然後串成長字串
            while ((line = br.readLine()) != null) { //每行進行
                String str = line.replaceAll(" ", "");
                longStr.append(str);
            }

            System.out.println(longStr);
            KMP(String.valueOf(longStr), itemName);
            //字串切成陣列
            strArray = longStr.toString().split(",");
            System.out.println(Arrays.toString(strArray));
            br.close();
            reader.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

        //陣列一個一個比對，找到存進 conformProducts 陣列
        List<String> conformProducts = new ArrayList<>();
        for (String item : strArray) {
            if (item.contains(itemName)) {
                conformProducts.add(item);
            }
        }
        findItem.setCount(conformProducts.size());
        findItem.setItemList(conformProducts);
        return findItem;
    }

    public static void KMP(String text, String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            System.out.println("The pattern occurs with shift 0");
        }

        if (text == null || (pattern != null ? pattern.length() : 0) > text.length()) {
            System.out.println("Pattern not found");
        }

        char[] chars = new char[0];
        if (pattern != null) {
            chars = pattern.toCharArray();
        }

        //next[i] 存儲下一個最佳部分匹配的索引
        int[] next = new int[(pattern != null ? pattern.length() : 0) + 1];
        for (int i = 1; i < (pattern != null ? pattern.length() : 0); i++) {
            int j = next[i];
            while (j > 0 && chars[j] != chars[i]) {
                j = next[j];
            }
            if (j > 0 || chars[j] == chars[i]) {
                next[i + 1] = j + 1;
            }
        }
        for (int i = 0, j = 0; i < (text != null ? text.length() : 0); i++) {
            if (j < pattern.length() && text.charAt(i) == pattern.charAt(j)) {
                if (++j == pattern.length()) {
                    System.out.println("The pattern occurs with shift " + (i - j + 1));
                }
            } else if (j > 0) {
                j = next[j];
                i--;
            }
        }
    }
}