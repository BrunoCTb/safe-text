package safe.repository;

import safe.database.SqliteConfig;
import safe.domain.User;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void createTableUsers() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY," +
                "email TEXT not null," +
                "password TEXT not null" +
                ");";

        try (Connection connection = SqliteConfig.connection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (id, email, password) VALUES (?, ?, ?)";

        try (Connection connection = SqliteConfig.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> printAllUsers() {
        String sql = "SELECT * from users;";

        try (Connection connection = SqliteConfig.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                int id = data.getInt("id");
                String email = data.getString("email");
                String password = data.getString("password");
                System.out.println(id + " | " + email + " | " + password);
            }

            return null;
        } catch (SQLException ex) {
            return null;
        }
    }


}
