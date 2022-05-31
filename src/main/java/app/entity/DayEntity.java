package app.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-сущность дня. Состоит из списков сущностей доходов и расходов за текущий день
 */
@Data
public class DayEntity extends Entity {

    private LocalDate date;
    private List<IncomeEntity> incomeList;
    private List<ExpenseEntity> expenseList;

    /**
     * Конструктор для создания дня в случае присутствия записи только о прибыли
     *
     * @param date дата дня
     * @param income запись о прибыли
     */
    public DayEntity (LocalDate date, IncomeEntity income) {

        this.date = date;
        this.incomeList = new ArrayList<>();
        incomeList.add(income);
        this.expenseList = new ArrayList<>();
    }

    /**
     * Конструктор для создания дня в случае присутствия записи только о расходе
     *
     * @param expense запись о расходе
     */
    public DayEntity (LocalDate date, ExpenseEntity expense) {

        this.date = date;
        this.incomeList = new ArrayList<>();
        this.expenseList = new ArrayList<>();
        expenseList.add(expense);
    }

    /**
     * Конструктор пустого дня, если это выходной или не участвующий в создании календаря день
     */
    public DayEntity(LocalDate date) {

        this.date = date;
        this.incomeList = new ArrayList<>();
        this.expenseList = new ArrayList<>();

    }
}
