package firstVersion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorStage extends Stage {
    Text errorMessage = new Text();
    GridPane pane = new GridPane();
    Button btOK = new Button("OK");

    public ErrorStage(String errorString)
    {
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setAlignment(Pos.CENTER);


        errorMessage.setText(errorString);
        pane.add(errorMessage, 1, 1);

        btOK.setOnAction(event -> close());
        pane.add(btOK, 1, 2);


        Scene scene = new Scene(pane, 250, 200);
        setScene(scene);

        pane.widthProperty().addListener(observable -> resize());
        pane.heightProperty().addListener(observable -> resize());
    }

    private void resize()
    {
        pane.setVgap(pane.getHeight()/ 40);
        errorMessage.setFont(new Font(pane.getHeight() > pane.getWidth()? pane.getWidth(): pane.getHeight() / 10));
        btOK.setFont(new Font(pane.getHeight() > pane.getWidth()? pane.getWidth(): pane.getHeight() / 10));
        btOK.setPrefHeight(pane.getHeight() / 10);
        btOK.setPrefWidth(pane.getWidth() / 8);
    }

}
