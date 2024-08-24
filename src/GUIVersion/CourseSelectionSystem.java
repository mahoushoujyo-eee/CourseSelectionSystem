package GUIVersion;

import GUIVersion.GUIInteraction.AdministratorStage.AdministratorLogInStage;
import GUIVersion.GUIInteraction.AdministratorStage.AdministratorRegisterStage;
import GUIVersion.GUIInteraction.AdministratorStage.AdministratorStage;
import GUIVersion.GUIInteraction.ErrorStage;
import GUIVersion.GUIInteraction.StartStage;
import GUIVersion.GUIInteraction.StudentStage.StudentStage;
import ReviewConsole.Business.AdministratorBusiness;
import ReviewConsole.Data.Administrator;
import ReviewConsole.Main;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class CourseSelectionSystem extends Application
{
    static Button btStudent = new Button("Student");
    static Button btAdministrator = new Button("Administrator");
    static StartStage startStage = new StartStage(btStudent, btAdministrator);

    static Button btAdministratorLogIn = new Button("LogIn");
    static Button btAdministratorTurnToRegister = new Button("Register");
    static AdministratorLogInStage administratorLogInStage = new AdministratorLogInStage(btAdministratorLogIn, btAdministratorTurnToRegister);

    static Button btAdministratorRegister = new Button("Register");
    static AdministratorRegisterStage administratorRegisterStage = new AdministratorRegisterStage(btAdministratorRegister);

    static AdministratorStage administratorStage = new AdministratorStage();

    static StudentStage studentStage = new StudentStage();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Main.initialize();

        startStage.show();

        setOnAction();
        setOnCloseRequest();

        Main.close();
    }

    public static void setOnAction()
    {
        btAdministrator.setOnAction(event ->
        {
            startStage.close();
            administratorLogInStage.show();
        });
        btStudent.setOnAction(event ->
        {
            startStage.close();
            studentStage.show();
        });
        btAdministratorTurnToRegister.setOnAction(event ->
        {
            administratorLogInStage.close();
            administratorRegisterStage.show();
        });
        btAdministratorLogIn.setOnAction(event ->
        {
            if (!AdministratorBusiness.judgeAccountAndPasswordMatch(new Administrator(administratorLogInStage.getLogInAccount(), administratorLogInStage.getLogInPassword())))
            {
                new ErrorStage("Your account don't match your password").show();
            }
            else
            {
                administratorLogInStage.close();
                administratorStage.show();
            }
        });
        btAdministratorRegister.setOnAction(event ->
        {
            if (AdministratorBusiness.accountExists(new Administrator(administratorRegisterStage.getRegisterAccount(), administratorRegisterStage.getRegisterPassword())))
                new ErrorStage("Your input account is already existed").show();
            else if (!AdministratorBusiness.judgeAccountLegal(administratorRegisterStage.getRegisterAccount()))
                new ErrorStage("Your account is illegal").show();
            else if (!AdministratorBusiness.judgePasswordLegal(administratorRegisterStage.getRegisterPassword()))
                new ErrorStage("Your password is illegal").show();
            else
            {
                AdministratorBusiness.addAdministrator(new Administrator(administratorRegisterStage.getRegisterAccount(), administratorRegisterStage.getRegisterPassword()));
                administratorRegisterStage.close();
                administratorLogInStage.show();
            }
        });
    }

    public static void setOnCloseRequest()
    {
        startStage.setOnCloseRequest(event ->
        {
            try
            {
                Main.close();
            } catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        });
        administratorStage.setOnCloseRequest(event ->
        {
            try
            {
                Main.close();
            } catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        });
        administratorLogInStage.setOnCloseRequest(event ->
        {
            try
            {
                Main.close();
            } catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        });
        administratorRegisterStage.setOnCloseRequest(event ->
        {
            try
            {
                Main.close();
            } catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        });
        studentStage.setOnCloseRequest(event ->
        {
            try
            {
                Main.close();
            } catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        });

    }
}
