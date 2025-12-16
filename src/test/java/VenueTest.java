import org.junit.Test;
import static org.junit.Assert.*;


public class VenueTest {

    @Test(expected = IllegalArgumentException.class)
    public void testVenueConstructorNegativeCapacity() {
        new Venue(-1, 1);
    }

 
    @Test
    public void testWithinCapacity() {
        //  TRUE: Within capacity
        Venue v1 = new Venue(60, 50);
        assertTrue(v1.withinCapacity());
        
        // FALSE: Over capacity
        Venue v2 = new Venue(60, 100);
        assertFalse(v2.withinCapacity());
        
        // Boundary: Exactly at capacity
        Venue v3 = new Venue(100, 100);
        assertTrue(v3.withinCapacity());
    }
}
