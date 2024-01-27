package interfaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage App) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FutureDuesMain.fxml"));
        Parent calc = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/FutureDuesMain.fxml")));

        App.setTitle("Future Dues Calculator");
        App.setScene(new Scene(calc));
        App.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
