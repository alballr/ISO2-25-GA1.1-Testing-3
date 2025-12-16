import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


public class MainTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputCapture;

    @Before
    public void setUp() {
        outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));
    }

    @After
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void setInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private String getOutput() {
        return outputCapture.toString();
    }

    @Test
    public void testMainWithSpringActivities() {
        // healthy=true, symptoms=false, temp=20.0, humidity=50.0, 
        // raining=false, snowing=false, cloudy=false, capacity=100, visitors=50
        String input = "true\nfalse\n20.0\n50.0\nfalse\nfalse\nfalse\n100\n50\n";
        setInput(input);
        
        Main.main(new String[]{});
        
        String output = getOutput();
        assertTrue(output.contains("Spring, Summer and Autumn Activities"));
    }

    @Test
    public void testMainWithNoActivity() {
        // healthy=false, symptoms=true -> NOT ALLOWED
        // With temp=20, humidity=50, no rain/snow/cloud -> Spring activities
        String input = "false\ntrue\n20.0\n50.0\nfalse\nfalse\nfalse\n100\n50\n";
        setInput(input);
        
        Main.main(new String[]{});
        
        String output = getOutput();
        // Verifica que se muestra una recomendación (el código ejecuta aunque client no allowed)
        assertTrue(output.contains("Recommendation:"));
    }

    @Test
    public void testMainWithStayHome() {
        // temp=-5.0, humidity=10.0, raining=true -> STAY-HOME
        String input = "true\nfalse\n-5.0\n10.0\ntrue\nfalse\nfalse\n100\n50\n";
        setInput(input);
        
        Main.main(new String[]{});
        
        String output = getOutput();
        assertTrue(output.contains("STAY-HOME"));
    }

    @Test
    public void testMainWithSkiing() {
        // temp=-5.0, humidity=10.0, raining=false, snowing=false -> Skiing
        String input = "true\nfalse\n-5.0\n10.0\nfalse\nfalse\nfalse\n100\n50\n";
        setInput(input);
        
        Main.main(new String[]{});
        
        String output = getOutput();
        assertTrue(output.contains("Skiing"));
    }

    @Test
    public void testMainWithHiking() {
        // temp=10.0, humidity=50.0, raining=false -> Hiking
        String input = "true\nfalse\n10.0\n50.0\nfalse\nfalse\nfalse\n100\n50\n";
        setInput(input);
        
        Main.main(new String[]{});
        
        String output = getOutput();
        assertTrue(output.contains("Hiking Or Climbing"));
    }

    @Test
    public void testMainWithCultural() {
        // temp=27.0, humidity=70.0, raining=false, cloudy=true -> Cultural
        String input = "true\nfalse\n27.0\n70.0\nfalse\nfalse\ntrue\n100\n50\n";
        setInput(input);
        
        Main.main(new String[]{});
        
        String output = getOutput();
        assertTrue(output.contains("Cultural or Gastronomic activities"));
    }

    @Test
    public void testMainWithBeach() {
        // temp=35.0, humidity=50.0, raining=false -> Beach
        String input = "true\nfalse\n35.0\n50.0\nfalse\nfalse\nfalse\n200\n150\n";
        setInput(input);
        
        Main.main(new String[]{});
        
        String output = getOutput();
        assertTrue(output.contains("Beach or Pool activites"));
    }

    @Test
    public void testMainWithSnowing() {
        // temp=-5.0, humidity=10.0, snowing=true -> STAY-HOME
        String input = "true\nfalse\n-5.0\n10.0\nfalse\ntrue\nfalse\n100\n50\n";
        setInput(input);
        
        Main.main(new String[]{});
        
        String output = getOutput();
        assertTrue(output.contains("STAY-HOME"));
    }

    @Test
    public void testMainPrintsWelcomeMessage() {
        String input = "true\nfalse\n20.0\n50.0\nfalse\nfalse\nfalse\n100\n50\n";
        setInput(input);
        
        Main.main(new String[]{});
        
        String output = getOutput();
        assertTrue(output.contains("Adventure Activity Recommendation System"));
    }

    @Test
    public void testMainPromptsForHealth() {
        String input = "true\nfalse\n20.0\n50.0\nfalse\nfalse\nfalse\n100\n50\n";
        setInput(input);
        
        Main.main(new String[]{});
        
        String output = getOutput();
        assertTrue(output.contains("Are you in good health?"));
    }
}
