package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Member;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FutureDuesSummaryController implements Initializable {

    Stage stage;
    private Member member;

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
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public TextFlow styleTier(String tier){
        Text textBefore = new Text("Please set the climber's membership tier to ");
        Text variable = new Text(tier);
        variable.setFill(Color.DARKGREEN);
        variable.setStyle("-fx-font-weight: bold"); // Set font weight to bold and green

        Text textAfter = new Text(".");
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(textBefore,variable,textAfter);

        return textFlow;
    }

    public TextFlow styleDues(String tier, String date){
        Text textBefore = new Text("They will age into ");
        Text variable = new Text(tier); // Insert Next Tier
        variable.setFill(Color.DARKBLUE);
        variable.setStyle("-fx-font-weight: bold"); // Set font weight to bold and green
        Text textMiddle = new Text(" tier on ");
        Text variable2 = new Text(date);
        variable2.setFill(Color.DARKBLUE); // Insert Date on Next Tier
        variable2.setStyle("-fx-font-weight: bold");
        Text textEnd = new Text(".");

        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(textBefore,variable,textMiddle,variable2,textEnd);

        return textFlow;
    }

    public void getMember(Member mainMember){
        this.member = mainMember;

        //tierLabel.setText("Please set the climber's membership tier to " + member.getCurrentTier() + ".");
        tierLabel.setGraphic(styleTier(member.getCurrentTier()));
        //duesLabel.setText("They will age into the " + member.getNextTier() + " tier on " + member.getFutureDate() + ".");
        duesLabel.setGraphic(styleDues(member.getNextTier(),member.getFutureDate()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

}
