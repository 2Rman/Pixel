package app.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * Абстрактный класс записи (расход или доход)
 */
@Data
public abstract class Note {

    private LocalDate date;
    private String typeNote; //расход-доход
    private int amount;
    private String account;
    private String commentary;

}
