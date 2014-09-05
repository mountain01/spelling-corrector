package spell;

/**
 * Created by Matt on 9/4/2014.
 */
public class Words implements Trie  {
    WordNode root = new WordNode();

    @Override
    public void add(String word) {
        WordNode temp = root;
        for(char c:word.toLowerCase().toCharArray()){
            if(temp.Nodes[c-'a'] == null){
                temp.Nodes[c-'a'] = new WordNode();
            }
            temp = temp.Nodes[c-'a'];
        }
        temp.count++;
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
        return recursiveWordCount(root);
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
        return 1+recursiveNodeCount(root);
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

    private class WordNode implements Trie.Node {

        public WordNode[] Nodes = new WordNode[26];
        private int count=0;

        @Override
        public int getValue() {
            return count;
        }
    }
}
