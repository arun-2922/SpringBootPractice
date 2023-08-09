package com.evlpract.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.evlpract.user.Users;

public interface WorkersRepo extends MongoRepository<Users, Integer>{
}
