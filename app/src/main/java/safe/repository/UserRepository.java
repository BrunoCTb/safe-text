package safe.repository;

import safe.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository{

    void createTableUsers();

    void addUser(User user);

    List<User> findAll();

    Optional<User> findByEmail(String email);

}
