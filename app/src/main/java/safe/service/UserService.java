package safe.service;

import safe.Dto.LoginDTO;
import safe.domain.User;
import safe.repository.UserRepository;

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

    public Optional<User> printAllUsers() {
        return userRepository.printAllUsers();
    }

}
