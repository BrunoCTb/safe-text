package safe.cli;

import java.util.Scanner;

public class MenuCLI {
    Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        System.out.print("""
                \n<==== Menu principal ====>
                [1] Criar Safe Note
                [2] Visualizar minhas Safe Notes
                [3] Deletar uma Safe Note
                [4] Atualizar Uma safe Note
                [5] Trocar de conta
                [6] Sair da aplicação
                >>\s""");
    }

    public static void clearConsole() {
        for (int i=0; i<30; i++) {
            System.out.println();
        }
    }

}
