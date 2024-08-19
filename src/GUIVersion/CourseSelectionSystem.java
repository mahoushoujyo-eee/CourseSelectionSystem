package GUIVersion;

import GUIVersion.GUIInteraction.ErrorStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class CourseSelectionSystem extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ErrorStage errorStage = new ErrorStage("There is a test");
        errorStage.show();
    }
}
