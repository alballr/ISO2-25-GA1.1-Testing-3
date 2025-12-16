import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherTest {

    @Test
    public void testWeatherConstructor() {
        // Valid humidity
        Weather w1 = new Weather(20.0, 50.0, false, false, false);
        assertEquals(50.0, w1.getHumidity(), 0.01);
        
        // Boundary: humidity = 100.0 (valid)
        Weather w2 = new Weather(20.0, 100.0, false, false, false);
        assertEquals(100.0, w2.getHumidity(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeatherConstructorInvalidHumidity() {
        new Weather(20.0, 101.0, false, false, false);
    }
}
