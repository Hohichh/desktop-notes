package hohich.repository;

import hohich.deserializer.Deserializer;
import hohich.model.Note;
import hohich.serializer.Serializer;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Repository {
    private final Serializer<Note> NoteSerializer;
    private final Deserializer<Note> NoteDeserializer;
    private final Serializer ImageSerializer;
    private final Deserializer ImageDeserializer;
    @Getter
    @Setter
    private CachedNotes notes;

    public Repository(Serializer<Note> NoteSerializer,
                      Deserializer<Note> NoteDeserializer,
                      Serializer<Image> ImageSerializer,
                      Deserializer<Image> ImageDeserializer) {
        this.NoteSerializer = NoteSerializer;
        this.NoteDeserializer = NoteDeserializer;
        this.ImageSerializer = ImageSerializer;
        this.ImageDeserializer = ImageDeserializer;
        this.notes = new CachedNotes();
    }

    public Note getNote(UUID id) {
        Note tempNote;
        if ((tempNote = notes.getNote(id)) != null) {
            return tempNote;
        }
        return null;
    }

}
