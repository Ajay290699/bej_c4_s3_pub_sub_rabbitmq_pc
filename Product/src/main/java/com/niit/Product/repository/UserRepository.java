package com.niit.Product.repository;

import com.niit.Product.Exception.ProductNotFound;
import com.niit.Product.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query("{'product.productName':{$in:[?0]}}")
    public List<User> findAllByProductName(String productName) throws ProductNotFound;

}
