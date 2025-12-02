
public class Client {
	
	private boolean perfectHealth;
	private boolean infectuousDeseaseLastTwoWeaks;
	
	
	public Client(boolean p, boolean i) {
		perfectHealth = p;
		infectuousDeseaseLastTwoWeaks = i;
	}
	
	
	
	public boolean isInPerfectHealth() {
		return perfectHealth;
	}
	
	public boolean hasInfectuousDeseaseLast2Weaks() {
		return infectuousDeseaseLastTwoWeaks;
	}
	
	public boolean isAllowedToParticipate() {
        return perfectHealth && !infectuousDeseaseLastTwoWeaks;
    }
	
	
	
}
