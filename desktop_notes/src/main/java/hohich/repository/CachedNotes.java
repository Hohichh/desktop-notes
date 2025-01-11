package hohich.repository;

import hohich.model.Note;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
class CachedNotes {
    private List<Note> notes;

    public CachedNotes(){
        notes = new ArrayList<>();
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public Note getNote(UUID id) {
        return notes.stream().filter(note -> note
                        .getId()
                        .equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteNote(UUID id) {
        notes.removeIf(note -> note.getId().equals(id));
    }

    public void deleteAllNotes(){
        notes.clear();
    }
}
