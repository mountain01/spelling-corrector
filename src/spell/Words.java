package spell;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Matt on 9/4/2014.
 */
public class Words implements Trie  {
    WordNode root = new WordNode();
    int nodeCount =1;
    int wordCount=0;
    HashMap<String, Integer> toStringMap = new HashMap<String,Integer>();


    @Override
    public void add(String word) {
        WordNode temp = find(word);
        if (temp != null){
            temp.count++;
            return;
        }
        temp = root;
        for(char c:word.toLowerCase().toCharArray()){
            if(temp.Nodes[c-'a'] == null){
                temp.Nodes[c-'a'] = new WordNode();
                nodeCount++;
            }
            temp = temp.Nodes[c-'a'];
        }
        temp.setName(word);
        temp.count++;
        wordCount++;
    }

    @Override
    public WordNode find(String word) {
        WordNode temp = root;
        for(char c:word.toLowerCase().toCharArray()){
            if(temp.Nodes[c-'a'] == null){
                return null;
            }
            temp = temp.Nodes[c-'a'];
        }
        return temp.count == 0 ? null:temp;
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    private int recursiveWordCount(WordNode node){
        int count = 0;
        for(WordNode myNode: node.Nodes){
            if(myNode != null){
                count += recursiveWordCount(myNode);
            }
        }
        return count+=node.count>0?1:0;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    private int recursiveNodeCount(WordNode node){
        int count = 0;
        for(WordNode myNode:node.Nodes){
            if(myNode != null){
                count += 1+recursiveNodeCount(myNode);
            }
        }
        return count;
    }

    /**
     * The toString specification is as follows:
     * For each word, in alphabetical order:
     * <word> <count>\n
     */
    @Override
    public String toString(){
        StringBuilder returnString;
        returnString = getString(root);
        return returnString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Words)) return false;

        Words words = (Words) o;

        if(nodeCount == words.nodeCount && wordCount == words.wordCount){
            return root.equals(words.root);
        }
        else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        int result = root != null ? root.hashCode() : 0;
        result = 31 * result + nodeCount;
        result = 31 * result + wordCount;
        result = 31 * result + (toStringMap != null ? toStringMap.hashCode() : 0);
        return result;
    }

    public StringBuilder getString(WordNode node){
        StringBuilder returnString = new StringBuilder();
        for(WordNode n:node.Nodes){
            if(n != null){
                if(n.count > 0){
                    returnString.append(n.getName()).append(" ").append(n.getValue()).append("\n");
                }
                returnString.append(getString(n));
            }
        }
        return returnString;
    }


    public class WordNode implements Trie.Node {

        public WordNode[] Nodes = new WordNode[26];
        private int count=0;
        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WordNode wordNode = (WordNode) o;

            if (count != wordNode.count) return false;
            if (name != null ? !name.equals(wordNode.name) : wordNode.name != null) return false;
            for(int i = 0;i<26;i++){
                if(this.Nodes[i] == null && wordNode.Nodes != null){
                    return false;
                }
                else if(this.Nodes[i] != null && wordNode.Nodes == null){
                    return false;
                }
                else if(this.Nodes[i] == null && wordNode.Nodes == null){
                    continue;
                }
                else if(!this.Nodes[i].equals(wordNode.Nodes[i])){
                    return false;
                }

            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = count;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public int getValue() {
            return count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
