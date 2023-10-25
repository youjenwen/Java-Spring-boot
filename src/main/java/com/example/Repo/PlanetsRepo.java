package com.example.Repo;

import com.example.Model.Planets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetsRepo extends MongoRepository<Planets, String> {
}
