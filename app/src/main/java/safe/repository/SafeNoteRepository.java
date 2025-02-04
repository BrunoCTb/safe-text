package safe.repository;

import safe.domain.SafeNote;

import java.util.Optional;

public interface SafeNoteRepository {

    void createTableSafeNote();

    void addSafeNote(SafeNote safeNote);

    Optional<SafeNote> findById(Integer id);
}
