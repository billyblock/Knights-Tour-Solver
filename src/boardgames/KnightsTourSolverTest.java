package boardgames;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class KnightsTourSolverTest {

    private KnightsTourSolver solver;
    private Board board; 
    private Coordinate c2 = new Coordinate(2, 1);
    private Coordinate a1 = new Coordinate(0,0);

    @BeforeEach
    public void setUp(){
        board = new Board(6, Color.BLACK, Color.WHITE);
        solver = new KnightsTourSolver(board, c2);
    }

    @Test
    public void testConstructor(){
        assertFalse(solver.getBoard().isEmpty(c2));
        assertEquals(1, solver.getMoves().size());
        assertEquals(c2, solver.getMoves().get(0).getEnd());
    }

    @Test
    public void testHasSolvedPuzzle(){
        Move dummyMove = new Move(new Knight(), null, null);
        for(int i = 1; i < board.size() * board.size(); ++i){
            assertFalse(solver.hasSolvedPuzzle());
            solver.getMoves().add(dummyMove);
        }
        assertTrue(solver.hasSolvedPuzzle());
    }


    @Test
    public void testNextLegalMoves(){
        Move aMove = new Move(new Knight(), c2, a1);
        solver.make(aMove);
        List<Move> nextMoves = solver.nextLegalMoves();
        assertEquals(1, nextMoves.size());
        Coordinate location = new Coordinate(1, 2);
        Move nextMove = nextMoves.get(0);
        assertEquals(location, nextMove.getEnd());
    }

    @Test
    public void testSolving(){
        assertTrue(solver.solve());
        System.out.println("The puzzle has beed solved my dude. ");
    }

    @Test
    public void testHasVisited(){
        assertTrue(solver.hasVisited(c2));
        assertFalse(solver.hasVisited(a1));
    }
}
