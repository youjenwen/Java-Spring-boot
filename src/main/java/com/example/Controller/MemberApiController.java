package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.MemberAccount;

@RestController
public class MemberApiController {
    @Autowired
    MemberAccount memberAccount;

    @GetMapping("/memberApi/memberTest")
    public MemberAccount memberTest() {
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setAddress("台北市");
        memberAccount.setCellphone("0912345678");
        memberAccount.setEmail("test@example.com");
        memberAccount.setId(1);
        memberAccount.setPassword("12345");
        return memberAccount;
    }
}
