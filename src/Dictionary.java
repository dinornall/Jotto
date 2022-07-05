import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    /** all the words in the dictionary for a Jotto guess*/
    private String[] words;

    /**
     * Gets the word a given index
     * @param index the value at which the word being gotten is
     * @return the word at a given index
     */
    public String getWord (int index){
        return words[index];
    }

    /**
     * Gets the length of a given word
     * @return the length
     */
    public int getLength (){
        return words.length;
    }

    public Dictionary (String fileName) {
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            int size = 0;
            while (input.hasNext()) {
                String testWord = input.nextLine();
                size++;
            }
            input = new Scanner(file);
            words = new String[size];
            int i = 0;
            while (input.hasNext()) {
                String word = input.nextLine();
                words[i] = word;
                i++;
            }
        }
        catch (Exception e){
            System.out.println("Something else broke");
            System.out.println(e);
        }

    }

    private void writeWordFile(String[] words, String fileName){
            File file = new File(fileName);
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                for (String w : words){
                    fw.write(w + "\n");
                }
                fw.flush();
                fw.close();
            }
            catch (Exception ignored){
                System.out.println("Something bad Happened");
            }
        }

    /**
     * returns true if repeating letters
     * @param word word to check
     * @return true if repeating letters
     */
    private boolean hasRepeatingLetters(String word){
        for (int i = 0; i < word.length() - 1; i++) {
            String charCheck = word.substring(i,i+1);
            for (int j = i + 1; j < word.length(); j++) {
                String charCheck2 = word.substring(j,j+1);
                if (charCheck.equals(charCheck2)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks if the guessed word is valid
     * @param word the word that was just guessed
     * @return true if the word is in the dictionary
     */
    public boolean isValidWord (String word) {
        for (String wordCheck : words){
            if (wordCheck.equals(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * picks a random word from the dictionary
     * @return a random Jotto-valid word from our dictionary
     */
    public String getRandomWord() {
        int rand = (int)(Math.random() * words.length);
        return words[rand];
    }
}
