package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;
import model.Member;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class FutureDuesMainController implements Initializable {

    //private Member member;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ImageView imageView;

    @FXML
    Label tierLabel;

    @FXML
    Label duesLabel;

    Member mainMember;

    // Calculate Age
    public static int getAge(LocalDate date){
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(date, currentDate);

        if(period.getYears() <= 0){
            Alert alert = new Alert(Alert.AlertType.WARNING); //Alert dialog box
            alert.setTitle("Warning Dialog");
            alert.setContentText("Dates cannot be set in the future");
            alert.showAndWait();
        }

        return period.getYears();

    }

    // Calculate Date user needs to be moved to next Tier
    public static String getNextTierDate(LocalDate birthdate, String futureAgeString) {
        // Convert futureAge String to an integer
        int futureAgeInt = Integer.parseInt(futureAgeString);

        // Add futureAge years to the birthdate
        LocalDate futureDate = birthdate.plusYears(futureAgeInt);

        // Format the future date to "Month day, year" format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        return futureDate.format(formatter);
    }

    // Calculate Current Tier, Next Tier, and Age at next Tier
    public static String[] getTier(int age){
        String[] tier = new String[3];
        tier[0] = ""; // Current Tier
        tier[1] = ""; // Next Tier
        tier[2] = ""; // Age at next Tier
        if (age > 0 && age <= 4){
            tier[0] = "Kid";
            tier[1] = "Youth";
            tier[2] = "5";
        }
        else if (age >= 5 && age < 14){
            tier[0] = "Youth";
            tier[1] = "Young Adult";
            tier[2] = "14";
        }
        else if (age >= 14 && age < 23){
            tier[0] = "Young Adult";
            tier[1] = "Adult";
            tier[2] = "14";
        }
        else if (age >= 23){
            tier[0] = "Adult";
            tier[1] = "Adult";
            tier[2] = "23";
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING); //Alert dialog box
            alert.setTitle("Warning Dialog");
            alert.setContentText("Invalid Age: Cannot calculate Membership details");
            alert.showAndWait();
        }
        return tier;
    }

    // Parse String input from DatePicker and format to LocalDate
    private LocalDate parseDate(String inputDate) {

        if (inputDate == null || inputDate.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING); //Alert dialog box
            alert1.setTitle("Warning Dialog");
            alert1.setContentText("Please do not leave entries blank");
            alert1.showAndWait();
            return null; // Empty input, return null
        }

        if (!inputDate.matches("[0-9/]+")) {
            return null; // Invalid characters found, return null
        }

        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("MM/d/yyyy"),
                DateTimeFormatter.ofPattern("M/dd/yyyy")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                // If parsing is successful, return the parsed date
                return LocalDate.parse(inputDate, formatter);
            } catch (DateTimeParseException ignore) {
                // Continue to the next formatter if parsing fails
            }
        }
        return null; // Return null for inputs that couldn't be parsed
    }

    // Switch to next scene and send Member object data
    private void switchToNewView(Window window, Member mainMember) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FutureDuesSummary.fxml"));
        Parent scene = loader.load();

        // Give Summary Page access to methods on this page
        FutureDuesSummaryController controller = loader.getController();
        controller.getMember(mainMember);

        Stage stage = (Stage) window;
        stage.setScene(new Scene(scene));
        stage.show();
    }

    // Handle DatePicker event when "ENTER" key is pressed after inputting a String/picking a Date
    @FXML
    void enterKey(KeyEvent event) throws DateTimeParseException{

            // Add listener to clear the value of the DatePicker Field
            datePicker.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (isNowFocused) {
                    datePicker.setValue(null); // Clear the value
                }
            });

            try {
                // Handle ENTER press
                if(event.getCode() == KeyCode.ENTER) {

                    // Post Scene Change processing:

                    // Get put from DatePicker
                    String inputDate = datePicker.getEditor().getText();

                    // Format Input (FUNCTION)
                    LocalDate birthday = parseDate(inputDate);


                    // Get additional variables

                    // Check if birthday is null
                    if (birthday == null) {
                        //Handle invalid date input on the JavaFX Application Thread
//                        Platform.runLater(() -> {
//                            // Log statements for debugging purposes
//                            System.out.println("Creating and showing an alert for invalid date input...");
//                            Alert alert = new Alert(Alert.AlertType.ERROR);
//                            alert.setTitle("Error");
//                            alert.setContentText("Please enter a valid date");
//
//                            // Show the alert
//                            alert.showAndWait();
//                        });

                        // Exit the enterKey() function
                        System.out.println("Error: Age is Null");
                        return;
                    }

                    int age = getAge(birthday);

                    // Calculate Tier List of user (FUNCTION)
                    String[] tier = getTier(age);

                    // Set variables using results from array
                    String currentTier = tier[0];
                    String nextTier = tier[1];
                    String futureAge = tier[2];

                    String futureDate = "";

                    // Calculate Date at Next Tier (FUNCTION)
                    futureDate = getNextTierDate(birthday, futureAge);

                    // Create object and data to send to new scene
                    Member mainMember = new Member(birthday, age, currentTier, nextTier, futureDate);

                        // Switch to new Scene when processing is finished
                        switchToNewView(datePicker.getScene().getWindow(), mainMember);

                }
            } catch(IOException | RuntimeException e){
                System.out.println("Error at end of Event Function: " + e.getMessage());
            }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        // Display the Insight Logo image
        File file = new File("@../../Pictures/InsightLogo.png");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);

    }

}
