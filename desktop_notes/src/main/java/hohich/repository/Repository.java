package hohich.repository;

import hohich.deserializer.Deserializer;
import hohich.deserializer.ImageDeserializer;
import hohich.model.Note;
import hohich.serializer.Serializer;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Map;
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
        String filePath = PATH_TO_NOTE + id.toString() + "/";
        if ((tempNote = notes.getNote(id)) != null) {
            return tempNote;
        }
        tempNote = noteDeserializer.deserialize(filePath + id.toString() + ".json");

        for (Map.Entry<String, Image> file : tempNote.getCachedImages().entrySet()) {
            Image image = imageDeserializer.deserialize(file.getKey());
            tempNote.addFile(file.getKey(), image);
        }

        return tempNote;
    }

    public void createNote(Note note) {
        String filePath = PATH_TO_NOTE + note.getId().toString() + "/";
        notes.addNote(note);
        noteSerializer.serialize(note, filePath + note.getId().toString() + ".json");

        for (Map.Entry<String, Image> file : note.getCachedImages().entrySet()){
           imageSerializer.serialize(file.getValue(), filePath + "images" + file.getKey());
        }
    }

    public void updateNote(Note note) {
        String filePath = PATH_TO_NOTE + note.getId().toString() + "/";
        Note tempNote = notes.getNote(note.getId());
        if (tempNote == null) {
            tempNote = noteDeserializer.deserialize( filePath + note.getId().toString() + ".json");
            tempNote.setCachedImages(note.getCachedImages());
            tempNote.setTextContent(note.getTextContent());
            tempNote.setEditTime(LocalDateTime.now());
        }
        noteSerializer.serialize(tempNote, PATH_TO_NOTE + note.getId().toString()
                + "/" + note.getId().toString() + ".json");
        for(Map.Entry<String, Image> file : tempNote.getCachedImages().entrySet()){
            imageSerializer.serialize(file.getValue(), file.getKey());
        }
    }

    public void deleteNote(Note note) throws FileNotFoundException {
        String path = PATH_TO_NOTE + note.getId() + "/";
        File noteDir = new File(path);
        File noteFile = new File(noteDir, note.getId() + ".json");

        if (!noteFile.exists() || !noteFile.delete()) {
            throw new FileNotFoundException("Failed to delete note file: " + noteFile.getPath());
        }

        if (!deleteDirectory(noteDir)) {
            throw new FileNotFoundException("Failed to delete note directory: " + noteDir.getPath());
        }

        notes.deleteNote(note.getId());

    }

    private boolean deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!deleteDirectory(file)) {
                        return false;
                    }
                }
            }
        }
        return directory.delete();
    }


    public void deleteAllNotes() throws FileNotFoundException {
        if (!deleteDirectory(new File(PATH_TO_NOTE))){
            throw new FileNotFoundException("Failed to delete notes in 'notes'");
        }
        notes.deleteAllNotes();
    }

}
