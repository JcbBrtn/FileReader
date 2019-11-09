package FileController;

import java.io.File;
import java.util.ArrayList;
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
    //Breaks file into paragraphs, that go to sentences, that go to words.
    ArrayList<String> paragraphs = new ArrayList();
    ArrayList<String> sentences = new ArrayList();
    ArrayList<String> words = new ArrayList();
    
    public Controller(){
        //Read in file and split it into an arrayList of paragraphs.
        File file = new File("passage");
        try{
            Scanner sc = new Scanner(file);
            
            while(sc.hasNextLine()){
                //scan through each line of test
                String line = sc.nextLine();
                if (line.equals("\n")){
                    //Skip over blank lines.
                    continue;
                }//end blank line checker
                else{
                    //if lines are not blank, add them to the paragraphs ArrayList.
                    paragraphs.add(line);
                }//end else
            } //End While loop
        }
        catch(Exception e){
            System.out.println("There was an error reading the file.");
        }
    }//end Constructor
    
    public void FillWords(){
        //Split Sentence Array into an array of words and fill words ArrayList.
        String[] untrimmedWordArray, trimmedWordArray;
        String trimmedWord;
        
        for(int i = 0; i<sentences.size(); i++){
            untrimmedWordArray = sentences.get(i).split(" ");
            
            for(int j = 0; j<untrimmedWordArray.length; j++){
                trimmedWord = TrimWord(untrimmedWordArray[j]);
            }
        }
    }//end FillWords()
    
    public String TrimWord(String word){
        //'Trims' words to remove punctuation that split/trim method wont remove.
        word = word.trim().toLowerCase();
        char[] punctuation = {'.',',',':',';','\n','\'','(',')'};
        char wordStart = word.charAt(0);
        char wordEnd = word.charAt(word.length());
        
        for(int i = 0; i<punctuation.length;i++){
            //Go through Char[] of punctuation and compare start and end values
            //of word to them. Remove those if they equal.
        }
        return word;
    } //end TrimWord()
    
    public void FillSentences(){
        //Fills the Sentences ArrayList to be further broken down into words
        for(int i = 0; i<paragraphs.size(); i++){
            //goes through each paragraph
            String[] tempSentences = paragraphs.get(i).split(".");
            
            for(int j = 0; j<tempSentences.length; j++){
                //and breaks it into sepereate sentences.
                //This arrayList will be used to print final requirment.
                sentences.add(tempSentences[j]);
            } //end tempSentences iterations
        } //end paragraphs iterations
    } // end FillSentences()
    
    public void Run(){
        //Runs all the Controller Commands. Call this in main() to get output
        FillSentences();
        FillWords();
    }//end Run
    
    public static void main(String[] args) {
       Controller Main = new Controller();
       
       
       //Test Stuffz.
       String test = "Moovie.";
       System.out.println(test.substring(0, test.length()-1));
        System.out.println(test.substring(test.length() - 1, test.length()));
       //Delete This.
    }//end main Method
    
}
