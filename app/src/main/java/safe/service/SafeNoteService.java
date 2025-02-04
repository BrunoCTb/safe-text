package safe.service;

import safe.domain.SafeNote;
import safe.repository.SafeNoteRepository;

import java.util.Optional;

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

}
