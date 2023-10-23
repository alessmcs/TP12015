import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    protected Map<Character, TrieNode> children; // Map for children nodes
    protected boolean isWord; // boolean for marking word nodes; remove the prefix restriction
    protected char nodeCharacter;
    // constructor
    public TrieNode(char c) {
        //this.parent = parent;
        children = new HashMap<Character, TrieNode>(); // empty HashMap for children
        this.isWord = false; // a node is not storing a word by default
        this.nodeCharacter = c;
        //this.hasNext = true;
    }
    @Override
    public String toString() {
        return "Node< " + this.children.size() + " children, is a word? " + this.isWord + " >";
    }

}
