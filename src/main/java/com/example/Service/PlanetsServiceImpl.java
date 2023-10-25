package com.example.Service;

import com.example.Model.Planets;
import com.example.Repo.PlanetsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlanetsServiceImpl implements PlanetsService {
    @Autowired
    private PlanetsRepo planetsRepo;
    @Override
    public List<Planets> getPlanets(){
        return planetsRepo.findAll();
    }
}
