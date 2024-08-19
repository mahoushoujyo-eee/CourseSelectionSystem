package GUIVersion.GUIInteraction;

import com.sun.corba.se.impl.encoding.CDROutputObject;
import com.sun.xml.internal.messaging.saaj.soap.impl.TextImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class StartStage extends Stage
{
    Text text = new Text("Please choose your identity");
    GridPane pane = new GridPane();
    Button btStudent;
    Button btAdministrator;

    public StartStage(Button btStudent, Button btAdministrator)
    {
        this.btStudent = btStudent;
        this.btAdministrator = btAdministrator;

        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(20);
        //pane.setHgap(20);

        text.setFont(new Font(15));
        btAdministrator.setPrefWidth(100);
        btStudent.setPrefWidth(100);

        pane.add(text, 1, 1);
        pane.add(btAdministrator, 0, 2);
        pane.add(btStudent, 2, 2);
        setScene(new Scene(pane, 450, 300));

        pane.heightProperty().addListener(observable -> resize(btStudent, btAdministrator));
        pane.heightProperty().addListener(observable -> resize(btStudent, btAdministrator));

    }

    private void resize(Button btStudent, Button btAdministrator)
    {
        double height = pane.getHeight();
        double width = pane.getWidth();

        double min = Math.min(height, width);

        pane.setVgap(height / 15);
        text.setFont(new Font(min / 20));
        btAdministrator.setPrefWidth(width / 4.5);
        btAdministrator.setPrefHeight(height / 15);
        btStudent.setPrefWidth(width / 4.5);
        btStudent.setPrefHeight(height / 15);

    }
}
