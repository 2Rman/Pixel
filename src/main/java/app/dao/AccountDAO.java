package app.dao;

import app.connection.ConnectionPool;
import app.entity.Account;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static app.constant.ConstantQuery.CREATE_ACCOUNT;
import static app.constant.ConstantQuery.GET_ACCOUNT_BY_PHONE_NUMBER;

/**
 * Класс реализующий управления моделью Аккаунта
 */
public class AccountDAO implements IAbstractDAO<String, Account> {

    Logger logger = Logger.getLogger(AccountDAO.class);

    @Override
    public List getALL() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Account getById(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Реализация метода создания нового аакаунта с данными, предварительно собранными со страницы регистрации.
     * Извлекает свободное соединение из ConnectionPool, подготавливает SQL-запрос для создания новой записи в таблице
     *
     * @param account заполненная сущность аккаунта
     * @return результат выполнения запроса
     */
    @Override
    public int create(Account account) {

        logger.info("Creating new account-DAO");

        Connection connection;
        PreparedStatement preparedStatement = null;
        int result = 0;

        connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(CREATE_ACCOUNT);
            preparedStatement.setString(1, String.valueOf(UUID.randomUUID()));
            preparedStatement.setString(2, account.getName());
            preparedStatement.setString(3, account.getPhone());
            preparedStatement.setString(4, account.getPassword());
            preparedStatement.setInt(5, 0);
            logger.info(preparedStatement + " sending to DB");
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
    public Account getByPhoneNumber(String number) {

        logger.info("Getting account from DB");

        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Account account = new Account();

        connection = ConnectionPool.getInstance().getConnection();

        try {
            preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_PHONE_NUMBER);
            preparedStatement.setString(1, number);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                account.setAccountId(resultSet.getString(1));
                account.setName(resultSet.getString(2));
                account.setPhone(resultSet.getString(3));
                account.setPassword(resultSet.getString(4));
                account.setSavings(resultSet.getInt(5));
            }

            logger.info("New account created:");
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

    /**
     * TODO
     */
    @Override
    public boolean update(Account entity) {
        return false;
    }

    /**
     * TODO
     */
    @Override
    public boolean delete(Account entity) {
        return false;
    }
}

