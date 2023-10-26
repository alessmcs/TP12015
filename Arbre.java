// ------------------------- auteur, HAYS Océane 20240742, MANCAS Alessandra 20249098 -------------------------------

import java.util.*;

public class Arbre {

    private Stack<Lettre> listeChemin = new Stack<>(); // for the coordinates

    // Tree constructor
    public Arbre(String[] listeMots) {
        for( String mot: listeMots ) this.insert( mot );
    }
    protected final TrieNode root = new TrieNode(' '); // root of the Trie ; empty node



    // Méthode "insert" par François Major (2023), dans les notes du cours IFT2015.
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

    /*
        La méthode trouverDebut nous permet de trouver, dans la grille, les enfants d'une lettre
         (parent) à partir de la liste de ses voisins. Elle construit une liste de voisins "valides",
         et itère ensuite à travers celle-ci pour traverser la grille et l'arbre récursivement.
     */
    public int trouverProchain(Lettre[] voisins, Lettre parentChar, TrieNode currentNode){
        TrieNode parent;
        TrieNode rootChild = root.children.get(parentChar.caractere);

        if (rootChild == currentNode){ // Si le nœud courant est un enfant direct de la racine
            parent = currentNode;
            listeChemin.clear();
            listeChemin.push(parentChar);

        } else {
            parent = currentNode;
            listeChemin.push(parentChar);
        }

        ArrayList<Lettre> listeEnfants = new ArrayList<>(); // nouvelle liste pour le chemin des coordonnées

        if(currentNode.isWord && currentNode.children.isEmpty() ) {
            Command.addToList(cheminToString(listeChemin));
            return listeChemin.size();
        }

        // Cette boucle permet de trouver tous les chemins potentiels à prendre à partir de la lettre parente.
        for (TrieNode node : parent.children.values() ){
            for (Lettre voisin : voisins) {

                if (node.nodeCharacter == parentChar.caractere) { // double lettre dans le mot
                    if (!listeEnfants.contains(parentChar)) listeEnfants.add(parentChar);
                }
                if (node.nodeCharacter == voisin.caractere) {
                    listeEnfants.add(voisin);

                    if (node.isWord && node.children.isEmpty()) { // si n.isWord = true et n est une feuille
                        listeChemin.push(voisin);
                        String out = cheminToString(listeChemin);
                        Command.addToList(out);
                        listeChemin.pop();

                    } else if (node.isWord) { // n.isWord = true mais il reste des enfants, donc il faut continuer à descendre
                        listeChemin.push(voisin);
                        Command.addToList(cheminToString(listeChemin));
                        listeChemin.pop();
                        // aucun retour, chercher le reste du mot
                    }
                }
            }
        }

        if (listeEnfants.size() == 0){ // aucun enfant valide, mot introuvable pour ce chemin
            this.listeChemin.pop();
            return listeChemin.size();
        }

        // construire l'appel récursif pour chaque enfant valide
        for (Lettre listeEnfant : listeEnfants) {

            int nouvX = listeEnfant.indexX;
            int nouvY = listeEnfant.indexY;

            int currentTaille = listeChemin.size();

            char caractParent = listeEnfant.caractere;

            TrieNode nouvParent = parent.children.get(caractParent);

            Lettre lettreParent = listeEnfant;

            Lettre[] nouvVoisins = Command.trouverVoisin(nouvX, nouvY);

            int nouvCheminTaille = trouverProchain(nouvVoisins, lettreParent, nouvParent);

            int difference = nouvCheminTaille - currentTaille;

            // réduire le stack pour pouvoir ajouter un chemin alternatif à la prochaine itération
            for (int j = 0; j < difference; j++) {
                listeChemin.pop();
            }
        }
        return listeChemin.size();
    }

    /*
     La méthode cheminToString transforme le Stack du chemin (de lettre) en un
     */
    public String cheminToString(Stack chemin){
        Stack stackCopy = (Stack) chemin.clone();
        int size = chemin.size();
        StringBuilder result = new StringBuilder();

        ArrayList<String> coordList = new ArrayList<>();
        ArrayList<String> motList = new ArrayList<>();

        for (int i = 0; i < size; i++){
            Lettre element = (Lettre) stackCopy.pop();
            String string = "( " + element.indexX + " , " + element.indexY + " )";
            coordList.add(string);
            motList.add(String.valueOf(element.caractere));
        }

        Collections.reverse(coordList);
        Collections.reverse(motList);

        for (String s : motList) {
            result.append(s);
        }
        result.append(" ");

        for (int j = 0; j < motList.size(); j++){
            if (j != motList.size() - 1) {
                result.append(coordList.get(j)).append(" -> ");
            } else { result.append(coordList.get(j)); }
        }

        return result.toString();
    }

}