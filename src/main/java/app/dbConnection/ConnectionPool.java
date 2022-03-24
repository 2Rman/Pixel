package app.dbConnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс создания пул подключений к БД
 */
public class ConnectionPool {

    private ConnectionPool() {
    }

    private static ConnectionPool instance = null;

    /**
     * Метод создания ЕДИНСТВЕННОГО пула
     * @return экземпляр пула подключений к БД
     */
    private static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
            return instance;
    }

    /**
     * Метод получения нового подключения для дальнейшего взаимодействия с БД
     * @return новое соединение
     */
    public Connection getConnection() {

        Context context;
        Connection connection = null;

        try {
            context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java/env/jdbc/pixelPool");

            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
