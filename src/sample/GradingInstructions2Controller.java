package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class GradingInstructions2Controller {
    @FXML
    private ImageView grading3;
    @FXML
    private ImageView grading4;

    public void initialize() {
        //display the next two pages of grading instructions
        File gradingOneLocation = new File("res/img/grading_instructions/three.png");
        File gradingTwoLocation = new File("res/img/grading_instructions/four.png");
        Image img3 = new Image(gradingOneLocation.toURI().toString());
        Image img4 = new Image(gradingTwoLocation.toURI().toString());
        grading3.setImage(img3);
        grading4.setImage(img4);
    }

    //display the first two pages of grading instructions
    public void previousPage(ActionEvent event) throws Exception{
        Stage instructions = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("gradingInstructions.fxml"));
        instructions.setTitle("Grading Instructions");
        instructions.setScene(new Scene(root, 960, 800));
        instructions.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
