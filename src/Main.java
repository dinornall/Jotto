public class Main {
    /*
    NAME: Dinor Nallbani

    JOTTO: A game where a guesser has to guess a five letter word that a Word Monarch chooses

    HOW TO PLAY:
    1. A Word Monarch chooses a five letter word.
    2. The guesser picks a word that fits the rules of being 5 letters, having no repeating letters, and shares the same
    amount of letters with the previous guesses as the hint given for the guess
    3. The Word Monarch gives a hint for that guess
    4. Steps 2 and 3 are repeated until word is found (when hint equals five)

    BELLS AND WHISTLES:

    1.) Choosing Word Monarch Type:
        Can choose between a human or computer Word Monarch
    2.) Choosing Guesser Type:
        Can choose between a human, linear search, or a "smart" search
    3.) Has a "smart" search:
        Finds the guess with the lowest standard deviation on all the possible hints,
        and returns the most average guess
     */
    public static void main(String[] args) {
	    Jotto jotto = new Jotto();
        jotto.playGame();
//        Jotto jotto = new Jotto(true);
//        jotto.runTest();
    }
}
