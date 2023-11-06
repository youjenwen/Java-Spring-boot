package com.example.demo;

import com.example.Model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // User Object 轉 json
        User user1 = new User(1243, "Johnnnn");
        String json = objectMapper.writeValueAsString(user1);
        System.out.println("json: " + json);

        // json 轉 User Object
        User user2 = objectMapper.readValue(json, User.class);  // 使用objectMapper讀取JSON

        // List<User> 轉 json
        List<User> ulist = new ArrayList<>();
        User user4 = new User(123, "John");
        ulist.add(user4);
        ulist.add(user2);
        String ujson = objectMapper.writeValueAsString(ulist);
        System.out.println("ujson: " + ujson);

        // json 轉 List<User>
        List<User> urlist = objectMapper.readValue(ujson, new TypeReference<List<User>>() {
        });

        // Map<String, User> 轉 json
        HashMap<String, User> umap = new HashMap<>();
        User user3 = new User(123, "John");
        umap.put("userInfo", user3);
        String mjson2 = objectMapper.writeValueAsString(umap);

        System.out.println("mjson2: " + mjson2);

        // json 轉 Map<String, User>
        Map<String, User> urMap = objectMapper.readValue(mjson2, new TypeReference<Map<String, User>>() {
        });
    }
}

