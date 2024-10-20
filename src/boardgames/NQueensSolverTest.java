package boardgames;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.paint.Color;
import java.util.List;

public class NQueensSolverTest {

    private Board board;
    private NQueensSolver solver;
    private Coordinate a1 = new Coordinate(0, 0);
    private Coordinate d2 = new Coordinate(3,1);
    private Coordinate b3 = new Coordinate('b', 3);
    private Coordinate e3 = new Coordinate('e', 3);
    private Coordinate f3 = new Coordinate('f', 3);

    private Move qAtA1 = new Move(new Queen(), null, a1);
    private Move qAtD2 = new Move(new Queen(), null, d2);

    @BeforeEach
    public void setUp(){
        board = new Board(8, Color.BLACK, Color.WHITE);
        solver = new NQueensSolver(board);
    }

    @AfterEach
    public void tearDown(){
        board = null;
        solver = null;
    }

    @Test
    public void testHasSolvedPuzzle() {
        Move dummyMove = new Move(new Queen(), null, null);
        for(int i = 0; i < board.size(); ++i){
            assertFalse(solver.hasSolvedPuzzle());
            solver.getMoves().add(dummyMove);
        }
        assertTrue(solver.hasSolvedPuzzle());
    }

    @Test
    public void testNextLegalMoves() {
        solver.make(qAtA1);
        solver.make(qAtD2);
        List<Move> nextMoves = solver.nextLegalMoves();
        assertEquals(4, nextMoves.size());
        Move aMove = nextMoves.get(0);
        assertEquals(b3, aMove.getEnd());
        aMove = nextMoves.get(1);
        assertEquals(f3, aMove.getEnd());
    }

    @Test
    public void testIsSafe(){
        solver.make(qAtA1);
        solver.make(qAtD2);
        assertTrue(solver.isSafe(b3));
        assertTrue(solver.isSafe(f3));
        assertFalse(solver.isSafe(e3));
    }

    @Test
    public void testIsThreat(){
        assertTrue(solver.isThreat(e3, d2));
        assertFalse(solver.isThreat(e3, a1));
    }

    @Test
    public void testSolving(){
        assertTrue(solver.solve());
        System.out.println("The puzzle has been solved!");
    }
}
