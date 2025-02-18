package safe.service;

import safe.domain.SafeNote;
import safe.repository.SafeNoteRepository;

import java.util.List;
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

    public List<SafeNote> findByUserId(Integer userId) {
        return safeNoteRepository.findByUserId(userId);
    }

    public void deleteById(Integer id) {
//        safeNoteRepository.deleteById(id);
    }
}
