package dao;

import java.util.List;

public interface GenericDAO<T, E>{
    void create(T obj);

    T getById(E id);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();
}
