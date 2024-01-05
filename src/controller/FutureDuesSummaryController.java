package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Member;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FutureDuesSummaryController implements Initializable {

    Stage stage;
    Member member;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //tierLabel.setText("Please set the climber's membership tier to " + member.getCurrentTier() + ".");
        //duesLabel.setText("They will age into " + member.getNextTier() + "on " + member.getFutureDate() + ".");

    }

}
