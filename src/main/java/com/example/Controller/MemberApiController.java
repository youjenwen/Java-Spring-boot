package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //POST 方式
    @RequestMapping("/api/member")
    public MemberAccount member(@RequestBody MemberAccount memberAccount){
        System.out.println(memberAccount.getId());
        System.out.println(memberAccount.getEmail());
        return memberAccount;
    }

    //header
    @RequestMapping("/api/header")
    public String header(@RequestHeader String info){
        return info;
    }

    //path variable
    @RequestMapping("/api/member/{memberId}")
    public String member(@PathVariable String memberId){
        return memberId;
    }
}
