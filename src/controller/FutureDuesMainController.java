package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Member;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class FutureDuesMainController implements Initializable {


    @FXML
    private DatePicker datePicker;

    @FXML
    private ImageView imageView;

    // Calculate Age
    public static int getAge(LocalDate date){
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(date, currentDate);

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
            tier[2] = "23"; // TO DO: Should prompt an error message "This user is already an adult
        }
        else{
            System.out.println("Error when calculating Membership Tier: Invalid Age");
        }
        return tier;
    }

    private void switchToNewView(Window window) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FutureDuesSummary.fxml"));
        Parent scene = loader.load();

        Stage stage = (Stage) window;
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void updateData(LocalDate birthday, int age, String currentTier, String nextTier, String futureDate){

        member.setBirthday(birthday);
        member.setAge(age);
        member.setCurrentTier(currentTier);
        member.setNextTier(nextTier);
        member.setFutureDate(futureDate);
    }

    private Member member;



    @FXML
    void enterKey(KeyEvent event) throws IOException {


            // Add listener to clear the value when the menu is opened
            datePicker.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (isNowFocused) {
                    datePicker.setValue(null); // Clear the value
                }
            });


            try{

                String inputDate = datePicker.getEditor().getText();

                System.out.println("Text Input: "+ inputDate);

                // Format String inputDate to LocalDate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate birthday = LocalDate.parse(inputDate, formatter);

                int age = getAge(birthday);

                // Calculate Tier List of user (FUNCTION)
                String[] tier = getTier(age);

                // Set variables using results from array
                String currentTier = tier[0];
                String nextTier = tier[1];
                String futureAge = tier[2];

                // Calculate Date at Next Tier (FUNCTION)
                String futureDate = getNextTierDate(birthday,futureAge);

                System.out.println(
                        "Current Age: " + age + System.lineSeparator() +
                                "Current Tier: " + currentTier + System.lineSeparator() +
                                "Next Tier: " + nextTier + System.lineSeparator() +
                                "Date at Next Tier: " + futureDate
                );

                if (event.getCode() == KeyCode.ENTER){
                    switchToNewView(datePicker.getScene().getWindow());
                }

            }
            catch(NullPointerException e){
                System.out.println("An error occurred: " + e.getMessage());
            }
            catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR); //Alert dialog box
                alert.setTitle("Error");
                alert.setContentText("Please enter a valid date");
                alert.showAndWait();
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
