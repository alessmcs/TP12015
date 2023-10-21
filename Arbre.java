
import java.util.*;

public class Arbre {

    private String[] listeMots;


    // Tree constructor
    public Arbre(String[] listeMots) {
        this.listeMots = listeMots;
        for( String mot: listeMots ) this.insert( mot );
    }
    protected final TrieNode root = new TrieNode(' '); // root of the Trie ; empty node

    // Code du Trie basé sur les notes de cours
    public void insert( String word ) {
        // insert a word in the Trie
        TrieNode node = root; // take the root
        for( char c: word.toCharArray() ) {
            // if there is no node with char c in the children, create it
            if( node.children.get( c ) == null ) node.children.put( c, new TrieNode(c) );

            node = node.children.get( c ); // move to it to process the next char
        }
        node.isWord = true; // last node accessed was for the last char of word

    }


    // TODO: maybe make it a list of lettre object instead of chars????
    // La méthode trouverDebut nous permet de trouver la première et deuxième lettre des mots qu'on cherche,
    // et créer une liste à partir des voisins du parent, pour qu'on trouve tous les chemins possibles
    public  ArrayList<TrieNode> trouverDebut(Lettre[] voisins, char parentChar, TrieNode currentNode){
        TrieNode parent = null;

        if ( currentNode == root ){
            parent = currentNode.children.get(parentChar); // find the first letter in the 1st level of children
        } else {
            parent = currentNode;
        }
        ArrayList<TrieNode> listeEnfants = new ArrayList<TrieNode>(); // liste pour le chemin des coordonnées
        ArrayList<int[]> listeChemin = new ArrayList<>(); // liste pour les coordonnées

        // this loop gives la liste des voisins de la 1re lettre, donc des chemins potentiels pour trouver le mot
        for (TrieNode n : parent.children.values() ){
            for ( int i = 0 ; i < voisins.length ; i++){
                if (n.caractere == voisins[i].caractere) { // check if children of parent contain the character(s) we want
                    listeEnfants.add(n); // ajouter à la liste des enfants
                    int[] tabCoords = { voisins[i].indexX, voisins[i].indexY};
                    listeChemin.add( tabCoords ); // ajouter à la liste des coordonnées
                    if (n.isWord) System.out.println("Mot trouvé!");
                }
            }
        }

        for (int i = 0; i < listeEnfants.size() ; i++){
            // use the index attributes with the letters!!!
            TrieNode nouvParent = listeEnfants.get(i);
            Lettre[] nouvVoisins = Command.trouverVoisin(listeChemin.get(i)[0], listeChemin.get(i)[1]);
            trouverDebut(nouvVoisins, nouvParent.caractere, nouvParent);
        }

        // now we've found the list of children, which is made of TrieNodes, so we can look at its children and move
        // down the word recursively & moving along the grid

        return listeEnfants;
    }




    public void searchChildren(TrieNode parent, char[] array, int index){
        // TrieNode currentNode = parent;
        // ArrayList to add elements for the word (indexes from the 2d grid)
        // make a pointer for the branch & recursive call there when you're back
        //TODO: throw exceptions
        TrieNode childNode = null;
        TrieNode branchNode = parent; // pointer ; temp value for index so I can return to it when i go back up the branch

        ArrayList<int[]> chemin = new ArrayList<>(); // maybe mets à l'extérieur
        char character = array[index];

        if(parent.children != null) childNode = parent.children.get(character); // if it's not null keep moving down the branch

        if (childNode != null && childNode.isWord == false){
            // if not null, recursive call until isWord or null (so only part of the word is in the array not the whole thing)
            // childNode becomes parent, array stays the same but index advances
            searchChildren(childNode,array,++index);

        } else if (childNode != null && childNode.isWord == true){
            System.out.println("Word found!");
            // when the word is found, return to the base of the branch and check the array for an earlier index
            // if it's the end of the branch, go back to the parent and check for other children
            if(childNode.children == null) searchChildren(branchNode,array,++index);
            // if it's not the end of the branch & the word is found, keep advancing until the next word
            if(childNode.children != null) searchChildren(childNode,array,++index);

        } else if (childNode==null){
            searchChildren(parent, array, ++index);
        } else {
            System.out.println("No word in sub-grid");
        }

    }


    // TODO: find a way to return the word we found

}