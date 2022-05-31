package app.dao;

import app.connection.ConnectionPool;
import app.entity.AccountEntity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static app.constant.ConstantQuery.*;

/**
 * Класс реализующий управления моделью Аккаунта
 */
public class AccountDAO implements AbstractDAO<AccountEntity> {

    Logger logger = Logger.getLogger(AccountDAO.class);

    /**
     * Метод для получения всех аккаунтов из БД
     *
     * @return список сущностей аккаунтов
     */
    public List<AccountEntity> getAll() {

        logger.info("Getting all accounts data");

        List<AccountEntity> accountEntities = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {

            preparedStatement = connection.prepareStatement(GET_ALL_ACCOUNTS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idAccount = resultSet.getString(1);
                String name = resultSet.getString(2);
                String phoneNumber = resultSet.getString(3);
                String password = resultSet.getString(4);
                int savings = resultSet.getInt(5);

                accountEntities.add(new AccountEntity(idAccount, name, phoneNumber, password, savings));
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
        return accountEntities;
    }

    @Override
    public List<AccountEntity> getAll(String id) {
        return null;
    }

    /**
     * Метод для получения сущности аккаунта из БД по ID
     * @param id-аккаунта в БД (он же присваивается сессии)
     * @return заполненную сущность аккаунта
     */
    @Override
    public AccountEntity getById(String id) {

        logger.info("Getting account by ID from DB");

        PreparedStatement preparedStatement = null;
        AccountEntity account = new AccountEntity();

        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_ID);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                account.setIdAccount(resultSet.getString(1));
                account.setName(resultSet.getString(2));
                account.setPhoneNumber(resultSet.getString(3));
                account.setPassword(resultSet.getString(4));
                account.setSavings(resultSet.getInt(5));
            }

            logger.info("Account got from DB by ID");

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
        return account;
    }

    /**
     * Реализация метода создания нового аккаунта с данными, предварительно собранными со страницы регистрации.
     * Извлекает свободное соединение из ConnectionPool, подготавливает SQL-запрос для создания новой записи в таблице
     *
     * @param account заполненная сущность аккаунта
     * @return результат выполнения запроса
     */
    @Override
    public int create(AccountEntity account) {

        logger.info("Creating new account-DAO");

        int result = 0;
        PreparedStatement preparedStatement = null;

        Connection connection = ConnectionPool.getInstance().getConnection();

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(CREATE_ACCOUNT);

            preparedStatement.setString(1, String.valueOf(UUID.randomUUID()));
            preparedStatement.setString(2, account.getName());
            preparedStatement.setString(3, account.getPhoneNumber());
            preparedStatement.setString(4, account.getPassword());
            preparedStatement.setInt(5, 0);

            result = preparedStatement.executeUpdate();

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

    /**
     * Реализация метода запроса аккаунта из БД по номеру телефона
     *
     * @param number переданный номер телефона
     * @return заполненную сущность аккаунта
     */
    public AccountEntity getByPhoneNumber(String number) {

        logger.info("Getting account by PHONE_NUMBER from DB");

        PreparedStatement preparedStatement = null;
        AccountEntity account = new AccountEntity();

        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_PHONE_NUMBER);
            preparedStatement.setString(1, number);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                account.setIdAccount(resultSet.getString(1));
                account.setName(resultSet.getString(2));
                account.setPhoneNumber(resultSet.getString(3));
                account.setPassword(resultSet.getString(4));
                account.setSavings(resultSet.getInt(5));
            }

            logger.info("New accountDAO uploaded:");
            logger.info(account.toString());

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

        return account;
    }

    @Override
    public boolean update(AccountEntity entity) {
        return false;
    }

    @Override
    public boolean delete(AccountEntity entity) {
        return false;
    }
}

