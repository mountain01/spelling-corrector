package spell;

import java.io.IOException;

import spell.SpellCorrector.NoSimilarWordFoundException;

/**
 * A simple main class for running the spelling corrector
 */
public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws NoSimilarWordFoundException, IOException {
		
		String dictionaryFileName = args[0];
		String inputWord = args[1];
		
		/**
		 * Create an instance of your corrector here
		 */
		SpellCorrector corrector = new SpellingCorrector();
        Trie trie1 = new Words();

        trie1.add("bob");
        trie1.add("karen");
        trie1.add("smith");
        trie1.add("smal");

        Trie trie2 = new Words();

        trie2.add("bob");
        trie2.add("karen");
        trie2.add("smith");
        trie2.add("smab");

        boolean test = trie1.equals(trie2);

        System.out.println(test);

		corrector.useDictionary(dictionaryFileName);

		String suggestion = corrector.suggestSimilarWord(inputWord);
		
		System.out.println("Suggestion is: " + suggestion);
	}

}
