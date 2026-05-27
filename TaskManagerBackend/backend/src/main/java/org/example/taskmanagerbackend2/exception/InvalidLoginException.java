package org.example.taskmanagerbackend2.exception;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException() {
        super("Wrong email or password");
    }
}

