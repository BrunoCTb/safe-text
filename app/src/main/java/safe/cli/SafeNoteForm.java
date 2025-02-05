package safe.cli;

import safe.domain.SafeNote;

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
        if (input.next().trim().equals("1")) { isEncrypted = true; }

        SafeNote safeNote = new SafeNote(title.trim(), content.trim(), tags.trim(), type.trim(), isEncrypted);
        safeNote.setCreatedAt(createdAt);
        safeNote.setUpdatedAt(updatedAt);

        return safeNote;
    }

}
