package hohich.deserializer;

import javafx.scene.image.Image;

public class ImageDeserializer implements Deserializer<Image> {

    @Override
    public Image deserialize(String resource) {
        return new Image("file:" + resource);
    }
}
