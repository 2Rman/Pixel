package app.entity;

import java.util.Arrays;
import java.util.Objects;

public class DayEntity extends Day {

    int dDateDay;
    int dIncome;
    int dDebit;
    boolean isCurrentMonthDay;
    String[] clients;
    int numberOfServices;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayEntity dayEntity = (DayEntity) o;
        return dDateDay == dayEntity.dDateDay &&
                dIncome == dayEntity.dIncome &&
                dDebit == dayEntity.dDebit &&
                isCurrentMonthDay == dayEntity.isCurrentMonthDay &&
                numberOfServices == dayEntity.numberOfServices &&
                Arrays.equals(clients, dayEntity.clients);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dDateDay, dIncome, dDebit, isCurrentMonthDay, numberOfServices);
        result = 31 * result + Arrays.hashCode(clients);
        return result;
    }

    @Override
    public String toString() {
        return "DayEntity{" +
                "dDateDay=" + dDateDay +
                ", dIncome=" + dIncome +
                ", dDebit=" + dDebit +
                ", isCurrentMonthDay=" + isCurrentMonthDay +
                ", clients=" + Arrays.toString(clients) +
                ", numberOfServices=" + numberOfServices +
                '}';
    }
}
