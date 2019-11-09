package FileController;

import java.io.File;
import java.util.Scanner;

/**
 * 
 * File Controller
 * By
 * Jacob Barton
 * For
 * Ownum Coding Challenge
 * 
 * This Controller takes in the passage.txt file in the File package
 * And outputs the following:
 * 
 * The total word count
 * The top 10 words in a sorted order
 * The last sentence to contain the most used word.
 * 
 */



public class Controller {
     //This Class reads in a file and runs manipulations on it.
    String[] sentences;
    
    public Controller(){
        //Read in file and split it into an array of sentences.
        File file = new File("passage.txt");
        try{
            Scanner sc = new Scanner(file);
        }
        catch(Exception e){
            System.out.println("There was an error reading the file.");
        }
    }
    
    public void Run(){ //Runs all the Controller Commands. Call this in main() to get output
        
    }
    
    


    public static void main(String[] args) {
       Controller Main = new Controller();
    }
    
}
