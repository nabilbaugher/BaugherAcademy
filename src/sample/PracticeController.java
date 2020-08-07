package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.util.Arrays;

public class PracticeController {
    //initialize FXML elements
    @FXML
    private ImageView questionAnswerImage;
    @FXML
    private Button viewAnswer;
    @FXML
    private Button viewQuestion;
    @FXML
    private Button complete;
    @FXML
    private Button next;
    @FXML
    private Button grading;

    //initialize instance variables
    private String[] questionPaths;
    private String[] answerPaths;
    private String currentTopic;
    private int index;
    private File questionFolder;
    private File answerFolder;

    public void initialize() {
        //based on the current topic, store the location of the question and answer folders
        currentTopic = Main.getTopic();
        questionFolder = null;
        answerFolder = null;
        switch(currentTopic){
            case "algebra":
                questionFolder = new File("res/img/questions/algebra");
                answerFolder = new File("res/img/answers/algebra");
                break;
            case "functions":
                questionFolder = new File("res/img/questions/functions");
                answerFolder = new File("res/img/answers/functions");
                break;
            case "trig":
                questionFolder = new File("res/img/questions/trig");
                answerFolder = new File("res/img/answers/trig");
                break;
            case "vectors":
                questionFolder = new File("res/img/questions/vectors");
                answerFolder = new File("res/img/answers/vectors");
                break;
            case "stats":
                questionFolder = new File("res/img/questions/stats");
                answerFolder = new File("res/img/answers/stats");
                break;
            case "calculus":
                questionFolder = new File("res/img/questions/calculus");
                answerFolder = new File("res/img/answers/calculus");
                break;
        }

        //populate algebraPaths with the files in algebraFolder
        questionPaths = questionFolder.list((dir, name) -> !name.equals(".DS_Store"));
        answerPaths = answerFolder.list((dir, name) -> !name.equals(".DS_Store"));
        //sort files by name to ensure questions and answers correspond
        Arrays.sort(questionPaths);
        Arrays.sort(answerPaths);
        //keep track of the current index
        index = -1;
        toNextQuestion();
    }

    //center an image in its ImageView
    public static void centerImg(ImageView imgView) {
        Image img = imgView.getImage();
        if (img != null) {
            double width = 0, height = 0;
            double horizontalRatio = imgView.getFitWidth() / img.getWidth();
            double verticalRatio = imgView.getFitHeight() / img.getHeight();
            double reductionMultiplier = 0;

            if(horizontalRatio <= verticalRatio) {
                reductionMultiplier = horizontalRatio;
            } else {
                reductionMultiplier = verticalRatio;
            }
            width = img.getWidth() * reductionMultiplier;
            height = img.getHeight() * reductionMultiplier;
            imgView.setX((imgView.getFitWidth() - width) / 2);
            imgView.setY((imgView.getFitHeight() - height) / 2);

        }
    }

    //display the question image instead of the answer image
    public void displayQuestion() {
        //retrieve question image from array and display it
        try {
            File questionLocation = new File(questionFolder.getPath() + "/" + questionPaths[index]);
            Image questionImg = new Image(questionLocation.toURI().toString());
            questionAnswerImage.setImage(questionImg);
            centerImg(questionAnswerImage);
            complete.setDisable(true);
            next.setDisable(true);
            viewAnswer.setDisable(false);
            viewQuestion.setDisable(true);
            grading.setDisable(true);
        } catch (Exception e){
            //if there are no more incomplete questions, display alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You have completed all of the " + currentTopic + " questions.");
            alert.setHeaderText("Congratulations!");
            alert.show();
            next.setDisable(true);
        }
    }

    //display the answer image instead of the question image
    public void getAnswer() {
        //retrieve answer image from array and display it
        File answerLocation = new File(answerFolder.getPath() + "/" + answerPaths[index]);
        Image answerImg = new Image(answerLocation.toURI().toString());
        questionAnswerImage.setImage(answerImg);
        centerImg(questionAnswerImage);
        complete.setDisable(false);
        next.setDisable(false);
        viewAnswer.setDisable(true);
        viewQuestion.setDisable(false);
        grading.setDisable(false);
    }

    //display page with instructions for grading oneself IB style
    public void displayGradingInstructions() throws Exception{
        Stage instructions = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("gradingInstructions.fxml"));
        instructions.setTitle("Grading Instructions");
        instructions.setScene(new Scene(root, 960, 800));
        instructions.show();
    }

    //mark the current question as complete
    public void questionComplete() throws Exception {
        QuestionManager.getQuestionArrayByTopic(currentTopic)[index].setComplete(true);
        complete.setDisable(true);
        viewQuestion.setDisable(true);
        grading.setDisable(true);
    }

    //display the next incomplete question
    public void toNextQuestion() {
        index++;
        index = QuestionManager.getQuestion(currentTopic, index);
        displayQuestion();
    }

    //return to previous page
    public void goBack(ActionEvent event) throws Exception{
        Stage practiceMenu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("practiceMenu.fxml"));
        practiceMenu.setTitle("Baugher Academy");
        practiceMenu.setScene(new Scene(root, 1280, 720));
        practiceMenu.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
