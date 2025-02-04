package safe.repository;

import safe.database.SqliteConfig;
import safe.domain.SafeNote;
import safe.domain.User;

import java.sql.*;
import java.time.LocalDateTime;
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
                user_id INTEGER,\
                FOREIGN KEY (user_id) REFERENCES users(id)
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
    public void addSafeNote(SafeNote safeNote) {
        String sql = """
                INSERT INTO safe_note (id, title, content, created_at, updated_at, tags, type, isEncrypted, user_id) VALUES 
                (?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
        try (Connection connection = SqliteConfig.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            Timestamp timestamp = Timestamp.valueOf(safeNote.getCreatedAt());

            preparedStatement.setInt(1, safeNote.getId());
            preparedStatement.setString(2, safeNote.getTitle());
            preparedStatement.setString(3, safeNote.getContent());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(safeNote.getCreatedAt()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(safeNote.getUpdatedAt()));
            preparedStatement.setString(6, safeNote.getTags());
            preparedStatement.setString(7, safeNote.getType());
            preparedStatement.setBoolean(8, safeNote.isEncrypted());
            preparedStatement.setInt(9, safeNote.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<SafeNote> findById(Integer id) {
        String sql = "SELECT * FROM safe_note WHERE id=" + id;

        try (Connection connection = SqliteConfig.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet safeNoteData = preparedStatement.executeQuery();

            if (safeNoteData.next()) {
                String title = safeNoteData.getString("title");
                String content = safeNoteData.getString("content");
                LocalDateTime createdAt = safeNoteData.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updatedAt = safeNoteData.getTimestamp("updated_at").toLocalDateTime();
                String tags = safeNoteData.getString("tags");
                String type = safeNoteData.getString("type");
                boolean isEncrypted = safeNoteData.getBoolean("isEncrypted");
                Integer userId = safeNoteData.getInt("user_id");

                SafeNote safeNote = new SafeNote(id, title, content, createdAt, updatedAt,
                        tags, type, isEncrypted, userId);

                return Optional.of(safeNote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
