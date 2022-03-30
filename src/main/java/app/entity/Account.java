package app.entity;

import lombok.Data;

/**
 * Класс сущность, реализующий аккаунт пользователя со всеми соответствующими полями
 */
@Data
public class Account extends Entity {

    private String accountId;
    private String name;
    private String phone;
    private String password;
    private int savings;
}
