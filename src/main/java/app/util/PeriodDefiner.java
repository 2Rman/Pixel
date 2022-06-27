package app.util;

import java.time.LocalDate;

import static app.constant.ConstantUtil.NEXT;
import static app.constant.ConstantUtil.PREVIOUS;

/**
 * Утилитарный класс для определения начальной и конечно дат периода, для которого будут осуществляться
 * последующие подсчеты и запросы в БД
 */
public class PeriodDefiner {

    private PeriodDefiner() {}

    /**
     * Метод возвращает массив из переданной даты
     * (возможно лишнее, но для общей красоты структуры захотелось сделать так)
     * (Я художник, я так вижу=)
     *
     * @param date переданная со стороны клиента дата
     * @return массив из 2 элементов, содержащий переданную дату
     */
    public static LocalDate[] calculateDay(LocalDate date, String direction) {
        if (direction.equals(PREVIOUS)) {
            return new LocalDate[]{date.minusDays(1), date.minusDays(1)};
        } else if (direction.equals(NEXT)) {
            return new LocalDate[]{date.plusDays(1), date.plusDays(1)};
        } else return new LocalDate[]{date, date};
    }

    /**
     * Метод отсчитывает начальную и конечную даты НЕДЕЛИ, в которую входит переданная дата
     *
     * @param date переданная со стороны клиента дата
     * @return массив из 2 элементов, содержащий начальную и конечную даты недели
     */
    public static LocalDate[] calculateWeek(LocalDate date, String direction) {
        if (direction.equals(PREVIOUS)) {
            return new LocalDate[]{date.minusDays(date.getDayOfWeek().getValue()).minusWeeks(1),
                    date.minusDays(date.getDayOfWeek().getValue())};
        } else if (direction.equals(NEXT)) {
            return new LocalDate[]{date.minusDays(date.getDayOfWeek().getValue()).plusWeeks(1),
                    date.plusWeeks(2).minusDays(date.getDayOfWeek().getValue())};
        } else return new LocalDate[]{date.minusDays(date.getDayOfWeek().getValue()),
                date.plusWeeks(1).minusDays(date.getDayOfWeek().getValue())};
    }

    /**
     * Метод отсчитывает начальную и конечную даты МЕСЯЦА, в который входит переданная дата
     *
     * @param date переданная со стороны клиента дата
     * @return массив из 2 элементов, содержащий начальную и конечную даты месяца
     */
    public static LocalDate[] calculateMonth(LocalDate date, String direction) {
        if (direction.equals(PREVIOUS)) {
            return new LocalDate[]{date.minusMonths(1).withDayOfMonth(1), date.withDayOfMonth(1).minusDays(1)};
        } else if (direction.equals(NEXT)) {
            return new LocalDate[]{date.plusMonths(1).withDayOfMonth(1), date.plusMonths(2).withDayOfMonth(1).minusDays(1)};
        } else return new LocalDate[]{date.withDayOfMonth(1), date.plusMonths(1).withDayOfMonth(1).minusDays(1)};
    }

    /**
     * Метод отсчитывает начальную и конечную даты ГОДА, в который входит переданная дата
     *
     * @param date переданная со стороны клиента дата
     * @return массив из 2 элементов, содержащий начальную и конечную ДАТЫ года
     */
    public static LocalDate[] calculateYear(LocalDate date, String direction) {
        if (direction.equals(PREVIOUS)) {
            return new LocalDate[]{LocalDate.of(date.minusYears(1).getYear(), 1, 1),
                    LocalDate.of(date.minusYears(1).getYear(), 12, 31)};
        } else if (direction.equals(NEXT)) {
            return new LocalDate[]{LocalDate.of(date.plusYears(1).getYear(), 1, 1),
                    LocalDate.of(date.plusYears(1).getYear(), 12, 31)};
        } else return new LocalDate[]{LocalDate.of(date.getYear(), 1, 1),
                LocalDate.of(date.getYear(), 12, 31)};
    }

    /**
     * Метод отсчитывает начальную и конечную даты для случая, когда необходимо посчитать значения за весь период.
     * По сути, просто хранит два значения: 2000-01-01 и текущая дата. (Сделано так для целостности картины)
     *
     * @return массив из 2 элементов, содержащий даты для подсчета инфо за весь период ведения аккаунта
     */
    public static LocalDate[] calculateInfinity() {
        return new LocalDate[]{LocalDate.of(2000,1,1), LocalDate.now()};
    }
}
