package hohich.model;


import javafx.scene.image.Image;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Note {
    private UUID id;
    private String textContent;
    private LocalDateTime editTime;
    private final List<String> fileNames;

    public Note(){
        this.id = UUID.randomUUID();
        this.textContent = "";
        this.fileNames = new ArrayList<>();
        editTime = LocalDateTime.now();
    }

    public void addFile(String fileName) {
        fileNames.add(fileName);
    }

    public void removeFile(int index) {
        fileNames.remove(index);
    }

    public void removeFile(String fileName) {
        fileNames.remove(fileName);
    }
}
