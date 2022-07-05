/**
 * Keeps track of a full turn in a Jotto game
 * A guess and its corresponding hint
 */
public class Turn {
    /** A Jotto valid word input by the guesser */
    final private String guess;
    /** The number of correct letter in guess */
    final private int hint;

    /**
     * CONSTRUCTOR: creates a turn object from a guess and a hint
     * @param guess A Jotto valid word input by the guesser
     * @param hint A corresponding hint from the WordMonarch
     */
    public Turn (String guess, int hint) {
        this.guess = guess;
        this.hint = hint;
    }

    /**
     * ACCESSOR: Returns the guess
     * @return the valid Jotto guess
     */
    public String getGuess() {
        return guess;
    }

    /**
     * ACCESSOR: Returns the hint
     * @return the corresponding hint from the WordMonarch
     */
    public int getHint() {
        return hint;
    }

    /**
     * ACCESSOR: Returns a string of the turn
     * @return A string of the turn in guess + " " + hint
     */
    public String toString() {
        return guess + " " + hint;
    }
}
