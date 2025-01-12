package hohich.repository;

import hohich.deserializer.Deserializer;
import hohich.model.Note;
import hohich.serializer.Serializer;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

public class Repository {
    private final String PATH_TO_NOTE = "/notes/";
    private final Serializer<Note> noteSerializer;
    private final Deserializer<Note> noteDeserializer;
    private final Serializer<Image> imageSerializer;
    private final Deserializer<Image> imageDeserializer;
    @Getter
    @Setter
    private CachedNotes notes;


    public Repository(Serializer<Note> NoteSerializer,
                      Deserializer<Note> NoteDeserializer,
                      Serializer<Image> ImageSerializer,
                      Deserializer<Image> ImageDeserializer) {
        this.noteSerializer = NoteSerializer;
        this.noteDeserializer = NoteDeserializer;
        this.imageSerializer = ImageSerializer;
        this.imageDeserializer = ImageDeserializer;
        this.notes = new CachedNotes();
    }

    public Note getNote(UUID id) {
        Note tempNote;
        if ((tempNote = notes.getNote(id)) != null) {
            return tempNote;
        }
        tempNote = noteDeserializer.deserialize(PATH_TO_NOTE + id.toString() + "/"
                + id.toString() + ".json");

        for (String fileName : tempNote.getFileNames()) {
            //todo здесь сериализатором картинок достаем их и суем в tempNote.cashedImages
        }

        return tempNote;
    }

    public void createNote(Note note) {
        notes.addNote(note);
        noteSerializer.serialize(note, PATH_TO_NOTE + note.getId().toString()
                + "/" + note.getId().toString() + ".json");
        //todo cохраняем картинки с помощью сериализатора картинок
    }

    public void updateNote(Note note) {
        Note tempNote = notes.getNote(note.getId());
        if (tempNote == null) {
            tempNote = noteDeserializer.deserialize(PATH_TO_NOTE + note.getId().toString()
                    + "/" + note.getId().toString() + ".json");
            tempNote.setFileNames(note.getFileNames());
            tempNote.setCachedImages(note.getCachedImages());
            tempNote.setTextContent(note.getTextContent());
            tempNote.setEditTime(LocalDateTime.now());
        }
        noteSerializer.serialize(tempNote, PATH_TO_NOTE + note.getId().toString()
                + "/" + note.getId().toString() + ".json");
        //todo сохранить новые картинки
    }

    public void deleteNote(Note note) {
        String path = PATH_TO_NOTE + note.getId().toString() + "/" + note.getId().toString() + ".json";
        notes.deleteNote(note.getId());
        if(new File(path).delete()) {
            //всё нормально
        }
        //todo удалить картинки
    }

    public void deleteAllNotes(Note note) {
        //todo удалить рекурсивно все директории в notes
    }

}
