import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherTest {

    @Test(expected = IllegalArgumentException.class)
    public void testWeatherConstructorInvalidHumidity() {
        new Weather(20.0, 101.0, false, false, false);
    }
}
