package ru.itis.Exceptions;

public class WrongFileTypeException extends RuntimeException {
    public WrongFileTypeException(String message) {
        super(message);
    }
}
