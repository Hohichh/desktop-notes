package hohich.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hohich.model.Note;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonSerializer implements Serializer<Note>{
    private final Gson gson;
    @Setter
    @Getter
    private File file;

    public JsonSerializer() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void serialize(Note note) {
        saveToFile(convertToString(note));
    }

    private String convertToString(Note note){
        return gson.toJson(note);
    }

    private void saveToFile(String json){
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
