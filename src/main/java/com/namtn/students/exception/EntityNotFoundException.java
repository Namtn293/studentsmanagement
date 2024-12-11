package com.namtn.students.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String mesage){
        super(mesage);
    }
}
