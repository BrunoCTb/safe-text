package safe;

import safe.cli.MenuCLI;
import safe.cli.SafeNoteForm;
import safe.domain.SafeNote;
import safe.domain.User;
import safe.repository.SafeNoteRepository;
import safe.repository.SafeNoteRepositoryImpl;
import safe.repository.UserRepository;
import safe.repository.UserRepositoryImpl;
import safe.service.SafeNoteService;
import safe.service.UserService;

import java.time.LocalDateTime;
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

        SafeNoteRepository safeNoteRepository = new SafeNoteRepositoryImpl();
        SafeNoteService safeNoteService = new SafeNoteService(safeNoteRepository);

        userService.createTableUsers();
        safeNoteService.createTableSafeNote();

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
            System.out.println("\n\n\n\n\n\n\n\nOlá! " + userLogged + "");
            MenuCLI.mainMenu();
            int mainMenuOpt = input.nextInt();

            switch (mainMenuOpt) {
                case 1:
                    SafeNoteForm safeNoteForm = new SafeNoteForm();
                    SafeNote safeNoteCreated = safeNoteForm.createSafeNoteForm();
                    // setar user que criou a safe note
                    User user = userService.findByEmail(userLogged).get();
                    safeNoteCreated.setUserId(user.getId());
                    safeNoteService.save(safeNoteCreated);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
}