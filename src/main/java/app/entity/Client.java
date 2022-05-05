package app.entity;

import lombok.Data;

/**
 * Класс сущность, реализующий сущность-клиента со всеми соответствующими полями
 */
@Data
public class Client extends Entity {

    private String idClient;
    private String firstName;
    private String lastName;

}
