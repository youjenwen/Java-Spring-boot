package com.example.Controller;

import com.example.Model.FindItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
public class FindItemController {
    //用 get 方式取得欲搜尋詞組
    @GetMapping("/findItem")
    public FindItem getFindItem(@RequestParam(value = "name") String itemName) {
        FindItem findItem = new FindItem();

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

        List<String> conformProducts = new ArrayList<>();
        for (String item : strArray) {
            if (item.contains(itemName)) {
                conformProducts.add(item);
            }
        }
        findItem.setItemList(conformProducts);
        return findItem;
    }
}
