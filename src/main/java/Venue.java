
public class Venue {
	
	private int maxCapacity;
	private int expectedAttendence;
	
	public Venue(int maxC, int e) {
		if (maxC < 0 || expectedAttendence < 0) throw new IllegalArgumentException("Numbers cannot be negative");
		maxCapacity = maxC;
		expectedAttendence =  e;
		
	}
	
	
	
	public boolean withinCapacity() {
        return expectedAttendence <= maxCapacity;
    }
}
