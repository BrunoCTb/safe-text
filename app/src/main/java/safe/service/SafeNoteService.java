package safe.service;

import safe.cli.MenuCLI;
import safe.domain.SafeNote;
import safe.repository.SafeNoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SafeNoteService {

    private SafeNoteRepository safeNoteRepository;

    public SafeNoteService(SafeNoteRepository safeNoteRepository) {
        this.safeNoteRepository = safeNoteRepository;
    }

    public void createTableSafeNote() {
        safeNoteRepository.createTableSafeNote();
    }

    public Optional<SafeNote> findById(Integer id) {
        return safeNoteRepository.findById(id);
    }

    public void save(SafeNote safeNote) {
        safeNoteRepository.addSafeNote(safeNote);
    }

    public List<SafeNote> findByUserId(Integer userId) {
        return safeNoteRepository.findByUserId(userId);
    }

    public void deleteById(Integer id) {
        safeNoteRepository.deleteById(id);
    }

    public void update(SafeNote safeNote) {
        safeNoteRepository.update(safeNote);
    }

    // solicita uma lista de 'Safe note' que mostra todos os itens
    // e retorna a opção com o index que foi selecionado
    public SafeNote selectOne(List<SafeNote> allSafeNotes) {
        Scanner input = new Scanner(System.in);

        MenuCLI.clearConsole();

        for (int i=0; i<allSafeNotes.size(); i++) {
            System.out.println("[" + (i+1) + "] " + allSafeNotes.get(i).getTitle());
        }

        int opc = 0;
        while (opc <= 0 || opc > allSafeNotes.size()) {
            System.out.print("Selecione uma safe note: ");
            opc = input.nextInt();
            input.nextLine();
        }

        return allSafeNotes.get(opc-1);
    }

}
