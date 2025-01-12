package hohich.repository;

import hohich.model.Note;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
class CachedNotes {
    private List<Note> notes;
    private final int  MAX_CACHE_SIZE = 10;

    public CachedNotes(){
        notes = new ArrayList<>();
    }

    public void setNotes(List<Note> notes){
        if(notes.size() > MAX_CACHE_SIZE){
            throw new IllegalArgumentException("Maximum cache size reached");
        }
        this.notes = notes;
    }

    public void addNote(Note note) {
        if (!sizeCheck()){
            notes.removeFirst();
        }
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

    private boolean sizeCheck() {
        if (notes.size() == MAX_CACHE_SIZE) {
            return false;
        }
        return true;
    }
}
