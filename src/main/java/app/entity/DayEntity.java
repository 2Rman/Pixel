package app.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-день. Включает в себя всю информацию о дне:
 * дата, услуги, затраты, имена клиентов
 */
@Data
public class DayEntity implements Day {

    private LocalDate date;
    private List<String> services;
    private List<Integer> incomes;
    private List<String> clients;
    private List<String> commentaryIncomes;
            //пустая строка лишняя, знаю, сделал, чтобы не путать данные прибыли с данными расходов =)
    private List<String> expenseNames;
    private List<Integer> expenses;
    private List<String> commentaryExpenses;
            //еще одна лишняя строка просто для внешней логичности. Возможно поле ниже вообще удалю
    private int numberOfServices;

    /**
     * Конструктор пустого дня, если это выходной или не участвующий в создании календаря день
     */
    public DayEntity (LocalDate date) {

        this.date = date;
        this.services = new ArrayList<>();
        this.incomes = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.commentaryIncomes = new ArrayList<>();
        this.expenseNames = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.commentaryExpenses = new ArrayList<>();
    }

    /**
     * Конструктор в случае, если создается жень с записью (прибылью)
     *
     * @param date дата дня
     * @param service наименование услуги за которую получена прибыль
     * @param income количество прибыли
     * @param client имя и фамилия (никнейм) клиента
     * @param comment комментарий к текуще
     */
    public DayEntity (LocalDate date, String service, int income, String client, String comment) {
        this.date = date;
        this.services = new ArrayList<>();
        services.add(service);
        this.incomes = new ArrayList<>();
        incomes.add(income);
        this.clients = new ArrayList<>();
        clients.add(client);
        this.commentaryIncomes = new ArrayList<>();
        commentaryIncomes.add(comment);
        this.commentaryIncomes = new ArrayList<>();
        this.expenseNames = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.commentaryExpenses = new ArrayList<>();
    }

    /**
     * Конструктор в случае, если создается жень с записью (прибылью)
     *
     * @param date дата дня
     * @param expense наименование "затраты"
     * @param expenseAmt сумма расхода (одного, т.е. одна покупка или одна затрата, если можно так сказать)
     * @param comment комментарий к текущему "расходу"
     */
    public DayEntity (LocalDate date, String expense, int expenseAmt, String comment) {
        this.date = date;
        this.services = new ArrayList<>();
        this.incomes = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.commentaryIncomes = new ArrayList<>();
        this.expenseNames = new ArrayList<>();
        expenseNames.add(expense);
        this.expenses = new ArrayList<>();
        expenses.add(expenseAmt);
        this.commentaryExpenses = new ArrayList<>();
        commentaryExpenses.add(comment);
    }
}
