package com.example.Controller.Hamaya;

import com.example.Model.Hamaya.Brand;
import com.example.Repo.Hamaya.BrandRepo;
import com.example.Service.Hamaya.BrandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hamaya")
public class BrandController {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private BrandRepo brandRepo;
    @Autowired
    private BrandService brandService;

    @GetMapping("/brand")
    public List<Brand> fetchBrandList() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(brandService.fetchBrandList());
        System.out.println("json: " + json);
        return brandService.fetchBrandList();
    }

    @GetMapping("/brand/{id}")
    public Brand fetchBrandId(@PathVariable long id) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(brandService.fetchBrandId(id));
        System.out.println("json: " + json);
        return brandService.fetchBrandId(id);
    }

    @PostMapping("/brand")
    public Brand saveNewBrand(@RequestBody Brand brand) {
        List<Brand> searchBrand = brandRepo.findByName(brand.getName());
        if (searchBrand.isEmpty()) {
            return brandService.saveNewBrand(brand);
        } else {
            return searchBrand.get(0);
        }
    }

    @PutMapping("/brand/{id}")
    public Brand updateBrand(@PathVariable("id") Long id, @RequestBody Brand brand) {
        return brandService.updateBrandById(id, brand);
    }

    @DeleteMapping("/brand/{id}")
    public String deleteBrand(@PathVariable("id") Long id) {
        return brandService.deleteBrandById(id);
    }

}
