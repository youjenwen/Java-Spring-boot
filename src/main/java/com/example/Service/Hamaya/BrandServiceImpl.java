package com.example.Service.Hamaya;

import com.example.Model.Hamaya.Brand;
import com.example.Repo.Hamaya.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepo brandRepo;

    @Override
    public List<Brand> fetchBrandList() {
        return brandRepo.findAll();
    }

    @Override
    public Brand fetchBrandId(Long id) {
        Optional<Brand>  brand = brandRepo.findById(id);
        return brand.orElse(null);
    }

    @Override
    public Brand saveNewBrand(Brand brand) {
        return brandRepo.save(brand);
    }



    @Override
    public Brand updateBrandById(Long id, Brand brand){
        Optional<Brand> brandId = brandRepo.findById(id);

        if(brandId.isPresent()){
            Brand originalBrand = brandId.get();
            if(brand.getName() != null && !Objects.equals(brand.getName(), "")){
                originalBrand.setName(brand.getName());
            }
            return brandRepo.save(originalBrand);
        }
        return null;
    }

    @Override
    public String deleteBrandById(Long id){
        if(brandRepo.findById(id).isPresent()){
            brandRepo.deleteById(id);
            return "Brand deleted successfully";
        }
        return "No such brand in the database";
    }

}
