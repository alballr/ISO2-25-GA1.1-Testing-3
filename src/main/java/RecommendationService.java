

public class RecommendationService {
	
	public RecommendationService() {
		
	}
	public String recommendActivity(Client client, Weather weather, Venue venue) {
		
		String result = "";
		
		if (!client.isAllowedToParticipate())
			result = "NO-ACTIVITY";
		
		double temperature = weather.getTemperature();
        double humidity = weather.getHumidity();
        boolean rainOrSnow = weather.isRaining() || weather.isSnowing();
        boolean isVenueWithinCapacity = venue.withinCapacity();        
        
        if (isStayAtHome(temperature, humidity, rainOrSnow))result = "STAY-HOME";
        
        if (isSkiing(temperature, humidity, rainOrSnow, isVenueWithinCapacity)) result = "Skiing";
        
        if(isHikingOrClimbing(temperature, weather.isRaining(), isVenueWithinCapacity)) result = "Hiking Or Climbing";
        	
		
        if(isSummerAutumnSpringActivities(temperature, weather.isRaining(), weather.isCloudy(), humidity)) result = "Spring, Summer and Autumn Activities";
        
        
        if (isCulturalGastronomicActivities(temperature, weather.isRaining(), isVenueWithinCapacity)) result = "Cultural or Gastronomic activities";
        	
        	
        	
        if(isBeachOrPoolActivities(temperature, weather.isRaining(), isVenueWithinCapacity)) result = "Beach or Pool activites";
        	
        
        
        
        return result;
		
	}
	
	private boolean isStayAtHome(double temperature, double humidity, boolean rainOrSnow) {
		boolean result = false;
		if (temperature < 0 && humidity < 15 && rainOrSnow) {
			result = true;
		}
        	
		return result;
	}
	
	private boolean isSkiing(double temperature, double humidity, boolean rainOrSnow, boolean venueWithinCapapacity){
		boolean result = false;
		if (temperature < 0 && humidity < 15 && !rainOrSnow && venueWithinCapapacity) result = true;
        	
		return result;
	}
	
	private boolean isHikingOrClimbing(double temperature, boolean isRaining, boolean venueWithinCapapacity ) {
		boolean result = false;
		if(temperature >= 0 && temperature <= 15 && !isRaining && venueWithinCapapacity) result = true;
        	
		return result;
	}
	
	private boolean isSummerAutumnSpringActivities(double temperature, boolean isRaining, boolean isCloudy, double humidity ) {
		boolean result = false;
		if(temperature >= 15 && temperature <= 25 && !isRaining && !isCloudy && humidity <= 60) result = true;
        
		return result;
	}
	
	private boolean isCulturalGastronomicActivities(double temperature, boolean isRaining, boolean venueWithinCapapacity) {
		boolean result = false;
		if(temperature >= 25 && temperature <= 35 && !isRaining && venueWithinCapapacity) result = true;
		
		return result;
	}
	
	private boolean isBeachOrPoolActivities(double temperature, boolean isRaining, boolean venueWithinCapapacity) {
		boolean result = false;
		if(temperature > 30 && !isRaining &&  venueWithinCapapacity) result = true;
        	
        return result;
	}

}
