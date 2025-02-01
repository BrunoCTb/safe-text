package safe.repository;

import safe.database.SqliteConfig;
import safe.domain.SafeNote;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class SafeNoteRepositoryImpl implements SafeNoteRepository{

    @Override
    public void createTableSafeNote() {
        String sql = """
                CREATE TABLE IF NOT EXISTS safe_note (\
                id INTEGER PRIMARY KEY,\
                title TEXT not null,\
                content TEXT not null,\
                created_at TIMESTAMP not null,\
                updated_at TIMESTAMP not null,\
                tags TEXT,\
                type TEXT,\
                isEncrypted BIT not null,\
                userId Integer,\
                FOREIGN KEY userId REFERENCES users(id)
                );
                """;

        try (Connection connection = SqliteConfig.connection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addSafeNote() {

    }

    @Override
    public Optional<SafeNote> findById() {
        return Optional.empty();
    }
}
