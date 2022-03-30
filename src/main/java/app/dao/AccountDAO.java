package app.dao;

import app.entity.Account;
import app.сonnection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static app.constant.ConstantQuery.CREATE_ACCOUNT;
import static app.constant.ConstantQuery.GET_ACCOUNT_BY_ID;

/**
 * Класс реализующий управления моделью Аккаунта
 */
public class AccountDAO implements AbstractController<String, Account> {

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
    public boolean create(Account account) {

        Connection connection;
        PreparedStatement preparedStatement;
        boolean result = false;

        connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(CREATE_ACCOUNT);
            preparedStatement.setString(1, String.valueOf(UUID.randomUUID()));
            preparedStatement.setString(2, account.getName());
            preparedStatement.setString(3, account.getPhone());
            preparedStatement.setString(4, account.getPassword());
            result = preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
    public Account getByNumber(int number) {

        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Account account = new Account();

        connection = ConnectionPool.getInstance().getConnection();


        try {
            preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_ID);
            preparedStatement.setInt(1, number);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                account.setAccountId(resultSet.getString(1));
                account.setName(resultSet.getString(2));
                account.setPhone(resultSet.getString(3));
                account.setPassword(resultSet.getString(4));
                account.setSavings(resultSet.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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

