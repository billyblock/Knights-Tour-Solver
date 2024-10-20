package boardgames;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MoveTest {
    private Piece piece = new Piece();
    private Coordinate a1 = new Coordinate('a', 1);
    private Coordinate c2 = new Coordinate('c', 2);

    private Move placePiece = new Move(piece, null, c2);
    private Move movePiece = new Move(piece, c2, a1);
    private Board board;

    @BeforeEach
    public void setUp(){
        board = new Board(8, Color.BLACK, Color.WHITE);
    }

    @AfterEach
    public void tearDown(){
        board = null;
    }

    @Test
    public void testConstructor(){
        assertEquals(piece, placePiece.getPiece());
        assertNull(placePiece.getStart());
        assertEquals(c2, placePiece.getEnd());
        assertEquals(piece, movePiece.getPiece());
        assertEquals(c2, movePiece.getStart());
        assertEquals(a1, movePiece.getEnd());
    }

    @Test
    public void testPlacePieceMove(){
        // from previous test, we know location c2 is empty.
        placePiece.doOn(board);
        assertEquals(piece, board.getPiece(c2));
        placePiece.undoOn(board);
        assertTrue(board.isEmpty(c2));
    }

    @Test void testMovePieceMove(){
        placePiece.doOn(board);
        movePiece.doOn(board);
        assertTrue(board.isEmpty(c2));
        assertEquals(piece, board.getPiece(a1));
        movePiece.undoOn(board);
        assertEquals(piece, board.getPiece(c2));
        assertTrue(board.isEmpty(a1));
    }
}