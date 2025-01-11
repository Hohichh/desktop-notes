package hohich;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    private Button btn;

    @FXML
    private void click(ActionEvent event) {
        btn.setText("Clicked");
    }

}
