package hohich;

import hohich.model.Note;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {


        Text text = new Text("Кого ты бля выберешь?");
        Label selectedLbl = new Label();

        Group groupTxt = new Group(text);

        RadioButton javaBtn = new RadioButton("Их");
        RadioButton jsBtn = new RadioButton("Нас");


        ToggleGroup group = new ToggleGroup();
        // установка группы
        javaBtn.setToggleGroup(group);
        jsBtn.setToggleGroup(group);


        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10);
        root.getChildren().addAll(groupTxt,javaBtn, jsBtn, selectedLbl);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 250, 200);

        stage.setScene(scene);
        stage.setTitle("RadioButtons in JavaFX");
        stage.show();
    }
}