package app.entity;

import lombok.Data;

import java.sql.Date;

/**
 * Класс сущность, реализующий сущности-дохода со всеми соответствующими полями
 */
@Data
public class Income extends Entity {

    private String idIncome;
    private Date date;
    private String typeIncome;
    private int amount;
    private String idClient;
    private String idAccount;
}
