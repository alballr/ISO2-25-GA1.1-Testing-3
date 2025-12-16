import org.junit.Test;
import static org.junit.Assert.*;

public class RecommendationServiceTest {

    private RecommendationService service = new RecommendationService();

  
    @Test
    public void testRecommendActivityClientNotAllowed() {
        // Client not allowed with weather that doesn't match any activity -> NO-ACTIVITY
        Client c1 = new Client(false, true);
        Weather w1 = new Weather(40.0, 80.0, true, false, false); // No activity matches
        Venue v1 = new Venue(50, 100); // Over capacity
        assertEquals("NO-ACTIVITY", service.recommendActivity(c1, w1, v1));
        
        // Client with infectious disease with no matching activity
        Client c2 = new Client(true, true);
        Weather w2 = new Weather(45.0, 90.0, true, true, true); // No activity matches
        Venue v2 = new Venue(100, 200); // Over capacity
        assertEquals("NO-ACTIVITY", service.recommendActivity(c2, w2, v2));
    }

  
    @Test
    public void testIsStayAtHome() {
        Client client = new Client(true, false);
        Venue venue = new Venue(100, 50);
        
        // Case 1: All conditions TRUE -> STAY-HOME 
        Weather w1 = new Weather(-5.0, 10.0, true, false, false);
        assertEquals("STAY-HOME", service.recommendActivity(client, w1, venue));
        
        // Case 2: temp >= 0 -> NOT stay home 
        Weather w2 = new Weather(18.0, 10.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w2, venue));
        
        // Case 3: humidity >= 15 -> NOT stay home 
        Weather w3 = new Weather(-5.0, 58.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w3, venue));
        
        // Case 4: no rain/snow -> goes to Skiing 
        Weather w4 = new Weather(-5.0, 10.0, false, false, false);
        assertEquals("Skiing", service.recommendActivity(client, w4, venue));
        
        // Case 5: Snowing instead of raining -> STAY-HOME
        Weather w5 = new Weather(-5.0, 10.0, false, true, false);
        assertEquals("STAY-HOME", service.recommendActivity(client, w5, venue));
    }

    @Test
    public void testIsSkiing() {
        Client client = new Client(true, false);
        
        // Case 1: All conditions TRUE 
        Weather w1 = new Weather(-7.0, 10.0, false, false, false);
        Venue v1 = new Venue(100, 50);
        assertEquals("Skiing", service.recommendActivity(client, w1, v1));
        
        // Case 2: humidity >= 15 
        Weather w2 = new Weather(-7.0, 16.0, false, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w2, v1));
        
        // Case 3: rainOrSnow = true 
        Weather w3 = new Weather(-7.0, 10.0, true, false, false);
        assertEquals("STAY-HOME", service.recommendActivity(client, w3, v1));
        
        // Case 4: venue over capacity 
        Weather w4 = new Weather(-7.0, 10.0, false, false, false);
        Venue v2 = new Venue(50, 100);
        assertNotEquals("Skiing", service.recommendActivity(client, w4, v2));
    }

    
    @Test
    public void testIsHikingOrClimbing() {
        Client client = new Client(true, false);
        Venue venueOk = new Venue(100, 50);
        Venue venueOver = new Venue(50, 100);
        
        // Case 1: All conditions TRUE -> Hiking (MC/DC H1)
        Weather w1 = new Weather(0.1, 50.0, false, false, false);
        assertEquals("Hiking Or Climbing", service.recommendActivity(client, w1, venueOk));
        
        // Case 2: temp > 15 -> NOT hiking (MC/DC H2)
        Weather w2 = new Weather(18.0, 50.0, false, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w2, venueOk));
        
        // Case 3: raining = true -> NOT hiking (MC/DC H3)
        Weather w3 = new Weather(10.0, 50.0, true, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w3, venueOk));
        
        // Case 4: venue over capacity -> NOT hiking (MC/DC H4)
        Weather w4 = new Weather(10.0, 50.0, false, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w4, venueOver));
        
        // Case 5: temp = 0 (boundary) -> Hiking
        Weather w5 = new Weather(0.0, 50.0, false, false, false);
        assertEquals("Hiking Or Climbing", service.recommendActivity(client, w5, venueOk));
        
        // Case 6: temp = 15 (boundary) -> Hiking
        Weather w6 = new Weather(15.0, 70.0, false, false, true);
        assertEquals("Hiking Or Climbing", service.recommendActivity(client, w6, venueOk));
    }


    @Test
    public void testIsSummerAutumnSpringActivities() {
        Client client = new Client(true, false);
        Venue venue = new Venue(100, 50);
        
        // Case 1: All conditions TRUE -> Spring (MC/DC SA1)
        Weather w1 = new Weather(20.0, 58.0, false, false, false);
        assertEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w1, venue));
        
        // Case 2: temp > 25 -> NOT spring (MC/DC SA2)
        Weather w2 = new Weather(32.0, 58.0, false, false, false);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w2, venue));
        
        // Case 3: humidity > 60 -> NOT spring (MC/DC SA3)
        Weather w3 = new Weather(20.0, 70.0, false, false, false);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w3, venue));
        
        // Case 4: raining = true -> NOT spring (MC/DC SA4)
        Weather w4 = new Weather(20.0, 58.0, true, false, false);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w4, venue));
        
        // Case 5: cloudy = true -> NOT spring (MC/DC SA5)
        Weather w5 = new Weather(20.0, 58.0, false, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w5, venue));
    }

   
    
    @Test
    public void testIsCulturalGastronomicActivities() {
        Client client = new Client(true, false);
        Venue venueOk = new Venue(100, 50);
        Venue venueOver = new Venue(50, 100);
        
        // Case 1: All conditions TRUE -> Cultural (MC/DC CG1)
        Weather w1 = new Weather(27.0, 70.0, false, false, true);
        assertEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w1, venueOk));
        
        // Case 2: temp < 25 -> NOT cultural (MC/DC CG2)
        Weather w2 = new Weather(18.0, 70.0, false, false, true);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w2, venueOk));
        
        // Case 3: raining = true -> NOT cultural (MC/DC CG3)
        Weather w3 = new Weather(27.0, 70.0, true, false, true);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w3, venueOk));
        
        // Case 4: venue over capacity -> NOT cultural (MC/DC CG4)
        Weather w4 = new Weather(27.0, 70.0, false, false, true);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w4, venueOver));
    }

   

    @Test
    public void testIsBeachOrPoolActivities() {
        Client client = new Client(true, false);
        Venue venueOk = new Venue(200, 150);
        Venue venueOver = new Venue(100, 200);
        
        // Case 1: All conditions TRUE -> Beach (MC/DC BP1)
        Weather w1 = new Weather(35.0, 50.0, false, false, false);
        assertEquals("Beach or Pool activites", service.recommendActivity(client, w1, venueOk));
        
        // Case 2: temp <= 30 -> NOT beach (MC/DC BP2)
        Weather w2 = new Weather(28.0, 50.0, false, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w2, venueOk));
        
        // Case 3: raining = true -> NOT beach (MC/DC BP3)
        Weather w3 = new Weather(35.0, 50.0, true, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w3, venueOk));
        
        // Case 4: venue over capacity -> NOT beach (MC/DC BP4)
        Weather w4 = new Weather(35.0, 50.0, false, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w4, venueOver));
        
        // Case 5: temp = 30 (boundary) -> NOT beach
        Weather w5 = new Weather(30.0, 50.0, false, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w5, venueOk));
        
        // Case 6: temp = 30.1 (just over boundary) -> Beach
        Weather w6 = new Weather(30.1, 50.0, false, false, false);
        assertEquals("Beach or Pool activites", service.recommendActivity(client, w6, venueOk));
    }

    /**
     * Tests for edge cases where no activity matches
     */
    @Test
    public void testNoActivityMatch() {
        Client client = new Client(true, false);
        
        // Temperature too high, raining, venue over capacity -> empty result
        Weather w1 = new Weather(40.0, 80.0, true, false, false);
        Venue v1 = new Venue(50, 100);
        assertEquals("", service.recommendActivity(client, w1, v1));
        
        // All conditions false
        Weather w2 = new Weather(45.0, 90.0, true, true, true);
        Venue v2 = new Venue(100, 200);
        assertEquals("", service.recommendActivity(client, w2, v2));
    }

    /**
     * Tests for RecommendationService constructor
     */
    @Test
    public void testRecommendationServiceConstructor() {
        RecommendationService rs = new RecommendationService();
        assertNotNull(rs);
    }
}
