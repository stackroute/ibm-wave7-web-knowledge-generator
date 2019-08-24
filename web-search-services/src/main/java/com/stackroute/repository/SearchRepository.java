package com.stackroute.repository;

import com.stackroute.Modals.CodeBeautify;
import com.stackroute.Modals.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends MongoRepository<Result, Integer> {
}
