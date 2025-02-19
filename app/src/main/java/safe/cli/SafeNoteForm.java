package safe.cli;

import safe.domain.SafeNote;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class SafeNoteForm {

    private Scanner input = new Scanner(System.in);

    public SafeNote createSafeNoteForm() {
        System.out.print("Título: ");
        String title = input.nextLine();
        System.out.print("Conteúdo: ");
        String content = input.nextLine();

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = createdAt;

        System.out.print("Tags (opcional, separadas por vírgula): ");
        String tags = input.nextLine();

        System.out.print("Tipo (opcional): ");
        String type = input.nextLine();

        System.out.print("Criptografado [0 não (padrão) | 1 sim]: ");
        boolean isEncrypted = false;
        if (input.nextLine().trim().equals("1")) { isEncrypted = true; }

        SafeNote safeNote = new SafeNote(title.trim(), content.trim(), tags.trim(), type.trim(), isEncrypted);
        safeNote.setCreatedAt(createdAt);
        safeNote.setUpdatedAt(updatedAt);

        return safeNote;
    }

    public SafeNote updateSafeNoteForm(SafeNote safeNote) {
        MenuCLI.clearConsole();

        System.out.println("----- ATUALIZANDO A SAFE NOTE '"+ safeNote.getTitle() + "' -----");

        System.out.print("Título (Deixe em branco para manter): ");
        String title = input.nextLine();
        if (!title.trim().isEmpty()) {
            safeNote.setTitle(title);
        }

        System.out.print("Conteúdo (Deixe em branco para manter): ");
        String content = input.nextLine();
        if (!content.trim().isEmpty()) {
            safeNote.setContent(content);
        }

        System.out.print("Tags - separe por vírgula (Deixe em branco para manter): ");
        String tags = input.nextLine();
        if (!tags.trim().isEmpty()) {
            safeNote.setTags(tags);
        }

        System.out.print("Tipo (Deixe em branco para manter): ");
        String type = input.nextLine();
        if (!type.trim().isEmpty()) {
            safeNote.setType(type);
        }

        System.out.print("Criptograr (Deixe em branco para manter | 1 para SIM | 0 para NÃO): ");
        String encrypt = input.nextLine();
        // se for 0 muda para false, 1 para true e nenhum dos casos nao altera o que ja esta
        if (encrypt.trim().equals("0") || encrypt.trim().equals("1")) {
            safeNote.setEncrypted(encrypt.trim().equals("1"));
        }

        return safeNote;
    }

}
