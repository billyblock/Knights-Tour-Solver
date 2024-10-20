package boardgames;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javafx.scene.paint.Color;

public class BoardTest {

    private Board board;
    private Coordinate a1;
    private Coordinate c2;

    @BeforeEach
    public void setUp(){
        board = new Board(8, Color.BLACK, Color.WHITE);
        a1 = new Coordinate('a', 1);
        c2 = new Coordinate('c', 2);
    }

    @AfterEach
    public void tearDown(){
        board = null;
    }

    @Test
    public void testConstructor(){
        assertEquals(8, board.size());
        assertEquals(Color.BLACK, board.getColor(a1));
        assertEquals(Color.WHITE, board.getColor(c2));
        assertEquals(Color.WHITE, board.getSquare(c2).getColor());
    }

    @Test
    public void testPieceManagement(){
        assertTrue(board.isEmpty(a1));
        assertTrue(board.isEmpty(c2));
        Piece aPiece = new Piece();
        board.setPiece(a1, aPiece);
        assertEquals(aPiece, board.getPiece(a1));
        assertFalse(board.isEmpty(a1));
        assertTrue(board.isEmpty(c2));
        board.removePiece(a1);
        assertNull(board.getPiece(a1));
        assertTrue(board.isEmpty(a1));
    }
}
