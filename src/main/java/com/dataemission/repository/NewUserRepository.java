package com.dataemission.repository;

import com.dataemission.entities.NewUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewUserRepository extends MongoRepository<NewUser,Integer> {
}
