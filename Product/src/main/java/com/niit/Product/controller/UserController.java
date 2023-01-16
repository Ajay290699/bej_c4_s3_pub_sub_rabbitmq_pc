package com.niit.Product.controller;

import com.niit.Product.Exception.ProductNotFound;
import com.niit.Product.Exception.UserAlreadyExist;
import com.niit.Product.Exception.UserNotFound;
import com.niit.Product.domain.Product;
import com.niit.Product.domain.User;
import com.niit.Product.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productService")
public class UserController {

    IUserService iUserService;
    private ResponseEntity<?> responseEntity;
@Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }
    @GetMapping("/user")
    public ResponseEntity<?> getAllData(){
        return new ResponseEntity<>(iUserService.getAllUser(), HttpStatus.OK);
    }
    @PostMapping("/user")
    public ResponseEntity<?>addData(@RequestBody User user) throws UserAlreadyExist {
        return new ResponseEntity<>(iUserService.addUser(user),HttpStatus.CREATED);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?>deleteData(@PathVariable String id) throws UserNotFound {
        return new ResponseEntity<>(iUserService.deleteUser(id),HttpStatus.OK);
    }


    @GetMapping("/users/{productName}")
    public ResponseEntity<?>getByArtistName(@PathVariable String productName){
        try {
            List<User> productList=iUserService.findAllByProductName(productName);
            return new ResponseEntity<>(productList,HttpStatus.FOUND);
        }catch (ProductNotFound e){
            throw new RuntimeException(e);
        }
    }
@PostMapping("/product/{email}")
public ResponseEntity<?>saveUserProductToList(@RequestBody Product product,@PathVariable String email) throws UserNotFound{
try{
    responseEntity= new ResponseEntity<>(iUserService.saveUserProductToList(product,email),HttpStatus.CREATED);
} catch (UserNotFound e) {
    throw new UserNotFound();
}
return responseEntity;
}
@GetMapping("/product/{email}")
    public ResponseEntity<?>getAllUserProducts(@PathVariable  String email)throws UserNotFound{
return new ResponseEntity<>(iUserService.getAllUserProduct(email),HttpStatus.OK);
}



}
