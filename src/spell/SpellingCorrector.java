/**
 * Created by Matt on 9/4/2014.
 */
package spell;

import java.io.IOException;

public class SpellingCorrector implements SpellCorrector {

    public static void main(String[] args){
        Words test = new Words();
        test.add("kick");
        int nodeCount = test.getNodeCount();
        int wordCount = test.getWordCount();
        test.add("kicks");
        nodeCount = test.getNodeCount();
        wordCount = test.getWordCount();
        test.add("kicker");
        nodeCount = test.getNodeCount();
        wordCount = test.getWordCount();
        test.add("apple");
        nodeCount = test.getNodeCount();
        wordCount = test.getWordCount();
        test.add("ape");
        nodeCount = test.getNodeCount();
        wordCount = test.getWordCount();
        test.add("brick");
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
