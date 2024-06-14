package com.framallo90.Interfaces;

public interface repo <T>{
    void add(T object);
    void remove(String id);
    void update(String id);
    T find(String id);
}
