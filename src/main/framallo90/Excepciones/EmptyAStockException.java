package com.framallo90.Excepciones;

public class EmptyAStockException extends RuntimeException{
    public EmptyAStockException(String message) {
        super(message);
    }
}
