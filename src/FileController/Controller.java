package FileController;

import java.io.File;
import java.io.FileNotFoundException;
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
                
                if(!(line.equals(""))){
                    //Check for blank lines.
                    paragraphs.add(line);
                }//End of Blank Line Checker
            } //End While loop
        }//End try block
        catch(Exception e){
            System.out.println("There was an error reading the file.");
            canRun = false;
        }//End catch block
    }//end Constructor
    
    public void GetLastSentence(){
        //Get the last utterance of the most used word.
        //Assume there is no tie for first.
        String mostUsedWord = GetMostUsedWord();
        System.out.println("\nThe last utterance of \"" + mostUsedWord + "\": ");
        for(int i = sentences.size() - 1; i >= 0; i--){
            if(sentences.get(i).contains(" " + mostUsedWord + " ")){
                System.out.println(sentences.get(i)+ ".");
                return;
            }//End mostUsedWord checker
        }//End for loop
    }//End GetLastSentence()
    
    public String GetMostUsedWord(){
        //get the first word in words with placement 1
        for(int i = 0; i<words.size();i++){
            if(words.get(i).GetPlacement() == 1){
                return words.get(i).GetWordName();
            }//end placement checker
        }//end for loop
        //If "Oh Hai" is most used, an error occured and words is empty
        return "Oh Hai";
    }//End GetMostUsedWord()
    
    public void GetTopTenWords(){
        //Use placement int to show sorted view of words.
        int numPlacesDisplayed = 10;
        
        System.out.println("Top 10 words used: ");
        
        for(int place = 1; place <= numPlacesDisplayed; place++){
            for(int i = 0; i<words.size(); i++){
                if(words.get(i).GetPlacement() == place){
                    System.out.println(words.get(i).toString());
                }
            }//End word finding for loop
        }//End place for loop
    }//End GetTopTenWords()
    
    public void PrintTotalWordCount(){
        //go through words, add up all word counts of each word to get total.
        int totalWordCount = 0;
        for(int i = 0; i<words.size(); i++){
            totalWordCount += words.get(i).GetCount();
        }
        System.out.println("Total Word Count of File: "
                + Integer.toString(totalWordCount) + "\n");
    }//end PrintTotalWordCount
    
    public void SetWordPlacement(){
        //sets the word class placememnt variable by usage of the words.
        //1 is most used, words.size() is least used word.
        //base is array so ties will be same placement.
        ArrayList<Word> base = new ArrayList<>();
        int baseNum = 0;
        int placeNum = 1;
        while(WordsNeedPlaced()){
            //Start at top of words list, and make our way down finding largest wordCount
            //Then keep going finding 2nd highest, until we complete the list
            base.clear();
            Word baseWord = GetNextBase();
            base.add(baseWord);
            baseNum = GetBaseNum(baseWord);
            for(int j = baseNum; j<words.size(); j++){
                //Iterate over the rest of the words list looking for words
                //with higher or equal word count, skipping over the ones w/ placements
                if(base.get(0).GetWordName().equals(words.get(j).GetWordName())
                        || words.get(j).GetPlacement() != 0){
                    //Skip over the word your comparing and words already placed.
                    continue;
                }
                else if(base.get(0).GetCount() == words.get(j).GetCount()){
                    //If a tie, they both get the same place.
                    base.add(words.get(j));
                }
                else if(base.get(0).GetCount() < words.get(j).GetCount()){
                    //If count is greater than base, replace base with bigger value. 
                    base.clear();
                    base.add(words.get(j));
                }//end of base comparison
            }//End of For Loop
            for(int baseNumber = 0; baseNumber < base.size(); baseNumber++){
                base.get(baseNumber).SetPlacement(placeNum);
            }
            //Add placeNum by size so if tie, Then only 10 words get displayed.
            placeNum += base.size();
        }//End of While Loop
    }//EndSetWordPlacement
    
    public int GetBaseNum(Word base){
        for(int i = 0; i<words.size();i++){
            if(words.get(i) == base){
                return i;
            }//end base checker
        }//end for loop
        return 0;
    }//End GetBaseNum()
    
    public Word GetNextBase(){
        for(int i = 0; i<words.size(); i++){
            if(words.get(i).GetPlacement() == 0){
                return words.get(i);
            }//end placemment checker
        }//end for loop
        //This will placehold the system until it gets set back on track.
        return new Word("Oh Hai");
    }//End GetNextBase()
    
    public boolean WordsNeedPlaced(){
        //Checks to see if words still needs to be placed
        boolean needPlaced = false;
        for(int i = 0; i< words.size();i++){
            if(words.get(i).GetPlacement() == 0){
                needPlaced = true;
            }//end base checker
        }//end for loop
        return needPlaced;
    }//End WordsNeedPlaced
    
    public void FillWords(){
        //Split Sentence Array into an array of words and fill words ArrayList.
        String[] untrimmedWordArray, trimmedWordArray;
        String trimmedWord;
        
        for(int i = 0; i<sentences.size(); i++){
            untrimmedWordArray = sentences.get(i).split(" ");
            
            for(int j = 0; j<untrimmedWordArray.length; j++){
                if(untrimmedWordArray[j].equals("\n") || untrimmedWordArray[j].length() == 0){
                    //Write out special cases
                    continue;
                }
                else if(untrimmedWordArray[j].length() > 1){
                    //Error would be thrown if word was size of 1, so write it out.
                trimmedWord = TrimWord(untrimmedWordArray[j]);
                AddToWordList(trimmedWord);
                }//end of Special Case Checks
                else{
                    AddToWordList(untrimmedWordArray[j]);
                }
            }//End of sentence for loop
        }//End of sentence division for loop
    }//end FillWords()
    
    public void AddToWordList(String word){
        //Check to see if word is in WordList, if not, add it
        //If word is in WordList, increase word count by 1
        for(int i = 0; i < words.size(); i++){
            if(words.get(i).GetWordName().equals(word)){
                words.get(i).IncrementCount();
                return;
            }//end words checker
        }//end words iteration.
        //If word is not in words, then add it to words.
        words.add(new Word(word));        
    }//End AddToWordList()
    
    public String TrimWord(String word){
        //'Trims' words to remove punctuation that split/trim method wont remove.
        String trimmedWord = word.trim().toLowerCase();
        char[] punctuation = {'.',',',':',';','\n','\'','(',')','"'};
        char wordStart = trimmedWord.charAt(0);
        char wordEnd = trimmedWord.charAt(word.length()-1);
        
        for(int i = 0; i<punctuation.length;i++){
            //Go through Char[] of punctuation and compare start and end values
            //of word to them. Remove those if they equal.
            if(wordStart == punctuation[i]){
                //If word starts with punctuation, get rid of punctuation.
                return trimmedWord.substring(1, trimmedWord.length());
            }//end of Starting Char comparison.
            if(wordEnd == punctuation[i]){
                //If word ends with punctuation, get rid of punctuation.
                return trimmedWord.substring(0, trimmedWord.length()-1);
            }//end of End Char Comparison.
        }//end for loop for punctuations.
        return trimmedWord;
    } //end TrimWord()
    
    public void FillSentences(){
        //Fills the Sentences ArrayList to be further broken down into words
        for(int i = 0; i<paragraphs.size(); i++){
            //goes through each paragraph
            String[] tempSentences = paragraphs.get(i).split("\\.");

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