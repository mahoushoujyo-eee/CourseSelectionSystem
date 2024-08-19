package firstVersion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CourseSelectionSystem extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ErrorStage errorStage = new ErrorStage("There is a test");
        errorStage.show();
    }
}
