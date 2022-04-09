package app.entity;

import lombok.Data;

import java.sql.Date;

/**
 * Класс сущность, реализующий сущности-затраты со всеми соответствующими полями
 */
@Data
public class Debit extends Entity {

    private String idDebit;
    private Date date;
    private String typeIncome;
    private int amount;
    private String idAccount;
}
