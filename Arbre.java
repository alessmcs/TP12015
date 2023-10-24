
import java.util.*;

public class Arbre {

    private String[] listeMots;
    private Stack<Lettre> listeChemin = new Stack<Lettre>(); // for the coordinates

    private int indexMot = 0;


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


    // La méthode trouverDebut nous permet de trouver la première et deuxième lettre des mots qu'on cherche,
    // et créer une liste à partir des voisins du parent, pour qu'on trouve tous les chemins possibles

    //TODO: listeChemin is empty when i exit the first loop

    public ArrayList<Lettre> trouverDebut(Lettre[] voisins, Lettre parentChar, TrieNode currentNode){
        TrieNode parent = null;

        if ( currentNode == root ){
            parent = currentNode.children.get(parentChar.caractere); // find the first letter in the 1st level of children
            listeChemin.push(parentChar); // add the valid letter to the stack
            indexMot ++;
        } else {
            parent = currentNode;
            listeChemin.push(parentChar);
            indexMot ++;
        }
        //ArrayList<TrieNode> listeEnfants = new ArrayList<TrieNode>(); // liste pour le chemin des coordonnées

        ArrayList<Lettre> listeEnfants = new ArrayList<Lettre>(); // liste pour le chemin des coordonnées


        // this loop gives la liste des voisins de la 1re lettre, donc des chemins potentiels pour trouver le mot
        for (TrieNode n : parent.children.values() ){

            for ( int i = 0 ; i < voisins.length ; i++){
                if (n.nodeCharacter == voisins[i].caractere) { // check if children of parent contain the character(s) we want

                    listeEnfants.add(voisins[i]); // ajouter à la liste des enfants

                    if (n.isWord && n.children == null){ // if n is a word and is a leaf
                        System.out.println("Mot trouvé!");
                        listeChemin.push(voisins[i]); // add the last letter to the list
                        indexMot ++;
                        System.out.println(cheminToString(listeChemin));

                        for(int j = 0; j<indexMot; j++){
                            indexMot --;
                            listeChemin.pop();
                        }

                        return listeEnfants;
                        // add an iterator that increases every time you add a letter, subtract it from stack.size when
                        // you return, so that only part of the stack remains and you can start anew
                    } else if(n.isWord){
                        // TODO
                        System.out.println("Mot trouvé!");
                        listeChemin.push(voisins[i]); // add the last letter to the list
                        indexMot ++;
                        System.out.println(cheminToString(listeChemin));
                        listeChemin.pop() ; indexMot--;

                        // no return , look for the rest of the word
                    }


                    // once the word is found return the word & the coords list then pop it for the amount of
                    // times you've added a letter
                    // then return everything
                }

            }
        }

        if (listeEnfants.size() == 0){
            // TODO
            // check when you pushed the Lettre object to ListeEnfants
            System.out.println("Mot trouvé!");
            this.listeChemin.pop(); // add the last letter to the list
            indexMot --;
            System.out.println(cheminToString(listeChemin));

            for(int i = 0; i <indexMot; i++){
                indexMot --;
                listeChemin.pop();
            }
        }

        for (int i = 0; i < listeEnfants.size() ; i++){

            int nouvX = listeEnfants.get(i).indexX;
            int nouvY = listeEnfants.get(i).indexY;


            char caractParent = listeEnfants.get(i).caractere;

            TrieNode nouvParent = parent.children.get(caractParent);

            Lettre lettreParent = listeEnfants.get(i);

            Lettre[] nouvVoisins = Command.trouverVoisin(nouvX, nouvY);

            trouverDebut(nouvVoisins, lettreParent, nouvParent);
        }

        return listeEnfants;
    }

    // TODO: make a method to update stack

    public String cheminToString(Stack chemin){
        Stack stackCopy = (Stack) chemin.clone();
        int size = chemin.size();
        String result = "";

        ArrayList<String> coordList = new ArrayList<String>();
        ArrayList<String> motList = new ArrayList<String>();

        for (int i = 0; i < size; i++){
            Lettre element = (Lettre) stackCopy.pop();
            String string = "( " + element.indexX + " , " + element.indexY + " )";
            coordList.add(string);
            motList.add(String.valueOf(element.caractere));
        }

        // reverse the arrayLists
        Collections.reverse(coordList);
        Collections.reverse(motList);

        // build the final string
        for (int j = 0; j < motList.size(); j++){
            result += (motList.get(j));
        }
        result += " ";

        for (int j = 0; j < motList.size(); j++){
            if (j != motList.size() - 1) {
                result += (coordList.get(j) + " -> ");
            } else { result += coordList.get(j); }
        }

        return result;
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




}