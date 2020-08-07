package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //initialize variable to store the current topic
    private static String currentTopic = "";
    public static void setTopic(String str){
        currentTopic = str;
    }
    public static String getTopic(){
        return currentTopic;
    }

    //load the menu page
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Baugher Academy");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        QuestionManager.initializeQuestionArray();
    }

    //start the program
    public static void main(String[] args) {
        launch(args);
    }
}
