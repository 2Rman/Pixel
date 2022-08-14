package app.entity;

import lombok.Data;

import java.util.UUID;

/**
 * Класс-сущность, реализующий "тип расхода" со всеми соответствующими полями
 */
@Data
public class ExpenseTypeEntity extends Entity {

    private String idExpenseType;
    private String expenseTypeName;

    /**
     * Конструктор, создает сущность по известным id-типа расхода и названию типа расхода
     *
     * @param idExpenseType id типа расхода
     * @param expenseTypeName название типа расхода
     */
    public ExpenseTypeEntity(String idExpenseType, String expenseTypeName) {
        this.idExpenseType = idExpenseType;
        this.expenseTypeName = expenseTypeName;
    }

    /**
     * Конструктор, создает сущность типа расхода по названию с генерацией id
     *
     * @param expenseTypeName название типа расхода
     */
    public ExpenseTypeEntity(String expenseTypeName) {
        this.idExpenseType = String.valueOf(UUID.randomUUID());
        this.expenseTypeName = expenseTypeName;
    }
}
