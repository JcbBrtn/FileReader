/*
 * This is a class for the words found in the file.
 * This only to help organize the name of the Word,
 * the total Word count, and rank of each word compared
 * to all the other words used.
 */
package FileController;

public class Word {
    private String wordName;
    private int count;
    private int placement;
    
    public Word(String Name){
        //Word only needs name. Initially, the word has only come up once.
        wordName = Name;
        count = 1;
        placement = 0;
    }
    
    public void IncrementCount(){
        //Add 1 to count.
        count++;
    }
    
    public void setPlacement(int place){
        //sets rankings of word useage.
        placement = place;
    }
    
    public String toString(){
        String out = "";
        out += Integer.toString(placement);
        out += ": " + wordName + " (";
        out += Integer.toString(count) + ")";
        return out;
    }
    
    //Getters
    public String GetWordName(){
        return wordName;
    }
    
    public int GetCount(){
        return count;
    }
    public int GetPlacement(){
        return placement;
    }
    
}
