

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
        
        
        if (temperature < 0 && humidity < 15 && rainOrSnow)
        	result = "STAY-HOME";
        
        if (temperature < 0 && humidity < 15 && !rainOrSnow) {
        	if ( venue.withinCapacity()) {
        		result = "Skiing";
        	}
        	else {
        		result = "Venue capacity exeeded";
        	}
        }
        
        if(temperature >= 0 && temperature <= 15 && !weather.isRaining()) {
        	if ( venue.withinCapacity()) {
        		result = "Climbing or Hiking";
        	}
        	else {
        		result = "Venue capacity exeeded";
        	}
        }
		
        if(temperature >= 15 && temperature <= 25 && !weather.isRaining() && !weather.isCloudy() && humidity <= 60) {
        	result = "Spring, Summer and Autumn Activities";
        }
        
        if (temperature >= 25 && temperature <= 35 && !weather.isRaining()) {
        	if ( venue.withinCapacity()) {
        		result = "Cultural or Gastronomic activities";
        	}
        	else {
        		result = "Venue capacity exeeded";
        	}
        }
        	
        	
        if(temperature > 30 && !weather.isRaining()) {
        	if ( venue.withinCapacity()) {
        		result = "Beach or Pool activites";
        	}
        	else {
        		result = "Venue capacity exeeded";
        	}
        }
        
        result = "No valid recommendation for you";
        
        return result;
		
	}

}
