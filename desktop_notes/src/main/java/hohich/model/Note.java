package hohich.model;


import com.google.gson.annotations.Expose;
import javafx.scene.image.Image;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Data
public class Note {
    private UUID id;
    private String textContent;
    private LocalDateTime editTime;
    private List<String> fileNames;

    @Expose(serialize = false, deserialize = false)
    private List<Image> cachedImages = new ArrayList<>();

    public Note() {
        this.id = UUID.randomUUID();
        this.textContent = "";
        this.fileNames = new ArrayList<>();
        editTime = LocalDateTime.now();
    }

    public void addFileName(String fileName) {
        fileNames.add(fileName);
    }

    public void deleteFileName(String fileName) {
        fileNames.remove(fileName);
    }

    public void deleteAllFileNames() {
        fileNames.clear();
    }

    public void addFile(Image image) {
        cachedImages.add(image);
    }

    public void deleteFile(Image image) {
        cachedImages.remove(image);
    }

    public void deleteAllFiles() {
        fileNames.clear();
    }

}
