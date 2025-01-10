package safe.repository;

import safe.database.SqliteConfig;
import safe.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    public List<User> findAll() {
        String sql = "SELECT * from users;";
        List<User> allUsers = new ArrayList<>();

        try (Connection connection = SqliteConfig.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                int id = data.getInt("id");
                String email = data.getString("email");
                String password = data.getString("password");
                User user = new User(id, email, password);
                allUsers.add(user);
            }

            return allUsers;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = SqliteConfig.connection()) {
            String sql = "SELECT * FROM users WHERE email = '" + email + "'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet data = preparedStatement.executeQuery();

            if (!data.next()) { return Optional.empty(); }

            int id = data.getInt("id");
            String login = data.getString("email");
            String password = data.getString("password");
            return Optional.of(new User(id, login, password));

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


}
