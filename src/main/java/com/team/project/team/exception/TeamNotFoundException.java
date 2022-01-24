package com.team.project.team.exception;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(String message){
        super(message);
    }
}
