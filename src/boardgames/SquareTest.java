package boardgames;

import javafx.scene.paint.Color;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {
    private Square darkSquare;
    private Square lightSquare;

    @BeforeEach
    public void setUp(){
        darkSquare = new Square(Color.BLACK);
        lightSquare = new Square(Color.WHITE);
    }

    @AfterEach
    public void tearDown(){
        darkSquare = null;
        lightSquare = null;
    }

    @Test
    public void testConstructor(){
        assertEquals(Color.BLACK, darkSquare.getColor());
        assertEquals(Color.WHITE, lightSquare.getColor());
        assertNull(darkSquare.getPiece());
        assertNull(lightSquare.getPiece());
        assertTrue(darkSquare.isEmpty());
        assertTrue(lightSquare.isEmpty());
    }

    @Test
    public void testPieceManagement(){
        Piece aPiece = new Piece();
        lightSquare.setPiece(aPiece);
        assertTrue(darkSquare.isEmpty());
        assertFalse(lightSquare.isEmpty());
        assertEquals(aPiece, lightSquare.getPiece());
        lightSquare.removePiece();
        assertTrue(lightSquare.isEmpty());
        assertNull(lightSquare.getPiece());
    }
}
