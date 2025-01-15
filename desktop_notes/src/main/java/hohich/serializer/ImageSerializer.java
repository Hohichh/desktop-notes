package hohich.serializer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageSerializer implements Serializer<Image>{

    @Override
    public void serialize(Image image, String filePath){
        try{
            ImageIO.write(
                    SwingFXUtils.fromFXImage(image, null),
                    "png", new File(filePath)
            );
        } catch (IOException e){
            e.printStackTrace();
        }   
    }
}
