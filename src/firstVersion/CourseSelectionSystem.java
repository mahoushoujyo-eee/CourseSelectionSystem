package firstVersion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CourseSelectionSystem extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btOK = new Button();
        ErrorPane errorPane = new ErrorPane("This is a testing" ,btOK);
        btOK.setOnAction(event -> primaryStage.close());

        primaryStage.setScene(new Scene(errorPane));
        primaryStage.setTitle("Test");
        primaryStage.show();
    }
}
