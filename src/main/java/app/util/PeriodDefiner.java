package app.util;

import java.time.LocalDate;

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
    public static LocalDate[] calculateDay(LocalDate date) {
        return new LocalDate[]{date, date};
    }

    /**
     * Метод отсчитывает начальную и конечную даты НЕДЕЛИ, в которую входит переданная дата
     *
     * @param date переданная со стороны клиента дата
     * @return массив из 2 элементов, содержащий начальную и конечную даты недели
     */
    public static LocalDate[] calculateWeek(LocalDate date) {
        return new LocalDate[]{date.minusDays(date.getDayOfWeek().getValue()),
                date.plusWeeks(1).minusDays(date.getDayOfWeek().getValue())};
    }

    /**
     * Метод отсчитывает начальную и конечную даты МЕСЯЦА, в который входит переданная дата
     *
     * @param receivedDate переданная со стороны клиента дата
     * @return массив из 2 элементов, содержащий начальную и конечную даты месяца
     */
    public static LocalDate[] calculateMonth(LocalDate receivedDate) {
        return new LocalDate[]{receivedDate.withDayOfMonth(1), receivedDate.plusMonths(1).withDayOfMonth(1).minusDays(1)};
    }

    /**
     * Метод отсчитывает начальную и конечную даты ГОДА, в который входит переданная дата
     *
     * @param date переданная со стороны клиента дата
     * @return массив из 2 элементов, содержащий начальную и конечную ДАТЫ года
     */
    public static LocalDate[] calculateYear(LocalDate date) {
        return new LocalDate[]{LocalDate.of(date.getYear(), 1, 1),
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
