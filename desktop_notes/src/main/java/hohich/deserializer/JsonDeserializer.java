package hohich.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hohich.model.Note;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonDeserializer implements Deserializer<Note> {
    private final Gson gson;

    public JsonDeserializer() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public Note deserialize(String filePath) {
        return jsonToNote(loadFromFile(filePath));
    }

    private Note jsonToNote(String json) {
        return gson.fromJson(json, Note.class);
    }

    private String loadFromFile(String filePath) {
        File file = new File(filePath);
        StringBuilder jsonString = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null){
                jsonString.append(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return jsonString.toString();
    }
}
