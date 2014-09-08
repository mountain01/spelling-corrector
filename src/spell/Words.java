package spell;

/**
 * Created by Matt on 9/4/2014.
 */
public class Words implements Trie  {
    WordNode root = new WordNode();
    int nodeCount =1;
    int wordCount=0;

    @Override
    public void add(String word) {
        WordNode temp = find(word);
        if (temp != null){
            temp.count++;
            return;
        }
        temp = root;
        StringBuilder name = new StringBuilder();
        for(char c:word.toLowerCase().toCharArray()){
            if(temp.Nodes[c-'a'] == null){
                temp.Nodes[c-'a'] = new WordNode();
                nodeCount++;
            }
            temp = temp.Nodes[c-'a'];
            name.append(c);
            temp.setName(name.toString());
        }
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

    public class WordNode implements Trie.Node {

        public WordNode[] Nodes = new WordNode[26];
        private int count=0;
        private String name;

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
