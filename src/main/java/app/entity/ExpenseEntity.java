package app.entity;

import lombok.Data;

import java.sql.Date;
import java.util.UUID;

import static app.constant.ConstantUtil.HUNDRED;

/**
 * Класс реализации одной записи расходов в БД
 */
@Data
public class ExpenseEntity extends Note {

    /**
     * Конструктор, использующийся для хранения сущности после запроса в БД в читабельном виде
     *
     * @param date     дата принятой затраты
     * @param typeNote тип затраты
     * @param amount   сумма затраты
     * @param comment  комментарий к затрате
     */
    public ExpenseEntity(Date date, String typeNote, int amount, String comment) {
        this.setDate(date.toLocalDate());
        this.setNoteType(typeNote);
        this.setAmount(amount/HUNDRED);
        this.setCommentary(comment);
    }

    /**
     * Конструктор для создания сущности-расхода в виде таблицы с id
     *
     * @param idExpense   id записи о расходе
     * @param date        дата затраты
     * @param idExpenseType тип расхода (на что потрачено)
     * @param amount      сумма затраты
     * @param idAccount   id аккаунта авторизованного пользователя
     * @param commentary  комментарий к полученной записи
     */
    public ExpenseEntity(String idExpense, Date date, String idExpenseType, int amount, String idAccount, String commentary) {
        this.setId(idExpense);
        this.setDate(date.toLocalDate());
        this.setIdNoteType(idExpenseType);
        this.setAmount(amount/HUNDRED);
        this.setIdAccount(idAccount);
        this.setCommentary(commentary);
    }

    /**
     * Конструктор, использующийся для создания новой записи в БД
     *
     * @param date          дата, которой будет привязана запись
     * @param idServiceType тип затраты
     * @param amount        сумма затраты
     * @param idAccount     авторизованный аккаунт пользователя
     * @param commentary    комментарий к записи о затрате
     */
    public ExpenseEntity(Date date, String idServiceType, int amount, String idAccount, String commentary) {
        this.setId(String.valueOf(UUID.randomUUID()));
        this.setDate(date.toLocalDate());
        this.setIdNoteType(idServiceType);
        this.setAmount(amount);
        this.setIdAccount(idAccount);
        this.setCommentary(commentary);
    }
}
