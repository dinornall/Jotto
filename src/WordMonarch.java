import java.util.Scanner;

public class WordMonarch {
    /** The Jotto valid hidden word for a game of Jotto */
    private String hiddenWord; //@todo make this final again
    /** The state of the game monarch for a game of Jotto */
    private static String monarchGameState;

    /**
     * CONSTRUCTOR: that sets hiddenWord to bread
     */
    public WordMonarch() {
        hiddenWord = "BREAD";
    }

    /**
     * CONSTRUCTOR: Sets hiddenWord to the parameter
     * @param hiddenWord
     */
    public WordMonarch(String hiddenWord) {
        this.hiddenWord = hiddenWord;
        // For testing purposes, can print out the hidden word
//        System.out.println(hiddenWord);
    }

    /**
     * Chooses whether a person or the computer chooses a hidden word
     * @param dictionary The dictionary to be used for hidden word selection
     */
    static public String WordMonarchChooser(Dictionary dictionary){
        //setting Word Monarch
        Scanner input = new Scanner(System.in);
        System.out.println("If you'd like to be the Word Monarch, type \"1\". If you'd like the computer to be the Word Monarch, type \"2\"");
        monarchGameState = input.nextLine().toUpperCase();
        while (!monarchGameState.equals("1") && !monarchGameState.equals("2")){
            System.out.print("That was neither, try again:");
            monarchGameState = input.nextLine().toUpperCase();
        }
        if (monarchGameState.equals("1")){
            //you set the hidden word
            System.out.print("What's your hidden word?:");
            String humanHiddenWord = input.nextLine().toUpperCase();
            while (!dictionary.isValidWord(humanHiddenWord)){
                System.out.println("Not Valid, try again:");
                humanHiddenWord = input.nextLine().toUpperCase();
            }
            return humanHiddenWord;
        }
        else if (monarchGameState.equals("2")){
            String computerHiddenWord = dictionary.getRandomWord();
            return computerHiddenWord;
        }
            //call constructor
        return "bread";
    }

    /**
     * CONSTRUCTOR: that sets hiddenWord to a random word from dictionary
     * @param dictionary The dictionary to be used for hidden word selection
     */
    public WordMonarch(Dictionary dictionary) {
        hiddenWord = WordMonarchChooser(dictionary);
//        System.out.println(hiddenWord);
    }

    /**
     * ACCESSOR: returns a hint given a guess
     * PRECONDITION: guess is a valid Jotto guess at this time
     * @param validGuess A valid Jotto guess by the guesser
     * @return the number of letters in common between valid guess & hiddenWord
     */
    public int giveHint(String validGuess) {
        int hintCounter = 0;
        for (int i = 0; i < validGuess.length(); i++){
            String charCheck = validGuess.substring(i,i+1);
            for (int j = 0; j < hiddenWord.length(); j++){
                String charCheck2 = hiddenWord.substring(j,j+1);
                if (charCheck.equals(charCheck2)){
                    hintCounter++;
                }
            }
        }
        return hintCounter;
    }

}