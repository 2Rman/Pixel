package app.entity;

import lombok.Data;

import java.util.UUID;

/**
 * Класс сущность, реализующий клиента со всеми соответствующими полями
 */
@Data
public class ClientEntity extends Entity {

    private String idClient;
    private String firstName;
    private String lastName;

    /**
     * Конструктор, создает сущность клиента по известным id, firstName, LastName
     *
     * @param idClient id клиента
     * @param firstName Имя клиента
     * @param lastName Фамилия клиента
     */
    public ClientEntity(String idClient, String firstName, String lastName) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Конструктор создает сущность клиента и генерирует id ри его отсутствии
     *
     * @param firstName Имя клиента
     * @param lastName Фамилия клиента
     */
    public ClientEntity(String firstName, String lastName) {
        this.idClient = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
