package app.dao;

import app.connection.ConnectionPool;
import app.entity.Entity;
import app.entity.IncomeEntity;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static app.constant.ConstantQuery.*;

public class IncomeDAO implements AbstractDAO {

    Logger logger = Logger.getLogger(IncomeDAO.class);

    /**
     * Метод для вызова записей дохода за один день
     *
     * @param id   аккаунта из которого осуществляется запрос
     * @param date дата для которой посылается запрос
     * @return список записей в заданную дату
     */
    public List getDailyIncome(String id, LocalDate date) {

        logger.info("Getting current month income data");

        List<IncomeEntity> notes = new ArrayList<>();

        Date start = Date.valueOf(date.toString());

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_DAILY_INCOME);

            preparedStatement.setString(1, id);
            preparedStatement.setDate(2, start);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Date incomeDate = resultSet.getDate(1);
                String typeNote = resultSet.getString(2);
                int amount = resultSet.getInt(3);
                String name = resultSet.getString(4) + " " + resultSet.getString(5);
                String commentary = resultSet.getString(6);
                new IncomeEntity(incomeDate, typeNote, amount, name, commentary);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return notes;
    }

    /**
     * Метод для получения прибыли за промежуток времени
     *
     * @param id аккаунта авторизованного пользователя
     * @param dateStart начальная дата
     * @param dateEnd конечная дата
     * @return список сущностей записей дохода
     */
    public List<IncomeEntity> getPeriodIncome(String id, LocalDate dateStart, LocalDate dateEnd) {

        logger.info("Getting income data between " + dateStart + " and " + dateEnd);

        List<IncomeEntity> notes = new ArrayList<>();

        Date dStart = Date.valueOf(dateStart.toString());
        Date dEnd = Date.valueOf(dateEnd.toString());

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_PERIOD_INCOME);

            preparedStatement.setString(1, id);
            preparedStatement.setDate(2, dStart);
            preparedStatement.setDate(3, dEnd);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Date date = resultSet.getDate(1);
                String typeNote = resultSet.getString(2);
                int amount = resultSet.getInt(3);
                String name = resultSet.getString(4) + " " + resultSet.getString(5);
                String commentary = resultSet.getString(6);
                String idIncome = resultSet.getString(7);
                notes.add(new IncomeEntity(idIncome, date, typeNote, amount, name, commentary));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return notes;
    }

    /**
     * Метод получения всех записей о прибыли из БД по id-account
     *
     * @param id текущего авторизованного аккаунта
     * @return записи о прибыли из БД
     */
    @Override
    public List<IncomeEntity> getAll(String id) {

        logger.info("Getting all income data");

        List<IncomeEntity> incomeEntities = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_ALL_INCOME);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Date incomeDate = resultSet.getDate(1);
                String typeNote = resultSet.getString(2);
                int amount = resultSet.getInt(3);
                String name = resultSet.getString(4) + " " + resultSet.getString(5);
                String commentary = resultSet.getString(6);
                incomeEntities.add(new IncomeEntity(incomeDate, typeNote, amount, name, commentary));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return incomeEntities;
    }

    /**
     * Метод для получения конкретной записи из БД о доходе в виде id-шников
     *
     * @param id конкретной записи в БД о доходе
     * @return сущность записи дохода, заполненную id-ключами других таблиц
     */
    @Override
    public Entity getById(String id) {

        logger.info("Getting income-note data by id");

        IncomeEntity incomeEntity = null;

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_INCOME_BY_ID);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idIncome = resultSet.getString(1);
                Date incomeDate = resultSet.getDate(2);
                String serviceType = resultSet.getString(3);
                int amount = resultSet.getInt(4);
                String idClient = resultSet.getString(5);
                String idAccount = resultSet.getString(6);
                String commentary = resultSet.getString(7);
                incomeEntity = new IncomeEntity(idIncome, incomeDate, serviceType, amount, idClient, idAccount, commentary);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return incomeEntity;
    }

    /**
     * Метод получения данных записи о прибыли по ее id в виде текстовой информации
     *
     * @param id записи о прибыли
     * @return заполненную сущность записи в читабельном виде
     */
    public Entity getDataById(String id) {

        logger.info("Getting data-income data by id");

        IncomeEntity incomeEntity = null;

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_DATA_INCOME_BY_ID);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Date incomeDate = resultSet.getDate(1);
                String typeNote = resultSet.getString(2);
                int amount = resultSet.getInt(3);
                String name = resultSet.getString(4) + " " + resultSet.getString(5);
                String commentary = resultSet.getString(6);
                incomeEntity = new IncomeEntity(incomeDate, typeNote, amount, name, commentary);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return incomeEntity;
    }

    /**
     * Метод для добавления в БД новой записи о доходе (произведенной процедуре)
     *
     * @param incomeEntity заполненная сущность "дохода" ("прибыли")
     * @return успешно ли добавлена запись
     */
    public boolean insertNote(IncomeEntity incomeEntity) {

        logger.info("Inserting new income-note");

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean result = false;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW_INCOME_NOTE);

            preparedStatement.setString(1, incomeEntity.getId());
            preparedStatement.setDate(2, Date.valueOf(incomeEntity.getDate()));
            preparedStatement.setString(3, incomeEntity.getIdNoteType());
            preparedStatement.setInt(4, incomeEntity.getAmount());
            preparedStatement.setString(5, incomeEntity.getIdClient());
            preparedStatement.setString(6, incomeEntity.getIdAccount());
            preparedStatement.setString(7, incomeEntity.getCommentary());

            int resultQuery = preparedStatement.executeUpdate();

            if (resultQuery == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int create(Entity entity) {
        return 0;
    }

    @Override
    public boolean update(Entity entity) {
        return false;
    }

    @Override
    public boolean delete(Entity entity) {
        return false;
    }
}
