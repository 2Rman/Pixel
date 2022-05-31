package app.util;

import app.entity.ExpenseEntity;
import app.entity.IncomeEntity;
import lombok.Data;

import java.util.*;

import static app.constant.ConstantUtil.HUNDRED;
import static app.constant.ConstantUtil.TOTAL_TABLE_ITEMS;

/**
 * Класс для сбора обобщенной информации об отображаемом периоде
 * в виде таблицы в нижней части экрана
 */
@Data
public class TotalDataCollector {

    private int totalProfit;
    private int totalIncome;
    private int totalExpense;
    private int totalProcedures;
    private int totalManicure;
    private int totalPedicure;
    private int uniqueClient;

    /**
     * Конструктор
     *
     * @param incomeEntities список дохода за период
     * @param expenseEntities список расходов за период
     */
    public TotalDataCollector(List<IncomeEntity> incomeEntities, List<ExpenseEntity> expenseEntities) {
        this.totalIncome = calculateIncome(incomeEntities);
        this.totalExpense = calculateExpense(expenseEntities);
        this.totalProfit = this.totalIncome - this.totalExpense;
        this.totalProcedures = incomeEntities.size();
        this.totalManicure = calculateManicure(incomeEntities);
        this.totalPedicure = calculatePedicure(incomeEntities);
        this.uniqueClient = calculateClient(incomeEntities);
    }

    /**
     * Метод для возвращение сгруппированной информации о расходах, доходах и записях за период
     *
     * @return HashMap фиксированного размера
     */
    public Map<String, String> toMap() {

        Map<String, String> totalMap = new HashMap<>();

        totalMap.put(TOTAL_TABLE_ITEMS[0], String.valueOf(this.totalProfit/HUNDRED));
        totalMap.put(TOTAL_TABLE_ITEMS[1], String.valueOf(this.totalIncome/HUNDRED));
        totalMap.put(TOTAL_TABLE_ITEMS[2], String.valueOf(this.totalExpense/HUNDRED));
        totalMap.put(TOTAL_TABLE_ITEMS[3], String.valueOf(this.totalProcedures));
        totalMap.put(TOTAL_TABLE_ITEMS[4], (this.totalManicure + "\\" + this.totalPedicure));
        totalMap.put(TOTAL_TABLE_ITEMS[5], String.valueOf(this.uniqueClient));

        return totalMap;
    }

    private int calculateIncome(List<IncomeEntity> incomeEntities) {

        int result = 0;

        for (IncomeEntity entity : incomeEntities) {
            result += entity.getAmount();
        }
        return result;
    }

    private int calculateExpense(List<ExpenseEntity> expenseEntities) {

        int result = 0;

        for (ExpenseEntity entity : expenseEntities) {
            result += entity.getAmount();
        }
        return result;
    }

    private int calculateManicure(List<IncomeEntity> incomeEntities) {

        int result = 0;

        for (IncomeEntity entity : incomeEntities) {
            if (entity.getNoteType().equals("МАНИКЮР") || entity.getNoteType().equals("МАНИКЮР+ПЕДИКЮР")) {
                result++;
            }
        }
        return result;
    }

    private int calculatePedicure(List<IncomeEntity> incomeEntities) {

        int result = 0;

        for (IncomeEntity entity : incomeEntities) {
            if (entity.getNoteType().equals("ПЕДИКЮР") || entity.getNoteType().equals("МАНИКЮР+ПЕДИКЮР")) {
                result++;
            }
        }
        return result;
    }

    private int calculateClient(List<IncomeEntity> incomeEntities) {

        Set<String> clients = new HashSet<>();

        for (IncomeEntity entity : incomeEntities) {
            clients.add(entity.getClient());
        }
        return clients.size();
    }
}
