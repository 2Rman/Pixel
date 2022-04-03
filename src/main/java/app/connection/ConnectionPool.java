package app.connection;

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

    private static ConnectionPool instance = null;

    private ConnectionPool() {}

    /**
     * Метод создания ЕДИНСТВЕННОГО пула
     * @return экземпляр пула подключений к БД
     */
    public static ConnectionPool getInstance() {
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

            DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pixelPool");
//            connection = dataSource.getConnection("admin", "Ghjuhfvvf795!");
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}