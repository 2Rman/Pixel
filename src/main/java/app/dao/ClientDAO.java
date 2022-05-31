package app.dao;

import app.connection.ConnectionPool;
import app.entity.ClientEntity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.constant.ConstantQuery.*;

public class ClientDAO implements AbstractDAO<ClientEntity> {

    Logger logger = Logger.getLogger(ClientDAO.class);

    /**
     * Метод для получения списка сущностей всех клиентов авторизованного аккаунта
     *
     * @param id авторизованного аккаунта
     * @return список клиентов авторизованного аккаунта
     */
    @Override
    public List<ClientEntity> getAll(String id) {

        logger.info("Getting all clients data");

        List<ClientEntity> clientEntities = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_ALL_CLIENTS);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idClient = resultSet.getString(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                clientEntities.add(new ClientEntity(idClient, firstName, lastName));
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
        return clientEntities;
    }

    /**
     * Метод для получения конкретной сущности клиента по id
     *
     * @param id клинета в БД
     * @return заполненную сущность клиента
     */
    @Override
    public ClientEntity getById(String id) {

        logger.info("Getting client data by ID");

        ClientEntity clientEntity = null;

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_CLIENT_BY_ID);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idClient = resultSet.getString(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(2);
                clientEntity = new ClientEntity(idClient, firstName, lastName);

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
        return clientEntity;
    }

    @Override
    public int create(ClientEntity client) {

        logger.info("Creating new client");

        int result = 0;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {

            preparedStatement = connection.prepareStatement(CREATE_CLIENT);

            preparedStatement.setString(1, client.getIdClient());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getLastName());

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

    @Override
    public boolean update(ClientEntity clientEntity) {
        return false;
    }

    @Override
    public boolean delete(ClientEntity clientEntity) {
        return false;
    }
}

