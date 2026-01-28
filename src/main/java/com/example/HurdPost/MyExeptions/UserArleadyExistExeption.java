package com.example.HurdPost.MyExeptions;

public class UserArleadyExistExeption extends RuntimeException{

    public UserArleadyExistExeption(String message){
        super(message);
    }

    public UserArleadyExistExeption(String message, Throwable cause){
        super(message, cause);
    }

}
