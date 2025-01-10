package safe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConfig {
    public static final String DB_RELATIVE_PATH = "src/main/resources/db/";
    public static final String DB_NAME = "safe";

    public static Connection connection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + DB_RELATIVE_PATH + DB_NAME + ".db");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

}
