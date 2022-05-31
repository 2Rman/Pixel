package app.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * Абстрактный класс записи (расход или доход)
 */
@Data
public abstract class Note extends Entity{

    private String id;
    private LocalDate date;
    private String idNoteType;
    private String noteType; //расход-доход
    private int amount;
    private String idAccount;
    private String commentary;


}
