import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * Jotto is a game of Jotto
 * 2 players, a guesser and a Word Monarch
 * Word Monarch chooses a word with 5 letters and no repeating letters
 * the guesser tries to guess this word be selecting jotto valid guesses
 * each guess has to have five letters that are non repeating and has to conform to all previous guesses
 */
public class Jotto {
//    private final Scanner input;
    /** Word Monarch chooses the hidden word and gives hints */
    private WordMonarch monarch; //@todo make this final again
    /** Player guesses valid Jotto words */
    private final Guesser player;
    /** Keeps track of all turns played in a game */
    static private Turn[] gameboard;
    /** Keeps track of all valid words in Jotto */
    private final Dictionary dictionary;
    private final Scanner input;

    /**
     * DEFAULT CONSTRUCTOR: Uses BREAD for the hidden word
     * and the default dictionary
     */
    public Jotto() {
        gameboard = new Turn[0];
        dictionary = new Dictionary("JottoWords.txt");
        Scanner input = new Scanner(System.in);
        this.input = input;
        monarch = new WordMonarch(dictionary);
        player = new Guesser();
    }

    public Jotto(boolean isTest){
        gameboard = new Turn[0];
        dictionary = new Dictionary("JottoWords.txt");
        Scanner input = new Scanner(System.in);
        this.input = input;
        player = new Guesser();
    }

    /**
     * Plays a full game of Jotto
     */
    public void playGame() {
        int humanGuesser = player.GuesserChooser();
        if (humanGuesser == 1) {
            while (!isGameOver(gameboard)) {
                playHumanTurn();
            }
        }
        else if (humanGuesser == 2){
            while (!isGameOver(gameboard)) {
                playLinearTurn();
            }
        }
        else if (humanGuesser == 3){
            while (!isGameOver(gameboard)) {
                playSmartTurn();
            }
        }
        Jotto.printBoard(gameboard);
        System.out.println("");
        System.out.println("CONGRATS!");
    }

    public void runTest(){
        double LinGuessLen = 0;
        double SmrGuessLen = 0;
        ArrayList<Double> linStats = new ArrayList<Double>();
        ArrayList<Double> smrStats = new ArrayList<Double>();

        //go through every word in dictionary
        for (int i = 0; i < dictionary.getLength(); i++) {
            //set it as the hidden word
            //
            monarch = new WordMonarch(dictionary.getWord(i));
            //perform a linear search on all of the words
            while (!isGameOver(gameboard)) {
                playLinearTurn();
            }
            //add the amount of guesses to LinGuessLen
            LinGuessLen += gameboard.length;
            linStats.add((double)gameboard.length);
            clearBoard(gameboard);
            //perform a Smart search on all of the words
            while (!isGameOver(gameboard)) {
                playSmartTurn();
            }
            //add it to SmrGuessLen
            SmrGuessLen += gameboard.length;
            smrStats.add((double)gameboard.length);
            clearBoard(gameboard);
            //Tell me how many left to go there are
            System.out.println((dictionary.getLength()-i) + " left to go!");
        }

        //Order array list
        Collections.sort(linStats);
        Collections.sort(smrStats);

        //Total Games Played by each
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("Total Linear Games Played: " + linStats.size());
        System.out.println("Total Smart Games Played: " + smrStats.size());
        System.out.println();

        //Total guesses done by each
        System.out.println("Total Linear Searches: " + LinGuessLen);
        System.out.println("Total Smart Searches: " + SmrGuessLen);
        System.out.println();

        //Mean for each
        double linMean = Stats.findMean(linStats);
        System.out.println("Linear Mean: " + linMean);
        double smrMean = Stats.findMean(smrStats);
        System.out.println("Smart Mean:" + smrMean);
        System.out.println();


        //St Dev. for each
        System.out.println("Linear Standard Deviation: " + Stats.findStdDev(linStats, linMean));
        System.out.println("Smart Standard Deviation: " + Stats.findStdDev(smrStats, smrMean));
        System.out.println();

        //Mode for each
        System.out.println("Linear Mode: " + Stats.findMode(linStats));
        System.out.println("Smart Mode: " + Stats.findMode(smrStats));
        System.out.println();

        //Min Value for each
        double linMin = linStats.get(0);
        System.out.println("Linear Min: " + linMin);
        double smrMin = smrStats.get(0);
        System.out.println("Smart Min: " + smrMin);
        System.out.println();

        //Q1 for each
        double linQ1 = linStats.get(linStats.size()/4);
        System.out.println("Linear Q1: " + linQ1);
        double smrQ1 = smrStats.get(smrStats.size()/4);
        System.out.println("Smart Q1: " + smrQ1);
        System.out.println();

        //Median for each
        double linMedian = linStats.get((linStats.size()/2));
        System.out.println("Linear Median: " + linMedian);
        double smrMedian = smrStats.get(smrStats.size()/2);
        System.out.println("Smart Median: " + smrMedian);
        System.out.println();

        //Q3 for each
        double linQ3 = linStats.get(3*(linStats.size()/4));
        System.out.println("Linear Q3: " + linQ3);
        double smrQ3 = smrStats.get(3*(smrStats.size()/4));
        System.out.println("Smart Q3: " + smrQ3);
        System.out.println();

        //Max Value for each
        double linMax = linStats.get(linStats.size() - 1);
        System.out.println("Linear Max: " + linMax);
        double smrMax = smrStats.get(smrStats.size() - 1);
        System.out.println("Smart Max: " + smrMax);
        System.out.println();

        //@todo find range for each
        double linRange = linMax - linMin;
        System.out.println("Linear Range: " + linRange);
        double smrRange = smrMax - smrMin;
        System.out.println("Smart Range: " + smrRange);
        System.out.println();

    }

    /**
     * Determines if a guess is valid under game conditions
     * @param guess The guess in question
     * @param dictionary The dictionary of the game
     * @return True if it is valid, False otherwise
     */
    static public boolean isValidGuess (String guess, Dictionary dictionary){
        if (dictionary.isValidWord(guess)) {
            for (int i = 0; i < gameboard.length; i++) {
                if (gameboard[i].getHint() != Jotto.commonLetters(guess, gameboard[i].getGuess())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prints out the Jotto game board
     * A list of guesses with hints
     * @param board The current state of the Jotto game
     */
    static private void printBoard (Turn[]board){
        for (int i = 0; i < board.length; i++) {
            System.out.println(board[i].toString());
        }
    }

    static private void clearBoard(Turn[]board){
        gameboard = new Turn[0];
    }

    /**
     * Plays a single turn in a game of Jotto
     */
    private void playHumanTurn () {
        printBoard(gameboard);
        //get a valid guess
        String guess = player.humanGuess(dictionary);
        //get a hint
        int hint = monarch.giveHint(guess);
        //add it to the board
        Turn newTurn = new Turn(guess, hint);
        gameboard = addTurn(gameboard, newTurn);
    }

    /**
     * Plays a single turn in a game of Jotto
     */
    private void playLinearTurn() {
        //get a valid guess
        String guess = player.guessValidGuess(dictionary);
        //get a hint
        int hint = monarch.giveHint(guess);
        //add it to the board
        Turn newTurn = new Turn(guess, hint);
        gameboard = addTurn(gameboard, newTurn);
    }

    /**
     * Plays a single turn in a game of Jotto
     */
    private void playSmartTurn () {
    //get a valid guess
    String guess = player.smartGuess(dictionary, gameboard);
    //get a hint
    int hint = monarch.giveHint(guess);
    //add it to the board
    Turn newTurn = new Turn(guess, hint);
    gameboard = addTurn(gameboard, newTurn);
    }

    /**
     *Gets the current state of the gameboard
     * @return the gameboard
     */
    public Turn[] getGameboard() {
        return gameboard;
    }

    /**
     * Create a Turn array that has the current turn added to the end of board
     * @param board the current board
     * @param turn the latest turn
     * @return A new board with the latest turn added to the board
     */
    private Turn[] addTurn (Turn[]board, Turn turn){
        Turn[] newBoard = new Turn[board.length + 1];
        for (int i = 0; i < board.length; i++) {
            newBoard[i] = board[i];
        }
        newBoard[newBoard.length - 1] = turn;
        return newBoard;
    }

    /**
     * Is the game over?
     * @param board The game in question
     * @return True if the last hint was a five
     */
    private boolean isGameOver (Turn[]board){
        return (board.length > 0 && board[board.length - 1].getHint() == 5);
    }

    /**
     * Returns number of letters in common between two words
     * @param guess the word that is being checked
     * @param testWord the word that is being checked with
     * @return the number of letters in common
     */
    static public int commonLetters (String guess, String testWord){
        int sameCount = 0;
        for (int i = 0; i < guess.length(); i++) {
            for (int j = 0; j < testWord.length(); j++) {
                if (testWord.substring(j, j + 1).equals(guess.substring(i, i + 1))) {
                    sameCount++;
                }
            }
        }
        return sameCount;
    }


}

