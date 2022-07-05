import java.util.Scanner;

/**
 * Guesser is responsible for guessing a valid Jotto word
 */
public class Guesser {
    private final Scanner input;
    private static String guesserGameState;

    /**
     * Constructor for Guesser which makes a Scanner.
     */
    public Guesser() {
        Scanner input = new Scanner(System.in);
        this.input = input;
    }

    /**
     * Chooses whether it's a human guesser or computer one
     * @return true if human guesser, false if a computer one
     */
    static public int GuesserChooser(){
        //setting Word Monarch
        Scanner input = new Scanner(System.in);
        System.out.println("If you'd like to guess, type \"1\". If you'd like a linear computer guesser to play, type \"2\". " +
                "If you'd like a smart computer guesser, type \"3\"");
        guesserGameState = input.nextLine().toUpperCase();
        while (!guesserGameState.equals("1") && !guesserGameState.equals("2") && !guesserGameState.equals("3")){
            System.out.print("That was neither, try again:");
            guesserGameState = input.nextLine().toUpperCase();
        }
        if (guesserGameState.equals("1")){
            //you guess the hidden word
            return 1;
        }
        else if (guesserGameState.equals("2")){
            // the computer guesses the hidden word
            return 2;
        }
        else if (guesserGameState.equals("3")){
            // the computer guesses the hidden word
            return 3;
        }
        return 1;
    }

    /**
     * Returns a valid guess given the dictionary and a guessed word by people
     * @param dictionary all the words valid in this Jotto game
     * @return true if it works, false if it doesn't
     */
    public String humanGuess(Dictionary dictionary){
        boolean guessFound = false;
        while(!guessFound) {
            System.out.print("Guess: ");
            String guess = input.nextLine().toUpperCase();
            if (Jotto.isValidGuess(guess, dictionary) && dictionary.isValidWord(guess)){
                guessFound = true;
                return guess;
            }
            else {
                System.out.println("That guess didn't work, try again :(");
            }
        }
        return "YOU ARE STUPID";
    }

    /**
     * Returns the most average valid guess given the dictionary, and the gameboard
     * @param dictionary all the words valid in this Jotto game
     * @param gameboard
     * @return
     */
    public String smartGuess(Dictionary dictionary, Turn[] gameboard) {
        if (gameboard.length == 0){
            return "RAVED";
        }
        else {
            String bestGuess = "";
            double stanDev = 0;
            int validWordCount = 0;
            int optimumCount = 0;
            String currOptimumWord = "";
            int currOptimumValue = 10000;
            //Count how many valid words there are for this guess
            for (int i = 0; i < dictionary.getLength(); i++) {
                if (Jotto.isValidGuess(dictionary.getWord(i), dictionary)) {
                    validWordCount++;
                }
            }
            //Go through every Word
            for (int i = 0; i < dictionary.getLength(); i++) {
                //find each valid word
                int[] scores = new int[]{0, 0, 0, 0, 0};
                String testWord = dictionary.getWord(i);
                if (Jotto.isValidGuess(testWord, dictionary)) {
                    for (int j = 0; j < dictionary.getLength(); j++) {
                        String compareWord = dictionary.getWord(j);
                        if (Jotto.isValidGuess(compareWord, dictionary)) {
                            //Count how many letters are shared with the test word
                            int count = Jotto.commonLetters(compareWord, testWord);
                            if (count < 5) {
                                //store that in an array
                                scores[count]++;
                            }
                        }
                    }
                    optimumCount = validWordCount / 5;
                    int testWordValue = 0;
                    int[] subtractArr = {0, 0, 0, 0, 0};
                    //find the difference between each share letter count and the validlength/5
                    for (int l = 0; l < 5; l++) {
                        subtractArr[l] = Math.abs(scores[l] - optimumCount);
                    }
                    //add them all up
                    for (int m = 0; m < 5; m++) {
                        testWordValue += subtractArr[m];
                    }
                    //find what word leads to the smallest
                    if (testWordValue < currOptimumValue) {
                        //set that as the best word
                        currOptimumValue = testWordValue;
                        currOptimumWord = testWord;
                    }
                }
            }
            bestGuess = currOptimumWord;
            return bestGuess;
        }
    }

    /**
     * Returns a valid guess for a Jotto game given the dictionary and previous
     * turns of guesses and hints
     * @param dictionary all the words valid in this Jotto game
     * @return A valid Jotto guess under the conditions
     */
    public String guessValidGuess(Dictionary dictionary) {
        boolean guessFound = false;
        int i = 0;
        while (!guessFound && i < dictionary.getLength()){
            String potentialGuess = dictionary.getWord(i);
            if (Jotto.isValidGuess(potentialGuess, dictionary)){
                guessFound = true;
                return potentialGuess;
            }
            i++;
        }
        return "Something didn't work; you suck";
    }


}
