
import java.util.*;

public class Arbre {

    private String[] listeMots;
    private Stack<Lettre> listeChemin = new Stack<Lettre>(); // for the coordinates

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
    public int trouverDebut(Lettre[] voisins, Lettre parentChar, TrieNode currentNode){
        TrieNode parent = null;

        if ( currentNode == root ){
            parent = currentNode.children.get(parentChar.caractere); // find the first letter in the 1st level of children
            listeChemin.push(parentChar); // add the valid letter to the stack

        } else {
            parent = currentNode;
            listeChemin.push(parentChar);
        }

        int startTaille = listeChemin.size();

        ArrayList<Lettre> listeEnfants = new ArrayList<Lettre>(); // liste pour le chemin des coordonnées

        if(currentNode.isWord && currentNode.children.isEmpty() ) {
            System.out.println("Mot trouvé!");
            //System.out.println(cheminToString(listeChemin)); //TODO HANDLE CHEMINS TO ORDERED OUTPUT & output duplicates
            Command.addToList(cheminToString(listeChemin));
            return listeChemin.size();
        }

        // this loop gives la liste des voisins de la 1re lettre, donc des chemins potentiels pour trouver le mot
        for (TrieNode n : parent.children.values() ){
            for ( int i = 0 ; i < voisins.length ; i++){
                if (n.nodeCharacter == voisins[i].caractere) { // check if children of parent contain the character(s) we want

                    listeEnfants.add(voisins[i]); // ajouter à la liste des enfants

                    if (n.isWord && n.children.isEmpty()){ // if n is a word and is a leaf & there's multiple valid children


                        System.out.println("Mot trouvé!");
                        listeChemin.push(voisins[i]); // add the last letter to the list
                        //System.out.println(cheminToString(listeChemin)); //TODO HANDLE CHEMINS TO ORDERED OUTPUT
                        String out = cheminToString(listeChemin);
                        Command.addToList(out);


                        listeChemin.pop();

                    } else if(n.isWord){ // n is a word and has children
                        // TODO
                        System.out.println("Mot trouvé!");
                        listeChemin.push(voisins[i]); // add the last letter to the list
                        //System.out.println(cheminToString(listeChemin));
                        Command.addToList(cheminToString(listeChemin));
                        listeChemin.pop() ;
                        // no return , look for the rest of the word
                    }

                }
            }
        }

//        int actualSize = listeChemin.size() - startTaille;
//        if (actualSize == 0) return listeChemin.size();
//        for (int i = 0; i < actualSize; i++ ){
//            listeChemin.pop();
//        }

        if (listeEnfants.size() == 0){

            System.out.println("Mot introuvable pour ce chemin");
            this.listeChemin.pop(); // remove the last letter we just added
            return listeChemin.size(); // return now

//            for(int i = 0; i <indexMot; i++){
//                indexMot --;
//                listeChemin.pop();
//            }

        }

        for (int i = 0; i < listeEnfants.size() ; i++){

            int nouvX = listeEnfants.get(i).indexX;
            int nouvY = listeEnfants.get(i).indexY;

            int currentTaille = listeChemin.size();

            char caractParent = listeEnfants.get(i).caractere;

            TrieNode nouvParent = parent.children.get(caractParent);

            Lettre lettreParent = listeEnfants.get(i);

            Lettre[] nouvVoisins = Command.trouverVoisin(nouvX, nouvY);

            int nouvCheminTaille = trouverDebut(nouvVoisins, lettreParent, nouvParent);

            int difference = nouvCheminTaille - currentTaille;

            // delete the letters you added, go back in the chemin????
            for (int j= 0; j < difference ; j++){
                listeChemin.pop();
            }

        }

        return listeChemin.size();
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

        return result + "\n";
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