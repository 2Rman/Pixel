package app.dao;

import app.entity.Entity;

import java.sql.Connection;
import java.util.List;

/**
 * Абстракный класс, для реализации работы с БД посредством DAO
 *
 * @param <E> сущность, наследованная от абстрактного класса сущности
 * @param <K> id в таблице БД
 */

public interface AbstractController<K, E extends Entity> {

    List<E> getALL();
    E getById(K id);
    boolean create(E entity);
    boolean update(E entity);
    boolean delete(E entity);

    /**
     * Метод для закрытия соединения
     *
     * @param connection соединение для закрытия после выполнения всех запросов
     *                   пока непонятно насколько он будет нужен, но на всякий случай пускай будет здесь
     */
}
