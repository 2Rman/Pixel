package app.entity;

import lombok.Data;

import java.util.UUID;

/**
 * Класс сущность, реализующий аккаунт пользователя со всеми соответствующими полями
 */
@Data
public class AccountEntity extends Entity {

    private String idAccount;
    private String name;
    private String phoneNumber;
    private String password;
    private int savings;

    public AccountEntity() {}

    public AccountEntity(String name, String phoneNumber, String password) {
        this.idAccount = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.savings = 0;
    }

    public AccountEntity(String idAccount, String name, String phoneNumber, String password, int savings) {
        this.idAccount = idAccount;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.savings = savings;
    }
}
