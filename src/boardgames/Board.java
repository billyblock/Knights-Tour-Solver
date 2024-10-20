package boardgames;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;

public class Board implements Observable {
    private Square[][] squares;
    private List<InvalidationListener> listeners = new ArrayList<InvalidationListener>();

    public Board(int size, Color darkColor, Color lightColor) {
        squares = new Square[size][size];
        for (int fileIndex = 0; fileIndex < squares.length; ++fileIndex) {
            for (int rankIndex = 0; rankIndex < squares[fileIndex].length; ++rankIndex) {
                if (shouldBeDark(fileIndex, rankIndex)) {
                    squares[fileIndex][rankIndex] = new Square(darkColor);
                } else {
                    squares[fileIndex][rankIndex] = new Square(lightColor);
                }
            }
        }
    }

    public void clearBoard() {
        synchronized (this) {
        for (int fileIndex = 0; fileIndex < squares.length; ++fileIndex) {
            for (int rankIndex = 0; rankIndex < squares[fileIndex].length; ++rankIndex) {
                if (squares[fileIndex][rankIndex].getPiece() != null)
                    squares[fileIndex][rankIndex].removePiece();
            }
        }
            notifyListeners();
        }
    }

    private void notifyListeners() {
        synchronized (this) {
            for(InvalidationListener listener : listeners)
            {
                listener.invalidated(this);
            }
        }
    }

    public boolean shouldBeDark(int fileIndex, int rankIndex) {
        return ((fileIndex + rankIndex) % 2) == 0;
    }

    public int size() {
        return squares.length;
    }

    public Square getSquare(Coordinate aCoordinate) {
        return squares[aCoordinate.fileIndex][aCoordinate.rankIndex];
    }

    public Color getColor(Coordinate aCoordinate) {
        return getSquare(aCoordinate).getColor();
    }

    public boolean isEmpty(Coordinate aCoordinate) {
        return getSquare(aCoordinate).isEmpty();
    }

    public void setPiece(Coordinate aCoordinate, Piece aPiece) {
        getSquare(aCoordinate).setPiece(aPiece);
    }

    public Piece getPiece(Coordinate aCoordinate) {
        return getSquare(aCoordinate).getPiece();
    }

    public void removePiece(Coordinate aCoordinate) {
        getSquare(aCoordinate).removePiece();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

}
