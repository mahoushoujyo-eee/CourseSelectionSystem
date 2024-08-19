package GUIVersion.GUIInteraction.AdministratorStage;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdministratorRegisterStage extends Stage
{
    Button btRegister;
    GridPane pane = new GridPane();
    Text accountText = new Text("Account: ");
    Text passwordText = new Text("Password:");
    TextField account = new TextField();
    TextField password = new TextField();

    public AdministratorRegisterStage(Button btRegister)
    {
        this.btRegister = btRegister;

        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(20);

        pane.add(accountText, 0, 0);
        pane.add(passwordText, 0, 1);
        pane.add(account, 1, 0);
        pane.add(password, 1, 1);
        pane.add(this.btRegister, 1, 2);

        GridPane.setHalignment(this.btRegister, HPos.RIGHT);

        setScene(new Scene(pane));
    }

    public String getRegisterAccount()
    {
        return account.getText();
    }

    public String getRegisterPassword()
    {
        return password.getText();
    }
}
