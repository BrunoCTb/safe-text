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

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(1, "test@gmail.com", "123");
        User user2 = new User(2, "zzz@gmail.com", "123");

        boolean isLog = false;
        User currentUser = null;

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
                String email = input.nextLine();
                System.out.print("Senha: ");
                String password = input.nextLine();

                if (userService.loginIsValid(email, password)) {
                    isLog = true;
                    currentUser = userService.findByEmail(email).get();
                    break;
                }

                System.out.println("Login ou senha inválidos!te");
            }

            // caso logado
            System.out.println("\n\n\n\n\n\nOlá! " + currentUser.getEmail() + "");
            MenuCLI.mainMenu();
            String mainMenuOpt = input.nextLine();

            // opcoes do menu principal
            switch (mainMenuOpt) {
                case "1": // criar safe note (mandar para um form)
                    SafeNoteForm safeNoteForm = new SafeNoteForm();
                    SafeNote safeNoteCreated = safeNoteForm.createSafeNoteForm();

                    // setar user que criou a safe note
                    safeNoteCreated.setUserId(currentUser.getId());
                    safeNoteService.save(safeNoteCreated);
                    break;
                case "2": // visualizar as safe notes
                    List<SafeNote> byUserId = safeNoteService.findByUserId(currentUser.getId());
                    for (SafeNote sn : byUserId) {
                        System.out.println(sn);
                    }
                    break;
                case "3":
                    isLog = false;
                    System.out.println("\n\n\n\n\n\n");
                    break;
                case "4":
                    System.exit(0);
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
}