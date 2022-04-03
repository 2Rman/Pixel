package app.dao;

import app.entity.Entity;

import java.util.List;

/**
 * Абстракный класс, для реализации работы с БД посредством DAO
 *
 * @param <E> сущность, наследованная от абстрактного класса сущности
 * @param <K> id в таблице БД
 */

public interface IAbstractDAO<K, E extends Entity> {

    List<E> getALL();
    E getById(K id);
    boolean create(E entity);
    boolean update(E entity);
    boolean delete(E entity);
}
