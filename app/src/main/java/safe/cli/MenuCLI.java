package safe.cli;

import java.util.Scanner;

public class MenuCLI {
    Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        System.out.print("""
                <==== Menu principal ====>
                [1] Criar Safe Note
                [2] Visualizar minhas Safe Notes
                [3] Trocar de conta
                [4] Sair da aplicação
                >>\s""");
    }


}
