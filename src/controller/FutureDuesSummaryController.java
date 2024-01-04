package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FutureDuesSummaryController {

    Stage stage;

    @FXML
    private Button closeButton;

    @FXML
    private Label duesLabel;

    @FXML
    private Button restartButton;

    @FXML
    private Label tierLabel;

    @FXML
    void onActionCloseApplication(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionReturnToMain(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/FutureDuesMain.fxml"));
        loader.load();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot(); //Switches to Tables
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
