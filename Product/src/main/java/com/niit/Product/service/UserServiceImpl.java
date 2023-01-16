package com.niit.Product.service;

import com.niit.Product.Exception.ProductNotFound;
import com.niit.Product.Exception.UserAlreadyExist;
import com.niit.Product.Exception.UserNotFound;
import com.niit.Product.config.ProductDTO;
import com.niit.Product.domain.Product;
import com.niit.Product.domain.User;
import com.niit.Product.proxy.UserProxy;
import com.niit.Product.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements IUserService{

    UserRepository userRepository;
  UserProxy userProxy;
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;
@Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy, RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) throws UserAlreadyExist {
        if(userRepository.findById(user.getEmail()).isEmpty()){
            userProxy.saveData(user);
            return userRepository.save(user);
        }
        throw new UserAlreadyExist();
    }

    @Override
    public String deleteUser(String id) throws UserNotFound {
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFound();
        }
        userRepository.deleteById(id);
        return"User Deleted Successfully";
    }

    @Override
    public List<User> findAllByProductName(String productName) throws ProductNotFound {
        if(userRepository.findAllByProductName(productName).isEmpty()){
            throw new ProductNotFound();
        }
        return userRepository.findAllByProductName(productName);
    }

    @Override
    public User saveUserProductToList(Product product, String email) throws UserNotFound {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }
        // user present
        User result=userRepository.findById(email).get();
        if(result.getProduct()!=null) {
            result.getProduct().add(product);
        }else{
            result.setProduct(new ArrayList<>());
            result.getProduct().add(product);
        }
        userRepository.save(result);
        return result;
    }

    @Override
    public List<Product> getAllUserProduct(String email) throws UserNotFound {
       if(userRepository.findById(email).isEmpty()){
           throw new UserNotFound();
       }
       List<Product> allProduct=userRepository.findById(email).get().getProduct();
List<Product> notViewed=new ArrayList<>();
for(Product products:allProduct){
    if(!products.isViewed()){
notViewed.add(products);
    }
}
        JSONObject jsonObject=new JSONObject();
jsonObject.put("productList",notViewed);
jsonObject.put("email",email);
        ProductDTO productDTO=new ProductDTO();
rabbitTemplate.convertAndSend(directExchange.getName(),"product_routing",productDTO);
return allProduct;

    }
}

