package safe.repository;

import safe.domain.User;

import java.util.Optional;

public interface UserRepository{

    void createTableUsers();

    void addUser(User user);

    Optional<User> printAllUsers();
}
