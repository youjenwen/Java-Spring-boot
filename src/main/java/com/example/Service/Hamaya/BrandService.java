package com.example.Service.Hamaya;

import com.example.Model.Hamaya.Brand;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BrandService {
    List<Brand> fetchBrandList() throws JsonProcessingException;

    Brand fetchBrandId(Long id);

    Brand saveNewBrand(Brand brand);

    Brand updateBrandById(Long id, Brand brand);

    String  deleteBrandById(Long id);
}
