package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class PracticeMenuController {

    //open the practice window with topic set to algebra
    public void openAlgebra(ActionEvent event) throws Exception {
        //check to see if all questions are complete
        Question[] algebraArray = QuestionManager.getQuestionArrayByTopic("algebra");
        boolean canEnter = false;
        for (int i = 0; i < algebraArray.length; i++) {
            if (!algebraArray[i].isComplete()) {
                canEnter = true;
            }
        }
        if(canEnter) {
            //open practice window
            Main.setTopic("algebra");
            Stage practice = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("practice.fxml"));
            practice.setTitle("Baugher Academy");
            practice.setScene(new Scene(root, 1280, 720));
            practice.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            //display alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You have completed all of the Algebra questions.");
            alert.setHeaderText("Please choose another topic");
            alert.show();
        }
    }

    //open the practice window with topic set to functions
    public void openFunctions(ActionEvent event) throws Exception {
        Question[] functionsArray = QuestionManager.getQuestionArrayByTopic("functions");
        boolean canEnter = false;
        for (int i = 0; i < functionsArray.length; i++) {
            if (!functionsArray[i].isComplete()) {
                canEnter = true;
            }
        }
        if(canEnter) {
            Main.setTopic("functions");
            Stage practice = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("practice.fxml"));
            practice.setTitle("Baugher Academy");
            practice.setScene(new Scene(root, 1280, 720));
            practice.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You have completed all of the Functions and Equations questions.");
            alert.setHeaderText("Please choose another topic");
            alert.show();
        }
    }

    //open the practice window with topic set to trigonometry
    public void openTrig(ActionEvent event) throws Exception {
        Question[] trigArray = QuestionManager.getQuestionArrayByTopic("trig");
        boolean canEnter = false;
        for (int i = 0; i < trigArray.length; i++) {
            if (!trigArray[i].isComplete()) {
                canEnter = true;
            }
        }
        if(canEnter) {
            Main.setTopic("trig");
            Stage practice = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("practice.fxml"));
            practice.setTitle("Baugher Academy");
            practice.setScene(new Scene(root, 1280, 720));
            practice.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You have completed all of the Circular Functions and Trigonometry questions.");
            alert.setHeaderText("Please choose another topic");
            alert.show();
        }
    }

    //open the practice window with topic set to vectors
    public void openVectors(ActionEvent event) throws Exception {
        Question[] vectorsArray = QuestionManager.getQuestionArrayByTopic("vectors");
        boolean canEnter = false;
        for (int i = 0; i < vectorsArray.length; i++) {
            if (!vectorsArray[i].isComplete()) {
                canEnter = true;
            }
        }
        if(canEnter) {
            Main.setTopic("vectors");
            Stage practice = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("practice.fxml"));
            practice.setTitle("Baugher Academy");
            practice.setScene(new Scene(root, 1280, 720));
            practice.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You have completed all of the Vectors questions.");
            alert.setHeaderText("Please choose another topic");
            alert.show();
        }    }

    //open the practice window with topic set to statistics
    public void openStats(ActionEvent event) throws Exception {
        Question[] statsArray = QuestionManager.getQuestionArrayByTopic("stats");
        boolean canEnter = false;
        for (int i = 0; i < statsArray.length; i++) {
            if (!statsArray[i].isComplete()) {
                canEnter = true;
            }
        }
        if(canEnter) {
            Main.setTopic("stats");
            Stage practice = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("practice.fxml"));
            practice.setTitle("Baugher Academy");
            practice.setScene(new Scene(root, 1280, 720));
            practice.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You have completed all of the Probability and Statistics questions.");
            alert.setHeaderText("Please choose another topic");
            alert.show();
        }
    }

    //open the practice window with topic set to calculus
    public void openCalculus(ActionEvent event) throws Exception {
        Question[] calculusArray = QuestionManager.getQuestionArrayByTopic("calculus");
        boolean canEnter = false;
        for (int i = 0; i < calculusArray.length; i++) {
            if (!calculusArray[i].isComplete()) {
                canEnter = true;
            }
        }
        if(canEnter) {
            Main.setTopic("calculus");
            Stage practice = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("practice.fxml"));
            practice.setTitle("Baugher Academy");
            practice.setScene(new Scene(root, 1280, 720));
            practice.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You have completed all of the Calculus questions.");
            alert.setHeaderText("Please choose another topic");
            alert.show();
        }
    }

    //return to previous page
    public void goBack(ActionEvent event) throws Exception{
        Stage main = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        main.setTitle("Baugher Academy");
        main.setScene(new Scene(root, 1280, 720));
        main.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
