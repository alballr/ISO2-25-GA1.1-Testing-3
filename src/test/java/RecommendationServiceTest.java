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
        
        // Case 1: temp=0.1, humidity=0.1, rainOrSnow=FALSE
        Weather w1 = new Weather(0.1, 0.1, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w1, venue));
        
        // Case 2: temp=-5, humidity=10, rainOrSnow=TRUE -> STAY-HOME
        Weather w2 = new Weather(-5.0, 10.0, true, false, false);
        assertEquals("STAY-HOME", service.recommendActivity(client, w2, venue));
        
        // Case 3: temp=-5, humidity=15.1, rainOrSnow=FALSE
        Weather w3 = new Weather(-5.0, 15.1, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w3, venue));
        
        // Case 4: temp=18, humidity=58, rainOrSnow=FALSE
        Weather w4 = new Weather(18.0, 58.0, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w4, venue));
        
        // Case 5: temp=18, humidity=10, rainOrSnow=TRUE
        Weather w5 = new Weather(18.0, 10.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w5, venue));
        
        // Case 6: temp=-0.1, humidity=0, rainOrSnow=FALSE
        Weather w6 = new Weather(-0.1, 0.0, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w6, venue));
        
        // Case 7: temp=0, humidity=15, rainOrSnow=FALSE
        Weather w7 = new Weather(0.0, 15.0, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w7, venue));
        
        // Case 8: temp=-5, humidity=14.99, rainOrSnow=TRUE -> STAY-HOME
        Weather w8 = new Weather(-5.0, 14.99, true, false, false);
        assertEquals("STAY-HOME", service.recommendActivity(client, w8, venue));
        
        // Case 9: temp=-5, humidity=0.1, rainOrSnow=TRUE -> STAY-HOME
        Weather w9 = new Weather(-5.0, 0.1, true, false, false);
        assertEquals("STAY-HOME", service.recommendActivity(client, w9, venue));
        
        // Case 10: temp=-5, humidity=58, rainOrSnow=TRUE
        Weather w10 = new Weather(-5.0, 58.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w10, venue));
        
        // Case 11: temp=0, humidity=0, rainOrSnow=TRUE
        Weather w11 = new Weather(0.0, 0.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w11, venue));
        
        // Case 12: temp=0, humidity=0.1, rainOrSnow=TRUE
        Weather w12 = new Weather(0.0, 0.1, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w12, venue));
        
        // Case 13: temp=18, humidity=14.99, rainOrSnow=FALSE
        Weather w13 = new Weather(18.0, 14.99, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w13, venue));
        
        // Case 14: temp=-5, humidity=15, rainOrSnow=TRUE
        Weather w14 = new Weather(-5.0, 15.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w14, venue));
        
        // Case 15: temp=-0.1, humidity=10, rainOrSnow=FALSE
        Weather w15 = new Weather(-0.1, 10.0, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w15, venue));
        
        // Case 16: temp=-0.1, humidity=0.1, rainOrSnow=TRUE -> STAY-HOME
        Weather w16 = new Weather(-0.1, 0.1, true, false, false);
        assertEquals("STAY-HOME", service.recommendActivity(client, w16, venue));
        
        // Case 17: temp=0.1, humidity=0, rainOrSnow=TRUE
        Weather w17 = new Weather(0.1, 0.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w17, venue));
        
        // Case 18: temp=-0.1, humidity=14.99, rainOrSnow=FALSE
        Weather w18 = new Weather(-0.1, 14.99, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w18, venue));
        
        // Case 19: temp=-5, humidity=0, rainOrSnow=TRUE -> STAY-HOME
        Weather w19 = new Weather(-5.0, 0.0, true, false, false);
        assertEquals("STAY-HOME", service.recommendActivity(client, w19, venue));
        
        // Case 20: temp=18, humidity=0.1, rainOrSnow=TRUE
        Weather w20 = new Weather(18.0, 0.1, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w20, venue));
        
        // Case 21: temp=18, humidity=15, rainOrSnow=TRUE
        Weather w21 = new Weather(18.0, 15.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w21, venue));
        
        // Case 22: temp=-0.1, humidity=15, rainOrSnow=FALSE
        Weather w22 = new Weather(-0.1, 15.0, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w22, venue));
        
        // Case 23: temp=0.1, humidity=15, rainOrSnow=FALSE
        Weather w23 = new Weather(0.1, 15.0, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w23, venue));
        
        // Case 24: temp=0.1, humidity=58, rainOrSnow=TRUE
        Weather w24 = new Weather(0.1, 58.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w24, venue));
        
        // Case 25: temp=0, humidity=15.1, rainOrSnow=TRUE
        Weather w25 = new Weather(0.0, 15.1, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w25, venue));
        
        // Case 26: temp=0, humidity=10, rainOrSnow=FALSE
        Weather w26 = new Weather(0.0, 10.0, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w26, venue));
        
        // Case 27: temp=18, humidity=0, rainOrSnow=FALSE
        Weather w27 = new Weather(18.0, 0.0, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w27, venue));
        
        // Case 28: temp=0.1, humidity=15.1, rainOrSnow=TRUE
        Weather w28 = new Weather(0.1, 15.1, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w28, venue));
        
        // Case 29: temp=0, humidity=58, rainOrSnow=TRUE
        Weather w29 = new Weather(0.0, 58.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w29, venue));
        
        // Case 30: temp=0.1, humidity=14.99, rainOrSnow=FALSE
        Weather w30 = new Weather(0.1, 14.99, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w30, venue));
        
        // Case 31: temp=-0.1, humidity=58, rainOrSnow=TRUE
        Weather w31 = new Weather(-0.1, 58.0, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w31, venue));
        
        // Case 32: temp=0.1, humidity=10, rainOrSnow=FALSE
        Weather w32 = new Weather(0.1, 10.0, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w32, venue));
        
        // Case 34: temp=0, humidity=14.99, rainOrSnow=TRUE
        Weather w34 = new Weather(0.0, 14.99, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w34, venue));
        
        // Case 35: temp=-0.1, humidity=15.1, rainOrSnow=TRUE
        Weather w35 = new Weather(-0.1, 15.1, true, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w35, venue));
        
        // Case 36: temp=18, humidity=15.1, rainOrSnow=FALSE
        Weather w36 = new Weather(18.0, 15.1, false, false, false);
        assertNotEquals("STAY-HOME", service.recommendActivity(client, w36, venue));
    }

    @Test
    public void testIsSkiing() {
        Client client = new Client(true, false);
        Venue venueOk = new Venue(100, 50);
        Venue venueOver = new Venue(50, 100);
        
        // Additional case: All conditions TRUE -> Skiing (temp<0, humidity<15, !rainOrSnow, venueWithinCapacity)
        Weather wSkiing = new Weather(-5.0, 10.0, false, false, false);
        assertEquals("Skiing", service.recommendActivity(client, wSkiing, venueOk));
        
        // Case 1: temp=0.0, humidity=16.0, rainOrSnow=TRUE, venueWithinCapacity=TRUE
        Weather w1 = new Weather(0.0, 16.0, true, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w1, venueOk));
        
        // Case 2: temp=-7.0, humidity=16.0, rainOrSnow=FALSE, venueWithinCapacity=FALSE
        Weather w2 = new Weather(-7.0, 16.0, false, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w2, venueOver));
        
        // Case 3: temp=-7.0, humidity=-12.0, rainOrSnow=TRUE, venueWithinCapacity=TRUE
        Weather w3 = new Weather(-7.0, -12.0, true, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w3, venueOk));
        
        // Case 4: temp=18.0, humidity=-12.0, rainOrSnow=FALSE, venueWithinCapacity=FALSE
        Weather w4 = new Weather(18.0, -12.0, false, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w4, venueOver));
        
        // Case 5: temp=0.0, humidity=61.0, rainOrSnow=FALSE, venueWithinCapacity=FALSE
        Weather w5 = new Weather(0.0, 61.0, false, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w5, venueOver));
        
        // Case 6: temp=-7.0, humidity=60.0, rainOrSnow=FALSE, venueWithinCapacity=TRUE
        Weather w6 = new Weather(-7.0, 60.0, false, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w6, venueOk));
        
        // Case 7: temp=18.0, humidity=60.0, rainOrSnow=TRUE, venueWithinCapacity=FALSE
        Weather w7 = new Weather(18.0, 60.0, true, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w7, venueOver));
        
        // Case 8: temp=18.0, humidity=16.0, rainOrSnow=TRUE, venueWithinCapacity=TRUE
        Weather w8 = new Weather(18.0, 16.0, true, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w8, venueOk));
        
        // Case 9: temp=32.48, humidity=60.0, rainOrSnow=TRUE, venueWithinCapacity=FALSE
        Weather w9 = new Weather(32.48, 60.0, true, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w9, venueOver));
        
        // Case 10: temp=32.48, humidity=-12.0, rainOrSnow=FALSE, venueWithinCapacity=TRUE
        Weather w10 = new Weather(32.48, -12.0, false, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w10, venueOk));
        
        // Case 11: temp=0.0, humidity=60.0, rainOrSnow=FALSE, venueWithinCapacity=FALSE
        Weather w11 = new Weather(0.0, 60.0, false, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w11, venueOver));
        
        // Case 12: temp=18.0, humidity=61.0, rainOrSnow=TRUE, venueWithinCapacity=TRUE
        Weather w12 = new Weather(18.0, 61.0, true, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w12, venueOk));
        
        // Case 13: temp=-7.0, humidity=61.0, rainOrSnow=TRUE, venueWithinCapacity=FALSE
        Weather w13 = new Weather(-7.0, 61.0, true, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w13, venueOver));
        
        // Case 14: temp=32.48, humidity=16.0, rainOrSnow=TRUE, venueWithinCapacity=TRUE
        Weather w14 = new Weather(32.48, 16.0, true, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w14, venueOk));
        
        // Case 15: temp=0.0, humidity=-12.0, rainOrSnow=TRUE, venueWithinCapacity=TRUE
        Weather w15 = new Weather(0.0, -12.0, true, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w15, venueOk));
        
        // Case 16: temp=32.48, humidity=61.0, rainOrSnow=FALSE, venueWithinCapacity=FALSE
        Weather w16 = new Weather(32.48, 61.0, false, false, false);
        assertNotEquals("Skiing", service.recommendActivity(client, w16, venueOver));
    }

    
    @Test
    public void testIsHikingOrClimbing() {
        Client client = new Client(true, false);
        Venue venueOk = new Venue(100, 50);
        Venue venueOver = new Venue(50, 100);
        
        // Case 1: temp=2.0, isRaining=TRUE, venueWithinCapacity=FALSE
        Weather w1 = new Weather(2.0, 50.0, true, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w1, venueOver));
        
        // Case 2: temp=15.0, isRaining=FALSE, venueWithinCapacity=FALSE
        Weather w2 = new Weather(15.0, 50.0, false, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w2, venueOver));
        
        // Case 3: temp=32.0, isRaining=FALSE, venueWithinCapacity=TRUE
        Weather w3 = new Weather(32.0, 50.0, false, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w3, venueOk));
        
        // Case 4: temp=0.1, isRaining=TRUE, venueWithinCapacity=TRUE
        Weather w4 = new Weather(0.1, 50.0, true, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w4, venueOk));
        
        // Case 5: temp=32.0, isRaining=TRUE, venueWithinCapacity=FALSE
        Weather w5 = new Weather(32.0, 50.0, true, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w5, venueOver));
        
        // Case 6: temp=0.0, isRaining=FALSE, venueWithinCapacity=FALSE
        Weather w6 = new Weather(0.0, 50.0, false, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w6, venueOver));
        
        // Case 7: temp=2.0, isRaining=FALSE, venueWithinCapacity=TRUE -> Hiking
        Weather w7 = new Weather(2.0, 50.0, false, false, false);
        assertEquals("Hiking Or Climbing", service.recommendActivity(client, w7, venueOk));
        
        // Case 8: temp=0.0, isRaining=TRUE, venueWithinCapacity=TRUE
        Weather w8 = new Weather(0.0, 50.0, true, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w8, venueOk));
        
        // Case 9: temp=15.0, isRaining=TRUE, venueWithinCapacity=TRUE
        Weather w9 = new Weather(15.0, 50.0, true, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w9, venueOk));
        
        // Case 10: temp=0.1, isRaining=FALSE, venueWithinCapacity=FALSE
        Weather w10 = new Weather(0.1, 50.0, false, false, false);
        assertNotEquals("Hiking Or Climbing", service.recommendActivity(client, w10, venueOver));
    }


    @Test
    public void testIsSummerAutumnSpringActivities() {
        Client client = new Client(true, false);
        Venue venue = new Venue(100, 50);
        
        // Additional case: All conditions TRUE -> Spring (temp 15-25, humidity<=60, !raining, !cloudy)
        // Using temp=20 to avoid overlap with Cultural (25-35) and Beach (>30)
        Weather wSpring = new Weather(20.0, 50.0, false, false, false);
        assertEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, wSpring, venue));
        
        // Case 1: temp=25.0, humidity=58.0, isRaining=TRUE, isCloudy=TRUE
        Weather w1 = new Weather(25.0, 58.0, true, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w1, venue));
        
        // Case 2: temp=-33.0, humidity=58.0, isRaining=FALSE, isCloudy=FALSE
        Weather w2 = new Weather(-33.0, 58.0, false, false, false);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w2, venue));
        
        // Case 3: temp=-33.0, humidity=61.0, isRaining=TRUE, isCloudy=TRUE
        Weather w3 = new Weather(-33.0, 61.0, true, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w3, venue));
        
        // Case 4: temp=32.0, humidity=58.0, isRaining=FALSE, isCloudy=TRUE
        Weather w4 = new Weather(32.0, 58.0, false, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w4, venue));
        
        // Case 5: temp=32.0, humidity=90.0, isRaining=TRUE, isCloudy=FALSE
        Weather w5 = new Weather(32.0, 90.0, true, false, false);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w5, venue));
        
        // Case 6: temp=25.0, humidity=10.0, isRaining=FALSE, isCloudy=FALSE
        // Note: temp=25 also matches Cultural (25-35), which is evaluated later
        Weather w6 = new Weather(25.0, 10.0, false, false, false);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w6, venue));
        
        // Case 7: temp=2.0, humidity=61.0, isRaining=FALSE, isCloudy=FALSE
        Weather w7 = new Weather(2.0, 61.0, false, false, false);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w7, venue));
        
        // Case 8: temp=14.0, humidity=90.0, isRaining=FALSE, isCloudy=TRUE
        Weather w8 = new Weather(14.0, 90.0, false, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w8, venue));
        
        // Case 9: temp=14.0, humidity=61.0, isRaining=TRUE, isCloudy=FALSE
        Weather w9 = new Weather(14.0, 61.0, true, false, false);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w9, venue));
        
        // Case 10: temp=32.0, humidity=10.0, isRaining=TRUE, isCloudy=TRUE
        Weather w10 = new Weather(32.0, 10.0, true, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w10, venue));
        
        // Case 11: temp=25.0, humidity=90.0, isRaining=FALSE, isCloudy=TRUE
        Weather w11 = new Weather(25.0, 90.0, false, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w11, venue));
        
        // Case 12: temp=32.0, humidity=61.0, isRaining=TRUE, isCloudy=TRUE
        Weather w12 = new Weather(32.0, 61.0, true, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w12, venue));
        
        // Case 13: temp=25.0, humidity=61.0, isRaining=FALSE, isCloudy=TRUE
        Weather w13 = new Weather(25.0, 61.0, false, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w13, venue));
        
        // Case 14: temp=-33.0, humidity=90.0, isRaining=TRUE, isCloudy=TRUE
        Weather w14 = new Weather(-33.0, 90.0, true, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w14, venue));
        
        // Case 15: temp=14.0, humidity=10.0, isRaining=FALSE, isCloudy=FALSE
        Weather w15 = new Weather(14.0, 10.0, false, false, false);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w15, venue));
        
        // Case 16: temp=-33.0, humidity=10.0, isRaining=FALSE, isCloudy=TRUE
        Weather w16 = new Weather(-33.0, 10.0, false, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w16, venue));
        
        // Case 17: temp=2.0, humidity=10.0, isRaining=TRUE, isCloudy=TRUE
        Weather w17 = new Weather(2.0, 10.0, true, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w17, venue));
        
        // Case 18: temp=14.0, humidity=58.0, isRaining=FALSE, isCloudy=TRUE
        Weather w18 = new Weather(14.0, 58.0, false, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w18, venue));
        
        // Case 19: temp=2.0, humidity=58.0, isRaining=FALSE, isCloudy=FALSE
        Weather w19 = new Weather(2.0, 58.0, false, false, false);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w19, venue));
        
        // Case 20: temp=2.0, humidity=90.0, isRaining=FALSE, isCloudy=TRUE
        Weather w20 = new Weather(2.0, 90.0, false, false, true);
        assertNotEquals("Spring, Summer and Autumn Activities", service.recommendActivity(client, w20, venue));
    }

   
    
    @Test
    public void testIsCulturalGastronomicActivities() {
        Client client = new Client(true, false);
        Venue venueOk = new Venue(100, 50);
        Venue venueOver = new Venue(50, 100);
        
        // Case 1: temp=26.0, isRaining=TRUE, venueWithinCapacity=FALSE
        Weather w1 = new Weather(26.0, 50.0, true, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w1, venueOver));
        
        // Case 2: temp=25.0, isRaining=TRUE, venueWithinCapacity=TRUE
        Weather w2 = new Weather(25.0, 50.0, true, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w2, venueOk));
        
        // Case 3: temp=18.0, isRaining=TRUE, venueWithinCapacity=TRUE
        Weather w3 = new Weather(18.0, 50.0, true, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w3, venueOk));
        
        // Case 4: temp=25.0, isRaining=FALSE, venueWithinCapacity=FALSE
        Weather w4 = new Weather(25.0, 50.0, false, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w4, venueOver));
        
        // Case 5: temp=32.0, isRaining=FALSE, venueWithinCapacity=TRUE
        // Note: temp=32 also matches Beach (>30), which is evaluated later
        Weather w5 = new Weather(32.0, 50.0, false, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w5, venueOk));
        
        // Case 6: temp=2.0, isRaining=FALSE, venueWithinCapacity=TRUE
        Weather w6 = new Weather(2.0, 50.0, false, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w6, venueOk));
        
        // Case 7: temp=26.0, isRaining=FALSE, venueWithinCapacity=TRUE -> Cultural
        Weather w7 = new Weather(26.0, 50.0, false, false, false);
        assertEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w7, venueOk));
        
        // Case 8: temp=2.0, isRaining=TRUE, venueWithinCapacity=FALSE
        Weather w8 = new Weather(2.0, 50.0, true, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w8, venueOver));
        
        // Case 9: temp=32.0, isRaining=TRUE, venueWithinCapacity=FALSE
        Weather w9 = new Weather(32.0, 50.0, true, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w9, venueOver));
        
        // Case 10: temp=-50.0, isRaining=FALSE, venueWithinCapacity=FALSE
        Weather w10 = new Weather(-50.0, 50.0, false, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w10, venueOver));
        
        // Case 11: temp=18.0, isRaining=FALSE, venueWithinCapacity=FALSE
        Weather w11 = new Weather(18.0, 50.0, false, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w11, venueOver));
        
        // Case 12: temp=-50.0, isRaining=TRUE, venueWithinCapacity=TRUE
        Weather w12 = new Weather(-50.0, 50.0, true, false, false);
        assertNotEquals("Cultural or Gastronomic activities", service.recommendActivity(client, w12, venueOk));
    }

   

    @Test
    public void testIsBeachOrPoolActivities() {
        Client client = new Client(true, false);
        Venue venueOk = new Venue(200, 150);
        Venue venueOver = new Venue(100, 200);
        
        // Case 1: temp=-0.7, isRaining=TRUE, venueWithinCapacity=FALSE
        Weather w1 = new Weather(-0.7, 50.0, true, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w1, venueOver));
        
        // Case 2: temp=28.0, isRaining=TRUE, venueWithinCapacity=TRUE
        Weather w2 = new Weather(28.0, 50.0, true, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w2, venueOk));
        
        // Case 3: temp=35.0, isRaining=TRUE, venueWithinCapacity=TRUE
        Weather w3 = new Weather(35.0, 50.0, true, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w3, venueOk));
        
        // Case 4: temp=28.0, isRaining=FALSE, venueWithinCapacity=FALSE
        Weather w4 = new Weather(28.0, 50.0, false, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w4, venueOver));
        
        // Case 5: temp=25.0, isRaining=FALSE, venueWithinCapacity=TRUE
        Weather w5 = new Weather(25.0, 50.0, false, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w5, venueOk));
        
        // Case 6: temp=18.0, isRaining=FALSE, venueWithinCapacity=TRUE
        Weather w6 = new Weather(18.0, 50.0, false, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w6, venueOk));
        
        // Case 7: temp=-0.7, isRaining=FALSE, venueWithinCapacity=TRUE
        Weather w7 = new Weather(-0.7, 50.0, false, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w7, venueOk));
        
        // Case 8: temp=18.0, isRaining=TRUE, venueWithinCapacity=FALSE
        Weather w8 = new Weather(18.0, 50.0, true, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w8, venueOver));
        
        // Case 9: temp=25.0, isRaining=TRUE, venueWithinCapacity=FALSE
        Weather w9 = new Weather(25.0, 50.0, true, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w9, venueOver));
        
        // Case 10: temp=-6.24, isRaining=FALSE, venueWithinCapacity=FALSE
        Weather w10 = new Weather(-6.24, 50.0, false, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w10, venueOver));
        
        // Case 11: temp=35.0, isRaining=FALSE, venueWithinCapacity=FALSE
        Weather w11 = new Weather(35.0, 50.0, false, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w11, venueOver));
        
        // Case 12: temp=-6.24, isRaining=TRUE, venueWithinCapacity=TRUE
        Weather w12 = new Weather(-6.24, 50.0, true, false, false);
        assertNotEquals("Beach or Pool activites", service.recommendActivity(client, w12, venueOk));
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
