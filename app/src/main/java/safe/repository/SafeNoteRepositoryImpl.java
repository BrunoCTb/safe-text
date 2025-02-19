package safe.repository;

import safe.database.SqliteConfig;
import safe.domain.SafeNote;
import safe.domain.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
                INSERT INTO safe_note (title, content, created_at, updated_at, tags, type, isEncrypted, user_id) VALUES 
                (?, ?, ?, ?, ?, ?, ?, ?);
                """;
        try (Connection connection = SqliteConfig.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            Timestamp timestamp = Timestamp.valueOf(safeNote.getCreatedAt());

            preparedStatement.setString(1, safeNote.getTitle());
            preparedStatement.setString(2, safeNote.getContent());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(safeNote.getCreatedAt()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(safeNote.getUpdatedAt()));
            preparedStatement.setString(5, safeNote.getTags());
            preparedStatement.setString(6, safeNote.getType());
            preparedStatement.setBoolean(7, safeNote.isEncrypted());
            preparedStatement.setInt(8, safeNote.getUserId());

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
                SafeNote safeNote = this.getSafeNoteFromResultSet(safeNoteData);

                return Optional.of(safeNote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<SafeNote> findByUserId(Integer userId) {
        List<SafeNote> safeNotes = new ArrayList<>();
        String sql = "SELECT * FROM safe_note WHERE user_id=" + userId;

        try (Connection connection = SqliteConfig.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet safeNoteData = preparedStatement.executeQuery();

            while (safeNoteData.next()) {
                SafeNote sn = getSafeNoteFromResultSet(safeNoteData);
                safeNotes.add(sn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return safeNotes;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM safe_note WHERE id=" + id;

        try (Connection connection = SqliteConfig.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(SafeNote safeNote) {
        // titulo, content, tags, type, isEncrypted
        String sql = """
                UPDATE safe_note
                SET title = ?, content = ?, tags = ?, type = ?, isEncrypted = ?
                WHERE id = ?
                """;

        System.out.println("updating... " + safeNote);

        try (Connection connection = SqliteConfig.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, safeNote.getTitle());
            preparedStatement.setString(2, safeNote.getContent());
            preparedStatement.setString(3, safeNote.getTags());
            preparedStatement.setString(4, safeNote.getType());
            preparedStatement.setBoolean(5, safeNote.isEncrypted());

            preparedStatement.setInt(6, safeNote.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SafeNote getSafeNoteFromResultSet(ResultSet safeNoteData) throws SQLException {
        Integer id = safeNoteData.getInt("id");
        String title = safeNoteData.getString("title");
        String content = safeNoteData.getString("content");
        LocalDateTime createdAt = safeNoteData.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = safeNoteData.getTimestamp("updated_at").toLocalDateTime();
        String tags = safeNoteData.getString("tags");
        String type = safeNoteData.getString("type");
        boolean isEncrypted = safeNoteData.getBoolean("isEncrypted");
        Integer userId = safeNoteData.getInt("user_id");

        return new SafeNote(id, title, content, createdAt, updatedAt,
                tags, type, isEncrypted, userId);
    }

}
