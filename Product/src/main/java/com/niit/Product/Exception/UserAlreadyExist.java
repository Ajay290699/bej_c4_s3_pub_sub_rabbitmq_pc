package com.niit.Product.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "User is Already Exist")
public class UserAlreadyExist extends Exception{
}
