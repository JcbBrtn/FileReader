/*
 * This is a class for the words found in the file.
 * This only to help organize the name of the Word,
 * and the total Word count
 */
package FileController;

public class Word {
    String wordName;
    int count;
    
    public Word(String Name){
        //Word only needs name. Initially, the word has only come up once.
        wordName = Name;
        count = 1;
    }
    
    public void IncrementCount(){
        //Add 1 to count.
        count++;
    }
    
    //Getters
    public String GetWordName(){
        return wordName;
    }
    
    public int GetCount(){
        return count;
    }
    
}
