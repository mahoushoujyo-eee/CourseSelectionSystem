package GUIVersion.GUIInteraction.AdministratorStage;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdministratorLogInStage extends Stage
{
    Text accountText = new Text("Account: ");
    Text passwordText = new Text("Password:");
    TextField account = new TextField();
    TextField password = new TextField();
    Button btLogIn;
    Button btToRegister;
    GridPane pane = new GridPane();

    public AdministratorLogInStage(Button btLogIn, Button btToRegister)
    {
        this.btLogIn = btLogIn;
        this.btToRegister = btToRegister;

        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(20);

        pane.add(accountText, 0, 0);
        pane.add(passwordText, 0, 1);
        pane.add(account, 1, 0);
        pane.add(password, 1, 1);
        pane.add(this.btLogIn, 0, 2);
        pane.add(this.btToRegister, 1, 2);

        GridPane.setHalignment(this.btLogIn, HPos.CENTER);
        GridPane.setHalignment(this.btToRegister, HPos.RIGHT);

        setScene(new Scene(pane, 250, 200));
        pane.widthProperty().addListener(observable -> resize());
        pane.heightProperty().addListener(observable -> resize());
    }

    private void resize()
    {
        double width = pane.getWidth();
        double height = pane.getHeight();
        double min = Math.min(width, height);

        pane.setVgap(height / 10);

        double setHeight = height / 10;
        int setColumn = (int) width / 10;
        account.setPrefHeight(setHeight);
        account.setPrefColumnCount(setColumn);
        password.setPrefHeight(setHeight);
        password.setPrefColumnCount(setColumn);

        Font font = new Font(min / 20);
        accountText.setFont(font);
        passwordText.setFont(font);
        btLogIn.setFont(font);
        btToRegister.setFont(font);

    }

    public String getLogInAccount()
    {
        return account.getText();
    }

    public String getLogInPassword()
    {
        return password.getText();
    }
}
