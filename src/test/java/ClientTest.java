import org.junit.Test;
import static org.junit.Assert.*;


public class ClientTest {

 
    @Test
    public void testIsAllowedToParticipate() {
        // Case 1: perfectHealth=true, infectious=false -> true 
        Client c1 = new Client(true, false);
        assertTrue(c1.isAllowedToParticipate());
        
        // Case 2: perfectHealth=false, infectious=false -> false 
        Client c2 = new Client(false, false);
        assertFalse(c2.isAllowedToParticipate());
        
        // Case 3: perfectHealth=true, infectious=true -> false 
        Client c3 = new Client(true, true);
        assertFalse(c3.isAllowedToParticipate());
        
        // Case 4: perfectHealth=false, infectious=true -> false 
        Client c4 = new Client(false, true);
        assertFalse(c4.isAllowedToParticipate());
    }

   
    @Test
    public void testIsInPerfectHealth() {
        // perfectHealth = true
        Client c1 = new Client(true, false);
        assertTrue(c1.isInPerfectHealth());
        
        // perfectHealth = false
        Client c2 = new Client(false, false);
        assertFalse(c2.isInPerfectHealth());
    }

   
    @Test
    public void testHasInfectuousDeseaseLast2Weaks() {
        // infectious = true
        Client c1 = new Client(true, true);
        assertTrue(c1.hasInfectuousDeseaseLast2Weaks());
        
        // infectious = false
        Client c2 = new Client(true, false);
        assertFalse(c2.hasInfectuousDeseaseLast2Weaks());
    }
}
