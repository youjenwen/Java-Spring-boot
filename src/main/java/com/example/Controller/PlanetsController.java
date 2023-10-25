package com.example.Controller;

import com.example.Model.Planets;
import com.example.Service.PlanetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
public class PlanetsController {
    @Autowired
    private PlanetsService planetsService;
    @GetMapping("/all")
    public List<Planets> getPlanets(){
        return planetsService.getPlanets();
    }
}
