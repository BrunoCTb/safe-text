package safe;

import safe.domain.User;
import safe.repository.UserRepository;
import safe.repository.UserRepositoryImpl;
import safe.service.UserService;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(1, "test@gmail.com", "123");
        User user2 = new User(2, "zzz@gmail.com", "123");

        Scanner input = new Scanner(System.in);
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserService(userRepository);

        userService.createTableUsers();

        System.out.println("=== Log In ===");

        String email;
        while (true) {
            System.out.print("Login: ");
            email = input.next();
            System.out.print("Senha: ");
            String password = input.next();

            Optional<User> userFound = userService.findByEmail(email);

            if (userFound.isPresent() && userFound.get().getPassword().equals(password)) {
                break;
            }

            System.out.println("Login ou senha inválidos!\n=======");
        }
        System.out.println("Olá! " + email);

    }
}