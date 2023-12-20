package com.example.Model.Hamaya;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    // 無參數的建構子
    public Brand() {

    }

    // 帶參數的建構子，用於初始化所有欄位的值
    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 帶參數的建構子，用於初始化除主鍵外的欄位的值
    public Brand(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
