package sample;

import java.io.File;

public class QuestionManager {
    //initialize instance variables
    private static Question[][] questions;
    private static final String[] TOPICS = {"algebra", "functions", "trig", "vectors", "stats", "calculus"};

    //quicksort algorithm to sort fileNames
    //quicksort is a simple yet time and space efficient sorting algorithm, especially for larger arrays
    //the array of file names could be as big as the user wants as they add their own practice questions to the folder
    public static void sort(String[] fileNames, int start, int end) {
        //call partition to split the data and assign partition the value of the index of the pivot
        int partition = partition(fileNames, start, end);
        //if the pivot index has not yet passed start then quicksort that segment of the data
        if (partition - 1 > start) {
            sort(fileNames, start, partition - 1);
        }
        //if the pivot index has not yet passed end then quicksort that segment of the data
        if (partition + 1 < end) {
            sort(fileNames, partition + 1, end);
        }
    }

    //split the data with respect to the pivot value
    public static int partition(String[] fileNames, int start, int end) {
        //arbitrarily assigned pivot value
        String pivot = fileNames[end];
        //place all values less than the pivot before the start index and all values less than the pivot after the start index
        for (int i = start; i < end; i++) {
            //if the current String is less than the pivot, switch it with start
            if (fileNames[i].compareTo(pivot) > 0) {
                String temp = fileNames[i];
                fileNames[i] = fileNames[start];
                fileNames[start] = temp;
                start++;
            }
        }
        //switch the String at index start and the pivot
        String temp = fileNames[start];
        fileNames[start] = pivot;
        fileNames[end] = temp;
        //return the index of the pivot String
        return start;
    }

    //populate the 2d array with questions
    public static void initializeQuestionArray() {
        questions = new Question[6][];
        for (int i = 0; i < questions.length; i++) {
            File folder = new File("res/img/questions/" + TOPICS[i]);
            String[] fileNames = folder.list((dir, name) -> !name.equals(".DS_Store")); //filter out .DS_Store extraneous returns
            sort(fileNames, 0, fileNames.length - 1);
            questions[i] = new Question[fileNames.length];
            for (int j = 0; j < fileNames.length; j++) {
                questions[i][j] = new Question(TOPICS[i], folder.getPath() + "/" + fileNames[j]);
            }
        }
    }

    //accessor method, returns String[] of questions by topic
    public static Question[] getQuestionArrayByTopic(String topic) {
        for (int i = 0; i < TOPICS.length; i++) {
            if (TOPICS[i].equals(topic)) {
                return questions[i];
            }
        }
        return null;
    }

    //accessor method
    public static Question[][] getQuestionArray() {
        return questions;
    }

    //searches for and returns the next incomplete question
    public static int getQuestion(String topic, int index) {
        int i = index;
        Question[] questionList = getQuestionArrayByTopic(topic);
        while (true) {
            if (i >= questionList.length) {
                i = 0;
            }
            if (!questionList[i].isComplete()) {
                return i;
            }
            i++;
            if (i == index) {
                break;
            }
        }
        //search failed
        return -1;
    }
}
