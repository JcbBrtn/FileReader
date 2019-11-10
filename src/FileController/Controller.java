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
    ArrayList<Word> words = new ArrayList();
    boolean canRun = true;
    
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
        }//End try block
        catch(Exception e){
            System.out.println("There was an error reading the file.");
            canRun = false;
        }//End catch block
    }//end Constructor
    
    public void PrintTotalWordCount(){
        //go through words, add up all word counts of each word to get total.
        int totalWordCount = 0;
        for(int i = 0; i<words.size(); i++){
            totalWordCount += words.get(i).count;
        }
        System.out.println("Total Word Count of File: "
                + Integer.toString(totalWordCount) + "\n");
    }
    
    public void FillWords(){
        //Split Sentence Array into an array of words and fill words ArrayList.
        String[] untrimmedWordArray, trimmedWordArray;
        String trimmedWord;
        
        for(int i = 0; i<sentences.size(); i++){
            untrimmedWordArray = sentences.get(i).split(" ");
            
            for(int j = 0; j<untrimmedWordArray.length; j++){
                trimmedWord = TrimWord(untrimmedWordArray[j]);
                AddToWordList(trimmedWord);
            }
        }
    }//end FillWords()
    
    public void AddToWordList(String word){
        //Check to see if word is in WordList, if not, add it
        //If word is in WordList, increase word count by 1
        for(int i = 0; i < words.size(); i++){
            if(words.get(i).wordName.equals(word)){
                words.get(i).IncrementCount();
                return;
            }
        }//end words iteration.
        //If word is not in words, then add it to words.
        words.add(new Word(word));        
    }
    
    public String TrimWord(String word){
        //'Trims' words to remove punctuation that split/trim method wont remove.
        String trimmedWord = word.trim().toLowerCase();
        char[] punctuation = {'.',',',':',';','\n','\'','(',')'};
        char wordStart = trimmedWord.charAt(0);
        char wordEnd = trimmedWord.charAt(word.length());
        
        for(int i = 0; i<punctuation.length;i++){
            //Go through Char[] of punctuation and compare start and end values
            //of word to them. Remove those if they equal.
            if(wordStart == punctuation[i]){
                //If word starts with punctuation, get rid of punctuation.
                trimmedWord = word.substring(1, word.length());
            }//end of Starting Char comparison.
            if(wordEnd == punctuation[i]){
                //If word ends with punctuation, get rid of punctuation.
                trimmedWord = word.substring(0, word.length() - 1);
            }//end of End Char Comparison.
        }//end for loop for punctuations.
        return trimmedWord;
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
        //Does not run if an exception was thrown in the Constructor
        if(canRun){
            FillSentences();
            FillWords();

            //Print outputs to the console.
            PrintTotalWordCount();
            GetTopTenWords();
            System.out.println("\n");
            GetLastSentence();
        }
        else{
            System.out.println("Sorry, unable to run the program.");
        }
    }//end Run
    
    public static void main(String[] args) {
       Controller Main = new Controller();
       Main.Run();

    }//end main Method
    
}
