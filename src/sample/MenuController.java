package sample;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.*;
import javafx.scene.*;

import java.util.Optional;

public class MenuController {
    //open the practice menu
    public void openPractice(ActionEvent event) throws Exception {
        Stage practiceMenu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("practiceMenu.fxml"));
        practiceMenu.setTitle("Baugher Academy");
        practiceMenu.setScene(new Scene(root, 1280, 720));
        practiceMenu.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    //open the statistics page
    public void openStats(ActionEvent event) throws Exception{
        //check to see if any questions are complete
        boolean noQuestionsComplete = true;
        Question[][] questions = QuestionManager.getQuestionArray();
        for (int i = 0; i < questions.length - 1; i++) {
            for (int j = 0; j < questions[i].length - 1; j++) {
                if (questions[i][j].isComplete())
                    noQuestionsComplete = false;
            }
        }
        //if no questions are complete then display alert; otherwise open stats page
        if (noQuestionsComplete) {
            alert("Oopsie!", "You must complete at least one question to view your stats.");
        } else {
            Stage statistics = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("stats.fxml"));
            statistics.setTitle("Baugher Academy");
            statistics.setScene(new Scene(root, 1280, 720));
            statistics.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    //display a popup alert
    public void alert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,message);
        alert.setHeaderText(header);
        alert.show();
    }

    //reset question data
    public void resetData() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("WARNING");
        dialog.setHeaderText("You are about to reset all data");
        dialog.setContentText("Enter \"CONFIRM\" to continue:");
        Optional<String> confirmation = dialog.showAndWait();
        //Data validation
        if (confirmation.isPresent()) {
            if (confirmation.get().equals("CONFIRM")) {
                QuestionManager.initializeQuestionArray();
                alert("Request successful", "All data has been deleted.");
            } else {
                alert("Request cancelled", "Data has not been deleted.");
            }
        }
    }


}