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

    public JsonSerializer() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void serialize(Note note, String filePath) {
        saveToFile(convertToString(note), filePath);
    }

    private String convertToString(Note note){
        return gson.toJson(note);
    }

    private void saveToFile(String json, String filePath){
        //todo design filenaming that matches dir tree
        try(FileWriter fileWriter = new FileWriter(new File(filePath))) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
