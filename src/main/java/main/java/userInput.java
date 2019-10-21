package main.java;

public class userInput {
//    only good values will be here since
//    checking is done before the instance
//    of this object
    private final String height;
    private final int weight;
    int singleQuoteIndex;
    int doubleQuoteIndex;

    public userInput(String height, int weight){
        this.height = height;
        this.weight = weight;
    }

    public String getHeight(){
        return height;
    }

    public int getWeight(){
        return weight;
    }

}
