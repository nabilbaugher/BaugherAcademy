package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;

public class GradingInstructionsController {
    //initialize FXML elements
    @FXML
    private ImageView grading1;
    @FXML
    private ImageView grading2;

    public void initialize() {
        //display the first two pages of grading instructions
        File gradingOneLocation = new File("res/img/grading_instructions/one.png");
        File gradingTwoLocation = new File("res/img/grading_instructions/two.png");
        Image img1 = new Image(gradingOneLocation.toURI().toString());
        Image img2 = new Image(gradingTwoLocation.toURI().toString());
        grading1.setImage(img1);
        grading2.setImage(img2);
    }

    //display the next two pages of grading instructions
    public void nextPage(ActionEvent event) throws Exception{
        Stage instructions2 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("gradingInstructions2.fxml"));
        instructions2.setTitle("Grading Instructions (cont.)");
        instructions2.setScene(new Scene(root, 960, 800));
        instructions2.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
