package com.example.Controller.Hamaya;

import com.example.Model.Hamaya.Brand;
import com.example.Service.Hamaya.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hamaya")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/brand")
    public List<Brand> fetchBrandList() {
        return brandService.fetchBrandList();
    }

    @GetMapping("/brand/{id}")
    public Brand fetchBrandId(@PathVariable long id) {
        return brandService.fetchBrandId(id);
    }

    @PostMapping("/brand")
    public Brand saveNewBrand(@RequestBody Brand brand) {
        return brandService.saveNewBrand(brand);

    }

    @PutMapping("/brand/{id}")
    public Brand updateBrand(@PathVariable("id") Long id, @RequestBody Brand brand){
        return brandService.updateBrandById(id, brand);
    }

    @DeleteMapping("/brand/{id}")
    public String deleteBrand(@PathVariable("id") Long id){
        return brandService.deleteBrandById(id);
    }

}
