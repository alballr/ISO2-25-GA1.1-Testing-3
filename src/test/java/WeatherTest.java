import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherTest {

    @Test
    public void testWeatherConstructor() {
        // Case 1: humidity=102.0 -> IllegalArgumentException
        try {
            new Weather(20.0, 102.0, false, false, false);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
        
        // Case 2: humidity=99.0 -> Valid
        Weather w2 = new Weather(20.0, 99.0, false, false, false);
        assertEquals(99.0, w2.getHumidity(), 0.01);
        
        // Case 3: humidity=10.0 -> Valid
        Weather w3 = new Weather(20.0, 10.0, false, false, false);
        assertEquals(10.0, w3.getHumidity(), 0.01);
        
        // Case 4: humidity=101.0 -> IllegalArgumentException
        try {
            new Weather(20.0, 101.0, false, false, false);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
        
        // Case 5: humidity=-4.0 -> Valid (no lower bound check in code)
        Weather w5 = new Weather(20.0, -4.0, false, false, false);
        assertEquals(-4.0, w5.getHumidity(), 0.01);
    }
}
