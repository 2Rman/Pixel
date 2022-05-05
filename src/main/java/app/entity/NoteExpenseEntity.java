package app.entity;

import lombok.Data;

import java.sql.Date;

/**
 * Класс реализации одной записи расходов в БД
 */
@Data
public class NoteExpenseEntity extends Note{

    public NoteExpenseEntity(Date date, String typeNote, int amount, String comment) {
        this.setDate(date.toLocalDate());
        this.setTypeNote(typeNote);
        this.setAmount(amount);
        this.setCommentary(comment);
    }
}
