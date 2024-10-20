package boardgames;

import javafx.scene.paint.Color;

public class Square {
    private Color color;
    private Piece piece;

    public Square(javafx.scene.paint.Color darkColor){
        color = darkColor;
    }

    public Color getColor(){
        return color;
    }

    public Piece getPiece(){
        return piece;
    }

    public boolean isEmpty(){
        return piece == null;
    }

    public void setPiece(Piece aPiece){
        piece = aPiece;
    }
    public void removePiece(){
        piece = null;
    }
}
