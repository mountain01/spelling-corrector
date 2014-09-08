/**
 * Created by Matt on 9/4/2014.
 */
package spell;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellingCorrector implements SpellCorrector {

    @Test
    public void test1() throws IOException {
        SpellingCorrector testSpell = new SpellingCorrector();
        testSpell.useDictionary("dictionary.txt");

        Assert.assertEquals(test.getNodeCount(), 19);
        Assert.assertEquals(test.getWordCount(), 6);
        Assert.assertNotNull(test.find("apple"));
        Assert.assertNull(test.find("bob"));
        test.add("bob");
        Assert.assertEquals(test.getNodeCount(), 21);
        Assert.assertEquals(test.getWordCount(), 7);
        Assert.assertNotNull(test.find("bob"));
        test.add("bob");
        Assert.assertEquals(test.getNodeCount(), 21);
        Assert.assertEquals(test.getWordCount(), 7);
        Assert.assertNotNull(test.find("bob"));


        Assert.assertFalse(false);
    }

    Words dictionary = new Words();
    public Words test;
    ArrayList<Words.WordNode> deleteList;
    ArrayList<Words.WordNode> transList;
    ArrayList<Words.WordNode> altList;
    ArrayList<Words.WordNode> insertList;

    @Before
    public void init(){
        test = new Words();
        test.add("kick");
        test.add("kicks");
        test.add("kicker");
        test.add("apple");
        test.add("ape");
        test.add("brick");
    }

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        Scanner in = new Scanner(new File(dictionaryFileName));
        while(in.hasNextLine()){
            dictionary.add(in.nextLine());
        }
        in.close();
    }

    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
        // if word is in the Trie
        if(dictionary.find(inputWord) != null){
            return inputWord.toLowerCase();
        } else {
            getEditLists(dictionary.root,inputWord.length());
        }
        return null;
    }

    private void getEditLists(Words.WordNode Node, int inputLength){
        for(Words.WordNode node:Node.Nodes){
            if(node != null){
                int nodeLength = node.getName().length();
                if(nodeLength == inputLength+1 && node.getValue() > 0){
                    deleteList.add(node);
                }
                if(nodeLength == inputLength && node.getValue() > 0){
                    transList.add(node);
                    altList.add(node);
                }
                if(nodeLength == inputLength - 1 && node.getValue() > 0){
                    insertList.add(node);
                }
                getEditLists(node,inputLength);
            }
        }
    }
}
