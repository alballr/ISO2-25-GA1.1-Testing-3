import org.junit.Test;
import static org.junit.Assert.*;


public class VenueTest {

    @Test
    public void testVenueConstructor() {
        // Case 1: maxC=14, e=435
        Venue v1 = new Venue(14, 435);
        assertNotNull(v1);
        
        // Case 2: maxC=0, e=0
        Venue v2 = new Venue(0, 0);
        assertNotNull(v2);
        
        // Case 3: maxC=23, e=5726
        Venue v3 = new Venue(23, 5726);
        assertNotNull(v3);
        
        // Case 4: maxC=0, e=435
        Venue v4 = new Venue(0, 435);
        assertNotNull(v4);
        
        // Case 5: maxC=23, e=435
        Venue v5 = new Venue(23, 435);
        assertNotNull(v5);
        
        // Case 6: maxC=23, e=1
        Venue v6 = new Venue(23, 1);
        assertNotNull(v6);
        
        // Case 7: maxC=14, e=1
        Venue v7 = new Venue(14, 1);
        assertNotNull(v7);
        
        // Case 8: maxC=23, e=60
        Venue v8 = new Venue(23, 60);
        assertNotNull(v8);
        
        // Case 9: maxC=2000, e=0
        Venue v9 = new Venue(2000, 0);
        assertNotNull(v9);
        
        // Case 10: maxC=0, e=60
        Venue v10 = new Venue(0, 60);
        assertNotNull(v10);
        
        // Case 11: maxC=14, e=60
        Venue v11 = new Venue(14, 60);
        assertNotNull(v11);
        
        // Case 12: maxC=14, e=5726
        Venue v12 = new Venue(14, 5726);
        assertNotNull(v12);
        
        // Case 13: maxC=0, e=5726
        Venue v13 = new Venue(0, 5726);
        assertNotNull(v13);
        
        // Case 14: maxC=2000, e=1
        Venue v14 = new Venue(2000, 1);
        assertNotNull(v14);
        
        // Case 15: maxC=14, e=0
        Venue v15 = new Venue(14, 0);
        assertNotNull(v15);
        
        // Case 16: maxC=2000, e=60
        Venue v16 = new Venue(2000, 60);
        assertNotNull(v16);
        
        // Case 17: maxC=2000, e=435
        Venue v17 = new Venue(2000, 435);
        assertNotNull(v17);
        
        // Case 18: maxC=2000, e=5726
        Venue v18 = new Venue(2000, 5726);
        assertNotNull(v18);
        
        // Case 19: maxC=0, e=1
        Venue v19 = new Venue(0, 1);
        assertNotNull(v19);
        
        // Case 20: maxC=23, e=0
        Venue v20 = new Venue(23, 0);
        assertNotNull(v20);
    }

    @Test
    public void testWithinCapacity() {
        // Case 3: expectedAttendance=2000, maximumCapacity=5726 -> true
        Venue v3 = new Venue(5726, 2000);
        assertTrue(v3.withinCapacity());
        
        // Case 4: expectedAttendance=1, maximumCapacity=60 -> true
        Venue v4 = new Venue(60, 1);
        assertTrue(v4.withinCapacity());
        
        // Case 8: expectedAttendance=2000, maximumCapacity=60 -> false
        Venue v8 = new Venue(60, 2000);
        assertFalse(v8.withinCapacity());
        
        // Case 9: expectedAttendance=1, maximumCapacity=5726 -> true
        Venue v9 = new Venue(5726, 1);
        assertTrue(v9.withinCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVenueConstructorNegativeMaxCapacity() {
        // Test with negative maxCapacity -> should throw IllegalArgumentException
        new Venue(-1, 100);
    }
}
