package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.util.*;

public class StatsController {
    //initialize FXML elements
    @FXML
    private Label affirmation;
    @FXML
    private PieChart algebraStats;
    @FXML
    private PieChart functionsStats;
    @FXML
    private PieChart trigStats;
    @FXML
    private PieChart vectorsStats;
    @FXML
    private PieChart statsStats;
    @FXML
    private PieChart calculusStats;
    @FXML
    private PieChart overallStats;
    @FXML
    private BarChart relativeStrengthByTopic;
    @FXML
    private ImageView correctIncorrect;

    //initialize instance variables
    private final String[] TOPICS = {"algebra", "functions", "trig", "vectors", "stats", "calculus"};
    private ObservableList<PieChart.Data> completeQuestionsByTopic;
    private ObservableList<PieChart.Data> completeVsIncompleteTotal;
    private boolean showingChart1;

    public void initialize() {
        //calculate and store the number of complete questions in each topic area
        int completeAlgebra = calculateNumComplete("algebra");
        int completeFunctions = calculateNumComplete("functions");
        int completeTrig = calculateNumComplete("trig");
        int completeVectors = calculateNumComplete("vectors");
        int completeStats = calculateNumComplete("stats");
        int completeCalculus = calculateNumComplete("calculus");

        //calculate and store the number of incomplete questions in each topic area
        int incompleteAlgebra = calculateNumIncomplete("algebra");
        int incompleteFunctions = calculateNumIncomplete("functions");
        int incompleteTrig = calculateNumIncomplete("trig");
        int incompleteVectors = calculateNumIncomplete("vectors");
        int incompleteStats = calculateNumIncomplete("stats");
        int incompleteCalculus = calculateNumIncomplete("calculus");

        //calculate percentage complete in each topic area
        double algebraPercentage = completeAlgebra / (double) (completeAlgebra + incompleteAlgebra);
        double functionsPercentage = completeFunctions / (double) (completeFunctions + incompleteFunctions);
        double trigPercentage = completeTrig / (double) (completeTrig + incompleteTrig);
        double vectorsPercentage = completeVectors / (double) (completeVectors + incompleteVectors);
        double statsPercentage = completeStats / (double) (completeStats + incompleteStats);
        double calculusPercentage = completeCalculus / (double) (completeCalculus + incompleteCalculus);

        //calculate the totals of the variables
        int totalComplete = completeAlgebra + completeFunctions + completeTrig + completeVectors + completeStats + completeCalculus;
        int totalIncomplete = incompleteAlgebra + incompleteFunctions + incompleteTrig + incompleteVectors + incompleteStats + incompleteCalculus;
        double totalPercentage = (algebraPercentage + functionsPercentage + trigPercentage + vectorsPercentage + statsPercentage + calculusPercentage) / 6;

        //create each chart with complete vs incomplete questions
        ObservableList<PieChart.Data> algebraData = FXCollections.observableArrayList(
                new PieChart.Data("Complete", completeAlgebra),
                new PieChart.Data("Incomplete", incompleteAlgebra)
        );
        algebraStats.setData(algebraData);
        ObservableList<PieChart.Data> functionsData = FXCollections.observableArrayList(
                new PieChart.Data("Complete", completeFunctions),
                new PieChart.Data("Incomplete", incompleteFunctions)
        );
        functionsStats.setData(functionsData);
        ObservableList<PieChart.Data> trigData = FXCollections.observableArrayList(
                new PieChart.Data("Complete", completeTrig),
                new PieChart.Data("Incomplete", incompleteTrig)
        );
        trigStats.setData(trigData);
        ObservableList<PieChart.Data> vectorsData = FXCollections.observableArrayList(
                new PieChart.Data("Complete", completeVectors),
                new PieChart.Data("Incomplete", incompleteVectors)
        );
        vectorsStats.setData(vectorsData);
        ObservableList<PieChart.Data> statsData = FXCollections.observableArrayList(
                new PieChart.Data("Complete", completeStats),
                new PieChart.Data("Incomplete", incompleteStats)
        );
        statsStats.setData(statsData);
        ObservableList<PieChart.Data> calculusData = FXCollections.observableArrayList(
                new PieChart.Data("Complete", completeCalculus),
                new PieChart.Data("Incomplete", incompleteCalculus)
        );
        calculusStats.setData(calculusData);

        //initialize the data for each different overallStats chart
        completeVsIncompleteTotal = FXCollections.observableArrayList(
                new PieChart.Data("Complete", totalComplete),
                new PieChart.Data("Incomplete", totalIncomplete)
        );
        completeQuestionsByTopic = FXCollections.observableArrayList(
                new PieChart.Data("Algebra", completeAlgebra),
                new PieChart.Data("Functions", completeFunctions),
                new PieChart.Data("Trigonometry", completeTrig),
                new PieChart.Data("Vectors", completeVectors),
                new PieChart.Data("Statistics", completeStats),
                new PieChart.Data("Calculus", completeCalculus)
        );
        //set the default overallStats chart to completeVsIncompleteTotal
        overallStats.setData(completeVsIncompleteTotal);
        overallStats.setTitle("Total Complete/Incomplete");
        showingChart1 = true;

        //set the data for the bar chart
        relativeStrengthByTopic.getData().add(getPercentageData());

        //add a communal legend at the bottom of the pie charts
        File imageLocation = new File("res/img/complete_incomplete.png");
        Image img = new Image(imageLocation.toURI().toString());
        correctIncorrect.setImage(img);

        //alter the message depending on user progress
        if (totalPercentage < .4) {
            affirmation.setText("Practice makes perfect!");
        } else if (algebraPercentage > .7 && functionsPercentage > .7 && trigPercentage > .7 && vectorsPercentage > .7 && statsPercentage > .7 && calculusPercentage > .7) {
            affirmation.setText("You're in good shape!");
        }
    }

    //calculate the number of complete questions of the given topic
    public int calculateNumComplete(String topic) {
        Question[] arrayByTopic = QuestionManager.getQuestionArrayByTopic(topic);
        int numComplete = 0;
        for (int i = 0; i < arrayByTopic.length; i++) {
            if (arrayByTopic[i].isComplete()){
                numComplete++;
            }
        }
        return numComplete;
    }

    //calculate the number of incomplete questions of the given topic
    public int calculateNumIncomplete(String topic) {
        Question[] arrayByTopic = QuestionManager.getQuestionArrayByTopic(topic);
        int numComplete = calculateNumComplete(topic);
        int numIncomplete = arrayByTopic.length - numComplete;
        return numIncomplete;
    }

    //sort topics by percentage complete
    public TreeMap<Double, String> sortTopics() {
        //create tree map to automatically sort the topics by their percentage complete
        TreeMap<Double, String> completeByTopic = new TreeMap<>();
        for (int i = 0; i < TOPICS.length; i++){
            double numComplete = calculateNumComplete(TOPICS[i]);
            double percentageComplete = numComplete/(numComplete + calculateNumIncomplete(TOPICS[i]));
            if (completeByTopic.get(percentageComplete) == null) {
                completeByTopic.put(percentageComplete, TOPICS[i]);
            } else {
                completeByTopic.put(percentageComplete + Math.random() / 100000000, TOPICS[i]);
            }
        }
        //return sorted array of topics
        return completeByTopic;
    }

    //get the data for the percentage complete by topic chart
    public XYChart.Series getPercentageData() {
        TreeMap<Double, String> completeByTopic = sortTopics();
        XYChart.Series data = new XYChart.Series();
        String[] topicOrder = completeByTopic.values().toArray(new String[completeByTopic.size()]);
        Collections.reverse(Arrays.asList(topicOrder));
        Double[] keys = completeByTopic.descendingKeySet().toArray(new Double[completeByTopic.size()]);
        for (int i = 0; i < topicOrder.length; i++) {
            data.getData().add(new XYChart.Data(topicOrder[i], keys[i]));
        }
        return data;
    }

    //switch the data shown in the overallStats pie chart
    public void showOtherChart() {
        if (showingChart1) {
            overallStats.setData(completeQuestionsByTopic);
            overallStats.setTitle("# Complete By Topic");
        } else {
            overallStats.setData(completeVsIncompleteTotal);
            overallStats.setTitle("Total Complete/Incomplete");
        }
        showingChart1 = !showingChart1;
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
