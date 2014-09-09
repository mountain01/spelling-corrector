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
    public void test1() throws IOException, NoSimilarWordFoundException {
        SpellingCorrector testSpell = new SpellingCorrector();
        testSpell.suggestSimilarWord("Tree");
//        testSpell.useDictionary("dictionary.txt");

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
    ArrayList<String> possibilities = new ArrayList<String>();

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
            getEditDistances(inputWord.toLowerCase());
        }
        return null;
    }

    public void getEditDistances(String input){
        deleteDistance(input);
        insAltDistance(input,0);
        insAltDistance(input,1);
        transpositionDistance(input);
    }

    public void deleteDistance(String input){
        ArrayList<String> deleteList = new ArrayList<String>();
       for(int i = 0; i < input.length();i++){
           String newWord = input.substring(0,i).concat(input.substring(i+1));
           deleteList.add(newWord);
       }
    }

    public void insAltDistance(String input,int distance){
        ArrayList<String> insertList = new ArrayList<String>();
        for(int i = 0;i<26;i++){
            char c = (char) ('a'+i);
            for(int k = 0;k<input.length();k++){
                String newWord = input.substring(0,k)+ c + input.substring(k+distance);
                insertList.add(newWord);
            }
            if(distance == 0){
                insertList.add(input+c);
            }
        }

    }

    public void transpositionDistance(String input){

    }

}
