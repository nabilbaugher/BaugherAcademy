package sample;

public class Question {
    //declare instance variables
    private String topic, path;
    private boolean complete;

    //default constructor
    public Question(){
        topic = "";
        path = "";
        complete = false;
    }

    //two argument constructor
    public Question(String topic, String path){
        this.topic = topic;
        this.path = path;
        complete = false;
    }

    //mark the question as complete
    public void setComplete(boolean complete) { this.complete = complete; }

    //set the question's topic
    public void setTopic(String topic){
        this.topic = topic;
    }

    //set the question's path
    public void setPath(String path){
        this.path = path;
    }

    //accessor method for variable complete
    public boolean isComplete(){
        return complete;
    }

    //accessor method for variable topic
    public String getTopic(){
        return topic;
    }

    //accessor method for variable path
    public String getPath(){
        return path;
    }

}
