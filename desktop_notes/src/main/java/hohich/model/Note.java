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

    @Expose(serialize = false, deserialize = false)
    private Map<String, Image> cachedImages;

    public Note() {
        this.id = UUID.randomUUID();
        this.textContent = "";
        this.cachedImages = new HashMap<>();
        editTime = LocalDateTime.now();
    }

    public void addFile(String name, Image image) {
        cachedImages.put(name, image);
    }

    public void deleteFile(String name) {
        cachedImages.remove(name);
    }

    public void deleteAllFiles() {
        cachedImages.clear();
    }

}
