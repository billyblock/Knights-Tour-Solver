package boardgames;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class PuzzleSolver {
    protected Board board;
    protected ObservableList<Move> moves;

    public PuzzleSolver(Board aBoard){
        board = aBoard;
        moves = FXCollections.observableArrayList();
    }

    public abstract boolean hasSolvedPuzzle();
    public abstract List<Move> nextLegalMoves();


    public boolean solve(){
        if(hasSolvedPuzzle()){
            return true;
        }
        List<Move> nextMoves = nextLegalMoves();
        for(Move eachMove: nextMoves){
            make(eachMove);
            if(solve()){
                return true;
            }
            unMakeLastMove();
        }
        return false;
    }

    public void make(Move aMove){
        aMove.doOn(board);
        moves.add(aMove);
    }

    public void unMakeLastMove(){
        Move aMove = moves.remove(moves.size() - 1);
        aMove.undoOn(board);
    }

    public Board getBoard(){
        return board;
    }

    public ObservableList<Move> getMoves(){
        return moves;
    }
}
