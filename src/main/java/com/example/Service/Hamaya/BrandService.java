package com.example.Service.Hamaya;

import com.example.Model.Hamaya.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<Brand> fetchBrandList();

    Brand fetchBrandId(Long id);

    Brand saveNewBrand(Brand brand);

    Brand updateBrandById(Long id, Brand brand);

    String  deleteBrandById(Long id);
}
