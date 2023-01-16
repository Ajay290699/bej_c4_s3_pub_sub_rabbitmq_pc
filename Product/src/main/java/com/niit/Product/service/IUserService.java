package com.niit.Product.service;

import com.niit.Product.Exception.ProductNotFound;
import com.niit.Product.Exception.UserAlreadyExist;
import com.niit.Product.Exception.UserNotFound;
import com.niit.Product.domain.Product;
import com.niit.Product.domain.User;

import java.util.List;

public interface IUserService {

    public List<User> getAllUser();
    public User addUser(User user) throws UserAlreadyExist;
    public String deleteUser(String id) throws UserNotFound;
    public List<User> findAllByProductName(String productName) throws ProductNotFound;

    public User saveUserProductToList(Product product, String email) throws UserNotFound;
   public List<Product> getAllUserProduct(String email) throws UserNotFound;
}
