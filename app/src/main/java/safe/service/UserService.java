package safe.service;

import safe.domain.User;
import safe.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.addUser(user);
    }

    public void createTableUsers() {
        userRepository.createTableUsers();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
