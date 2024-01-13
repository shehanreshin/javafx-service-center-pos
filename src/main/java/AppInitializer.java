import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("./view/login-page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("   E&E Service Center"); //spaces for margin
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
