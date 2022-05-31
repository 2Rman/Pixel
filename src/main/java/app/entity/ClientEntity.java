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

    public ClientEntity(String idClient, String firstName, String lastName) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ClientEntity(String firstName, String lastName) {
        this.idClient = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
