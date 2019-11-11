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
    }//End of constructor
    
    public void IncrementCount(){
        //Add 1 to count.
        count++;
    }//end of incrememntCount()
    
    public void SetPlacement(int place){
        //sets rankings of word useage.
        placement = place;
    }//end of setPlacement
    
    @Override
    public String toString(){
        String out = "";
        out += Integer.toString(placement);
        out += ": " + wordName + " (";
        out += Integer.toString(count) + ")";
        return out;
    }//end of toString()
    
    //Getters
    public String GetWordName(){
        return wordName;
    }//end of GetWordName()
    
    public int GetCount(){
        return count;
    }//end of GetCount()
    public int GetPlacement(){
        return placement;
    }//end of GetPlacement
}//End of Words class
