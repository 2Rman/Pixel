package app.dao;

import app.connection.ConnectionPool;
import app.entity.Entity;
import app.entity.ServiceTypeEntity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.constant.ConstantQuery.*;

/**
 * Класс реализующий сущность типа сервиса
 */
public class ServiceTypeDAO implements AbstractDAO{

    Logger logger = Logger.getLogger(ServiceTypeDAO.class);

    /**
     * Метод для получения всех сервисов, определенных авторизованным аккаунтом
     *
     * @param id авторизованного аккаунта
     * @return список сервисов данного аккаунта
     */
    @Override
    public List<ServiceTypeEntity> getAll(String id) {

        logger.info("Getting all service-type data");

        List<ServiceTypeEntity> serviceTypeEntities = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_ALL_INCOME_TYPES);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idServiceType = resultSet.getString(1);
                String serviceName = resultSet.getString(2);
                serviceTypeEntities.add(new ServiceTypeEntity(idServiceType, serviceName));

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
        return serviceTypeEntities;
    }

    /**
     * Метод для получения строки конкретного сервиса по id
     *
     * @param id сервиса
     * @return заполненную сущность сервиса
     */
    @Override
    public Entity getById(String id) {

        logger.info("Getting service-type data by ID");

        ServiceTypeEntity serviceTypeEntity = null;

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_EXPENSE_TYPE_BY_ID);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idServiceType = resultSet.getString(1);
                String serviceName = resultSet.getString(2);
                serviceTypeEntity = new ServiceTypeEntity(idServiceType, serviceName);

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
        return serviceTypeEntity;
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
