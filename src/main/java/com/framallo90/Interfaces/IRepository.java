package com.framallo90.Interfaces;

public interface IRepository<T, ID> {
    void add(T object);
    void remove(ID id) throws Exception;
    void update(ID id) throws Exception;
    T find(ID id);
}
