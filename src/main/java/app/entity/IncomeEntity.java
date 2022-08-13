package app.entity;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import static app.constant.ConstantUtil.HUNDRED;

/**
 * Класс реализации одной записи доходов в БД
 */
@Data
public class IncomeEntity extends Note {

    private String idClient; //id клиента
    private String client; //Имя и фамилия клиента в одной строке
    private int amount;

    /**
     * Конструктор использующийся для хранения сущности после запроса в БД
     *
     * @param date дата проведения процедуры
     * @param typeNote тип процедуры в виде текста (не id)
     * @param amount полученная сумма
     * @param client имя и фамилия клиента (не id)
     * @param commentary комментарий к выполненной процедуре
     */
    public IncomeEntity(Date date, String typeNote, int amount, String client, String commentary) {
        this.setDate(date.toLocalDate());
        this.setNoteType(typeNote);
        this.setAmount(amount/HUNDRED);
        this.client = client;
        this.setCommentary(commentary);
    }

    /**
     * Конструктор для создания сущности-дохода в виде таблицы с id
     *
     * @param idIncome id записи о доходе
     * @param date дата дохода
     * @param idServiceType тип дохода (процедура)
     * @param amount сумма дохода
     * @param idClient id клиента
     * @param idAccount id аккаунта авторизованного пользователя
     * @param commentary комментарий к полученной записи
     */
    public IncomeEntity(String idIncome, Date date, String idServiceType, int amount, String idClient, String idAccount, String commentary) {
        this.setId(idIncome);
        this.setDate(date.toLocalDate());
        this.setIdNoteType(idServiceType);
        this.setAmount(amount/HUNDRED);
        this.idClient = idClient;
        this.setIdAccount(idAccount);
        this.setCommentary(commentary);
    }

    /**
     * Конструктор, использующийся при создании новой сущности записи для передачи в БД
     *
     * @param date дата новой записи (может не соответствовать текущей дате)
     * @param idServiceType id выполненной услуги
     * @param amount оплаченная сумма
     * @param idClient id клиента побывавшего на визите
     * @param idAccount id аккаунта авторизованного пользователя
     * @param commentary комментарий к записи
     */
    public IncomeEntity(LocalDate date, String idServiceType, int amount, String idClient, String idAccount, String commentary) {
        this.setId(String.valueOf(UUID.randomUUID()));
        this.setDate(date);
        this.setIdNoteType(idServiceType);
        this.setAmount(amount*HUNDRED);
        this.idClient = idClient;
        this.setIdAccount(idAccount);
        this.setCommentary(commentary);
    }
}
