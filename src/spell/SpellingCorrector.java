/**
 * Created by Matt on 9/4/2014.
 */
package spell;

import java.io.IOException;

public class SpellingCorrector implements SpellCorrector {

    public static void main(String[] args){
        Words test = new Words();
        test.add("abc");
        int nodeCount = test.getNodeCount();
        int wordCount = test.getWordCount();
        test.add("testing");
        test.add("testing");
        nodeCount = test.getNodeCount();
        wordCount = test.getWordCount();
        test.add("tell");
        nodeCount = test.getNodeCount();
        wordCount = test.getWordCount();
        test.add("teller");
        nodeCount = test.getNodeCount();
        wordCount = test.getWordCount();
        test.add("telling");
        nodeCount = test.getNodeCount();
        wordCount = test.getWordCount();
    }

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

    }

    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
        return null;
    }
}
