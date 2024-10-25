package com.cjc.movers_and_packers.movers_and_packers.exceptions;

public class PasswordRequiredException extends RuntimeException{

    public PasswordRequiredException(String message){
        super(message);
    }
}
