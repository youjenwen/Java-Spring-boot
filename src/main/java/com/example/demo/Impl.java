package com.example.demo;

interface MyInterface {
    void myMethod();
}

class MyClass implements MyInterface {
    @Override
    public void myMethod() {
        // 實作介面中的方法
        System.out.println("這是 MyInterface 中的方法實作。");
    }
}

public class Impl {
    public static void main(String[] args) {
        MyInterface obj = new MyClass();
        obj.myMethod();
    }
}
