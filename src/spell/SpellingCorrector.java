/**
 * Created by Matt on 9/4/2014.
 */
package spell;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SpellingCorrector implements SpellCorrector {

//    @Test
//    public void test1() throws IOException, NoSimilarWordFoundException {
//        SpellingCorrector testSpell = new SpellingCorrector();
//        testSpell.useDictionary("dictionary.txt");
//        System.out.println(test.toString());
//
//        Assert.assertEquals(test.getNodeCount(), 19);
//        Assert.assertEquals(test.getWordCount(), 6);
//        Assert.assertNotNull(test.find("apple"));
//        Assert.assertNull(test.find("bob"));
//        test.add("bob");
//        Assert.assertEquals(test.getNodeCount(), 21);
//        Assert.assertEquals(test.getWordCount(), 7);
//        Assert.assertNotNull(test.find("bob"));
//        test.add("bob");
//        Assert.assertEquals(test.getNodeCount(), 21);
//        Assert.assertEquals(test.getWordCount(), 7);
//        Assert.assertNotNull(test.find("bob"));
//        System.out.println(testSpell.dictionary.toString());
//
//
//        Assert.assertFalse(false);
//    }

    Words dictionary = new Words();
    public Words test;

//    @Before
//    public void init(){
//        test = new Words();
//        test.add("kick");
//        test.add("kicks");
//        test.add("kicker");
//        test.add("apple");
//        test.add("ape");
//        test.add("brick");
//    }

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
        ArrayList<String> possibles = new ArrayList<String>();
        // if word is in the Trie
        if(dictionary.find(inputWord) != null){
            return inputWord.toLowerCase();
        } else {
            possibles = getEditDistances(inputWord.toLowerCase());
            Map<String,Integer> words = getValidWords(possibles);

            // no similar words found
            if(words.size() == 0){
                possibles = getEditDistances(possibles);
                words = getValidWords(possibles);
                if(words.size() == 0){
                    throw new NoSimilarWordFoundException();
                }
            }

            // get max occurances
            int max = 0;
            for(String word:words.keySet()){
                max = words.get(word) > max ? words.get(word):max;
            }

            // get list of words with max count
            ArrayList<String> validWords = new ArrayList<String>();
            for(String word:words.keySet()){
                if(words.get(word) == max){
                    validWords.add(word);
                }
            }

            // if only 1 return it, else return first alphebetically
            Collections.sort(validWords);
            return validWords.get(0);

        }
    }

    private Map<String,Integer> getValidWords(ArrayList<String> list){
        Map<String,Integer> words = new HashMap<String, Integer>();
        for(String word:list){
            Words.WordNode node = dictionary.find(word);
            if(node != null){
                words.put(word,node.getValue());
            }
        }
        return words;
    }

    private ArrayList<String> getEditDistances(String input){
        ArrayList<String> possibilities = new ArrayList<String>();
        possibilities.addAll(deleteDistance(input));
        possibilities.addAll(insAltDistance(input, 0));
        possibilities.addAll(insAltDistance(input, 1));
        possibilities.addAll(transpositionDistance(input));
        return possibilities;
    }

    private ArrayList<String> getEditDistances(ArrayList<String> list){
        ArrayList<String> possiblities = new ArrayList<String>();
        for(String word:list){
            possiblities.addAll(getEditDistances(word));
        }
        return possiblities;
    }

    private ArrayList<String> deleteDistance(String input){
        ArrayList<String> deleteList = new ArrayList<String>();
       for(int i = 0; i < input.length();i++){
           String newWord = input.substring(0,i).concat(input.substring(i+1));
           deleteList.add(newWord);
       }
        return deleteList;
    }

    private ArrayList<String> insAltDistance(String input,int distance){
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
        return insertList;
    }

    private ArrayList<String> transpositionDistance(String input){
        ArrayList<String> transList = new ArrayList<String>();
        for(int i = 0; i < input.length()-1;i++){
            String newWord = input.substring(0,i)+input.charAt(i+1)+input.charAt(i)+input.substring(i+2);
            transList.add(newWord);
        }
        return transList;
    }


}
