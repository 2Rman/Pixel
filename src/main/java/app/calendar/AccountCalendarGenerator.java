package app.calendar;

import java.time.LocalDate;
import java.time.Month;

/**
 * Класс, предназначенный для создания календаря, вычисление количества строк (недель) на главной странице
 * в зависимости от текущего месяца
 * и определение остальных полей, связанных с построением таблицы календаря
 * TODO це будет утилитный класс
 */
public class AccountCalendarGenerator {

    //    FOR TESTS
    private int currentYear = 2021;
    private Month currentMonth = Month.of(3);
//    private final int currentYear = LocalDate.now().getYear();
//    private final Month currentMonth = LocalDate.now().getMonth();
    private int numberOfRows = calculateNumberOfRows();
    private LocalDate[][] calendarValues = calculateCalendarValues(numberOfRows);

    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * TODO сделать статическим метод со всеми вытекающими...
     * @return
     */
    public LocalDate[][] getCalendarValues() {
        return calendarValues;
    }

    /**
     * Метод для подсчета количества отображаемых строк в календаре на странице клиента в зависимости от текущего месяца
     * Алгоритм вычисления основан на определении первого дня недели текущего месяца и
     * последнего числа предыдущего месяца.
     *
     * @return int количество отображаемых строк в таблице на странице пользователя в календаре
     */
    private int calculateNumberOfRows() {

        String firstDayOfWeek = String.valueOf(LocalDate.of(currentYear, currentMonth, 1).getDayOfWeek());
        String lastDayOfThisMonth = (String.valueOf((LocalDate.of(currentYear, currentMonth, 1).lengthOfMonth())));

        if (firstDayOfWeek.equals("MONDAY") && String.valueOf(currentMonth.getValue()).equals("2")) {
            return 4;
        } else if (firstDayOfWeek.equals("SUNDAY") && !String.valueOf(currentMonth.getValue()).equals("2")) {
            return 6;
        } else if (firstDayOfWeek.equals("SATURDAY") && lastDayOfThisMonth.equals("31")) {
            return 6;
        } else return 5;
    }

    /**
     * Метод для создания двумерного массива числами из реального календаря.
     *
     * TODO отрефакторить - убрать counter
     *
     * @return заполненную таблицу-календарь
     */
    private LocalDate[][] calculateCalendarValues(int numberOfRows) {

        int currentDayOfWeek = LocalDate.of(currentYear, currentMonth, 1).getDayOfWeek().getValue() - 1;
        LocalDate firstDateOfFirstWeek = LocalDate.of(currentYear, currentMonth, 1).minusDays(currentDayOfWeek);

        int counter = 0;

        LocalDate[][] table = new LocalDate[numberOfRows][7];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < 7; j++) {
                table[i][j] = firstDateOfFirstWeek.plusDays(counter);
                counter++;
            }
        }
        return table;
    }
}
