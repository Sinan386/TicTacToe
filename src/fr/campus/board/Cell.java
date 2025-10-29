package fr.campus.board;

/**
 * Représente une cellule individuelle du plateau de jeu.
 * Chaque cellule peut contenir un symbole (par exemple "X" ou "O") ou être vide.
 *
 * @author Campus
 * @version 1.0
 */
public class Cell {

    /**
     * Le symbole contenu dans cette cellule.
     * Une chaîne vide indique que la cellule est vide.
     */
    private String symbol = "";

    /**
     * Retourne une représentation formatée de la cellule pour l'affichage.
     * Le symbole est entouré d'espaces pour améliorer la lisibilité.
     * Si la cellule est vide, affiche trois espaces.
     *
     * @return la représentation formatée de la cellule (exemple : " X " ou "   ")
     */
    public String getRepresentation(){
        return " " + (symbol.isEmpty() ? " " : symbol) + " ";
    }

    /**
     * Vérifie si la cellule est vide.
     * Une cellule est considérée comme vide si son symbole est une chaîne vide.
     *
     * @return true si la cellule est vide, false sinon
     */
    public boolean isEmpty() {
        return symbol.isEmpty();
    }

    /**
     * Retourne le symbole contenu dans cette cellule.
     *
     * @return le symbole de la cellule (chaîne vide si la cellule est vide)
     */
    public String getSymbol(){
        return symbol;
    }

    /**
     * Définit le symbole pour cette cellule.
     *
     * @param symbol le symbole à placer dans la cellule (par exemple "X" ou "O")
     */
    public void setSymbol (String symbol){
        this.symbol = symbol;
    }
}