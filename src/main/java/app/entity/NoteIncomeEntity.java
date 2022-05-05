package app.entity;

import lombok.Data;

import java.sql.Date;

/**
 * Класс реализации одной записи доходов в БД
 */
@Data
public class NoteIncomeEntity extends Note {

    private String client;

    public NoteIncomeEntity(Date date, String typeNote, int amount, String client, String commentary) {
        this.setDate(date.toLocalDate());
        this.setTypeNote(typeNote);
        this.setAmount(amount);
        this.client = client;
        this.setCommentary(commentary);
    }
}
