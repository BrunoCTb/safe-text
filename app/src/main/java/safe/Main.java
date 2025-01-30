package safe;

import safe.cli.MenuCLI;
import safe.domain.User;
import safe.repository.UserRepository;
import safe.repository.UserRepositoryImpl;
import safe.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(1, "test@gmail.com", "123");
        User user2 = new User(2, "zzz@gmail.com", "123");

        boolean isLog = false;
        String userLogged = null;

        Scanner input = new Scanner(System.in);
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserService(userRepository);

        userService.createTableUsers();

        // visualizacao
        List<User> all = userService.findAll();
        System.out.println(all);
        //


        while (true) {
            // caso nao esteja logado
            while (!isLog) {
                System.out.println("=== Log In ===");
                System.out.print("Login: ");
                String email = input.next();
                System.out.print("Senha: ");
                String password = input.next();

                if (userService.loginIsValid(email, password)) {
                    userLogged = email;
                    isLog = true;
                    break;
                }

                System.out.println("Login ou senha inválidos!te");
            }
            // caso logado
            System.out.println("\n\n\n\n\n\n\n\n<==== Olá! " + userLogged + " ====>");
            MenuCLI.mainMenu();
            input.next();

        }
    }
}