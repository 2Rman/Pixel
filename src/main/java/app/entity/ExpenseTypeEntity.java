package app.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class ExpenseTypeEntity extends Entity{

    private String idExpense;
    private String expenseName;

    public ExpenseTypeEntity(String idExpense, String expenseName) {
        this.idExpense = idExpense;
        this.expenseName = expenseName;
    }

    public ExpenseTypeEntity(String expenseName) {
        this.idExpense = String.valueOf(UUID.randomUUID());
        this.expenseName = expenseName;
    }
}
