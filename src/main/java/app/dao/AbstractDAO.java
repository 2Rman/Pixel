package app.dao;

import app.entity.Entity;

import java.util.List;

/**
 * Абстрактный класс, для реализации работы с БД посредством DAO
 *
 * @param <E> сущность, наследованная от абстрактного класса сущности
 */
public interface AbstractDAO<E extends Entity> {

    List<E> getAll(String id);
    E getById(String id);
    int create(E entity);
    boolean update(E entity);
    boolean delete(E entity);
}
