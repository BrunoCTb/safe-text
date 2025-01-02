package safe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlite {

    private final String DB_RELATIVE_PATH = "src/main/resources/db/";
    private final String DB_NAME = "safe.db";

    public Statement connection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DB_RELATIVE_PATH + DB_NAME);
            return connection.createStatement();
        } catch (SQLException e) {
            System.out.println("erro gerando conexão");
            return null;
        }
    }

}
