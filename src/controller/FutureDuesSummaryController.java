package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FutureDuesSummaryController {

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
    void onActionReturnToMain(ActionEvent event) {

    }

}
