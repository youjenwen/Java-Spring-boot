package com.example.Repo.Hamaya;

import com.example.Model.Hamaya.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepo extends JpaRepository<Brand, Long> {
    List<Brand> findByName(String name);
}
