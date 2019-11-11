# FileReader
Reads in a given file, and outputs word count, top words used, and the last sentence to use the most used word.

The FileReader splits the file into paragraphs, then sentences, and then words. and holds each as a seperate arrayList.
Paragraphs and sentences are held as string, and words are their own class.
the words are "Trimmed" down, removing punctuation like commas, quotes, etc. This allows for a more accuare word count comparison.

The Word class has 3 values:
wordName is the word itself, and is initilized with the declaration of a new Word.
count is the number of times that a word was used in the file.
placement is the rank of the most used word. where 1 is the most used word and so on.

Using these, I output the total word count (sum count over all Words in words),

the top 10 words in descending order are used by comparing the count values.
First, looking for the most used word then the second most used word and so on.
Ties are possible in this, so there could be 2 words tied for second, so there would be no third place as one of the 2 values would be third.

And lastly, the last instance of a word in a sentence is displayed by starting at the bottom of the sentence and using
String.contains(" " + theMostUsedWord + " "). Spaces are nessisary as if "the" is the most used word, then "they" could be seen as "the"
if the spaces were not there.
