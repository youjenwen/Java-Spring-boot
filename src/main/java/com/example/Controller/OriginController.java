package com.example.Controller;

import com.example.Model.FindItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OriginController {
    @GetMapping("/origin")
    public FindItem getOrigin(@RequestParam(value = "name") String itemName) {
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
//            KMP(String.valueOf(longStr), itemName);
            //字串切成陣列
            strArray = longStr.toString().split(",");
            System.out.println(Arrays.toString(strArray));
            br.close();
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
}
