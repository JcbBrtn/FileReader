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
    private ArrayList<String> paragraphs = new ArrayList();
    private ArrayList<String> sentences = new ArrayList();
    private ArrayList<Word> words = new ArrayList();
    private boolean canRun = true;
    
    public Controller(){
        //Read in file and split it into an arrayList of paragraphs.
        File file = new File("passage.txt");
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
    public void GetLastSentence(){
        return;
    }
    public void GetTopTenWords(){
        return;
    }
    
    public void PrintTotalWordCount(){
        //go through words, add up all word counts of each word to get total.
        int totalWordCount = 0;
        for(int i = 0; i<words.size(); i++){
            totalWordCount += words.get(i).GetCount();
        }
        System.out.println("Total Word Count of File: "
                + Integer.toString(totalWordCount) + "\n");
    }
    
    public void SetWordPlacement(){
        //sets the word class placememnt variable by useage of the words.
        //1 is most used, words.size() is least used word.
        ArrayList<Word> base = new ArrayList<>();
        for(int place = 0; place<words.size();place++){
            //Start at top of words list, and make our way down finding max wordCount
            //Then keep going finding 2nd highest, until we complete the list
            base.add(words.get(place));
            for(int j = 0; j<words.size(); j++){
                //Iterate over the rest of the words list looking for words
                //with higher or equal word count, skipping over the ones w/ placements
                if(base.get(0).GetWordName().equals(words.get(j).GetWordName())
                        || words.get(j).GetPlacement() != 0){
                    //Skip over the word your comparing and words already placed.
                    continue;
                }
            }
        }
    }
    
    public void FillWords(){
        //Split Sentence Array into an array of words and fill words ArrayList.
        String[] untrimmedWordArray, trimmedWordArray;
        String trimmedWord;
        
        for(int i = 0; i<sentences.size(); i++){
            untrimmedWordArray = sentences.get(i).split(" ");
            
            for(int j = 0; j<untrimmedWordArray.length; j++){
                if(untrimmedWordArray[j].equals("\n")){
                    //Write out special cases
                    continue;
                }
                else if(untrimmedWordArray[j].length() == 1)
                trimmedWord = TrimWord(untrimmedWordArray[j]);
                AddToWordList(trimmedWord);
            }
        }
    }//end FillWords()
    
    public void AddToWordList(String word){
        //Check to see if word is in WordList, if not, add it
        //If word is in WordList, increase word count by 1
        for(int i = 0; i < words.size(); i++){
            if(words.get(i).GetWordName().equals(word)){
                words.get(i).IncrementCount();
                return;
            }
        }//end words iteration.
        //If word is not in words, then add it to words.
        words.add(new Word(word));        
    }//End AddToWordList()
    
    public String TrimWord(String word){
        //'Trims' words to remove punctuation that split/trim method wont remove.
        String trimmedWord = word.trim().toLowerCase();
        char[] punctuation = {'.',',',':',';','\n','\'','(',')'};
        //String punctuation = ".,:;\n";
        char wordStart = trimmedWord.charAt(0);
        //String wordStart = trimmedWord.substring(0,1);
        char wordEnd = trimmedWord.charAt(word.length());
        //String wordEnd = trimmedWord.substring(trimmedWord.length()-1, trimmedWord.length());
        for(int i = 0; i<punctuation.length;i++){
            //Go through Char[] of punctuation and compare start and end values
            //of word to them. Remove those if they equal.
            if(wordStart == punctuation[i]){
                //If word starts with punctuation, get rid of punctuation.
                trimmedWord = trimmedWord.substring(1, trimmedWord.length());
            }//end of Starting Char comparison.
            if(wordEnd == punctuation[i]){
                //If word ends with punctuation, get rid of punctuation.
                trimmedWord = trimmedWord.substring(0, trimmedWord.length() - 1);
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
            SetWordPlacement();

            //Print outputs to the console.
            PrintTotalWordCount();
            GetTopTenWords();
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
