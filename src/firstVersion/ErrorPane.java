package firstVersion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ErrorPane extends VBox {
    Text errorMessage = new Text();

    public ErrorPane(String error, Button btOK)
    {
        errorMessage.setText(error);

        btOK.setText("OK");
        getChildren().addAll(errorMessage, btOK);
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(10, 10, 10, 10));

    }
}
