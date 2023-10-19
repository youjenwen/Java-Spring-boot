package com.example.demo;

import com.example.Model.Store;
import com.example.Model.Student;
import me.paulschwarz.springdotenv.DotenvPropertySource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SpringBootHelloWorld {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        DotenvPropertySource.addToEnvironment(applicationContext.getEnvironment());
    }

    @RequestMapping("/")
    public String hello(){
        return "Hey, Spring Boot 的 Hello World";
    }

    @RequestMapping("/test")
    public String test(){
        System.out.println("Hi!");
        return "Hello World";
    }

    @RequestMapping("/user")
    public Student user(){
        Student student = new Student();
        student.setName("Judy");
        return student;
    }

    @RequestMapping("/product")
    public Store product(){
        Store store = new Store();
        List<String> list = new ArrayList<>();
        list.add("蘋果");
        list.add("橘子");
        store.setProductList(list);
        return store;
    }
}
