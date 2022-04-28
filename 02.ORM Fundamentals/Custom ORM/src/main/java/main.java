import entities.User;
import orm.EntityManager;

import java.sql.Connection;
import java.sql.SQLException;

import static orm.MyConnector.createConnection;
import static orm.MyConnector.getConnection;

public class main {
    public static void main(String[] args) throws SQLException {

        createConnection("root","1234","orm_db");
        Connection connection = getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);
    }
}
