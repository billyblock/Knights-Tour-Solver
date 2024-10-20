package boardgames;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class CoordinateTest {

    private Coordinate a1;
    private Coordinate c2;

    @BeforeEach
    public void setUp() {
        a1 = new Coordinate('a', 1);
        c2 = new Coordinate(2, 1);
    }

    @AfterEach
    public void tearDown() {
        a1 = null;
        c2 = null;
    }

    @Test
    public void testConstructor() {
        assertEquals(0, a1.fileIndex);
        assertEquals(0, a1.rankIndex);
        assertEquals('a', a1.getFile());
        assertEquals(1, a1.getRank());
        assertEquals(2, c2.fileIndex);
        assertEquals(1, c2.rankIndex);
        assertEquals('c', c2.getFile());
        assertEquals(2, c2.getRank());
    }

    @Test
    public void testEquals(){
        Coordinate a1Alternate = new Coordinate('a', 1);
        assertTrue(a1Alternate.equals(a1));
        assertFalse(c2.equals(a1));
        assertFalse(a1.equals(this));
    }
}
