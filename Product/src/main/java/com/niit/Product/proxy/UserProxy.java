package com.niit.Product.proxy;

import com.niit.Product.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "User-Server",url = "localhost:8082")
public interface UserProxy {
@PostMapping("api/userService/register")
    public ResponseEntity<?> saveData(@RequestBody User user);
}
