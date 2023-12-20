package com.example.Controller;

import com.example.Service.RedisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisTestController {
    @Autowired
    private RedisTestService redisTestService;

    @PostMapping("/set/{key}/{value}")
    public void setData(@PathVariable("key") String key, @PathVariable("value") String value){
        redisTestService.saveData(key, value);
    }

    @GetMapping("/get/{key}")
    public String getData(@PathVariable String key){
        return redisTestService.getData(key);
    }
}
