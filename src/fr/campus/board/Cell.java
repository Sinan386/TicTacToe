package fr.campus.board;


public class Cell {

    private String symbol = "";

    public String getRepresentation(){
        return " " + (symbol.isEmpty() ? " " : symbol) + " ";
    }

    public boolean isEmpty() {
        return symbol.isEmpty();

    }

    public String getSymbol(){
        return symbol;
    }

    public void setSymbol (String symbol){
        this.symbol = symbol;
    }

}
