package interfaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage mainPage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FutureDuesMain.fxml"));
        Parent calc = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/FutureDuesMain.fxml")));

        mainPage.setTitle("Future Dues Calculator");
        mainPage.setScene(new Scene(calc));
        mainPage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
