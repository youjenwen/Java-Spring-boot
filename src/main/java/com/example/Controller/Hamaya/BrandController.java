package com.example.Controller.Hamaya;

import com.example.Model.Hamaya.Brand;
import com.example.Repo.Hamaya.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hamaya")
public class BrandController {
    @Autowired
    private BrandRepo brandRepo;

    @GetMapping("/brand")
    public List<Brand> getAllBrand(){
        return brandRepo.findAll();
    }

}
