package boardgames;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

public class NQueensSolver extends PuzzleSolver{

    

    public NQueensSolver(Board aBoard) {
        super(aBoard);
    }

    @Override
    public boolean hasSolvedPuzzle() {
        return moves.size() == board.size();
    }

    @Override
    public List<Move> nextLegalMoves() {
        List<Move> nextMoves = new ArrayList<Move>();
        int currentRankIndex = moves.size();
        for(int i = 0; i < board.size(); ++i){
            Coordinate aCoord = new Coordinate(i, currentRankIndex);
            if(isSafe(aCoord)){
                Move aMove = new Move(new Queen(), null, aCoord);
                nextMoves.add(aMove);
            }
        }
        return nextMoves;
    }

    public boolean isSafe(Coordinate aCoord){
        for(Move eachMove: moves){
            if(isThreat(aCoord, eachMove.getEnd())){
                return false;
            }
        }
        return true;
    }

    public boolean isThreat(Coordinate aCoord, Coordinate qCoord){
        return aCoord.fileIndex == qCoord.fileIndex ||
        aCoord.rankIndex == qCoord.rankIndex        ||
        abs(aCoord.fileIndex - qCoord.fileIndex) == abs(aCoord.rankIndex - qCoord.rankIndex);
    }
}
