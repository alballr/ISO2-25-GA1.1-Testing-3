
public class Weather {
	
	private double temperature;
	private double humidity;
	private boolean raining;
	private boolean snowing;
	private boolean cloudy;
	
	public Weather(double temperature, double humidity, boolean raining, boolean snowing, boolean cloudy) {
        if (humidity < 0 || humidity > 100)
            throw new IllegalArgumentException("Humidity must be between 0 and 100");
        this.temperature = temperature;
        this.humidity = humidity;
        this.raining = raining;
        this.snowing = snowing;
        this.cloudy = cloudy;
    }
	
	public double getTemperature() {
		return temperature;
	}
	
	public double getHumidity() {
		return humidity;
	}
	
	public boolean isRaining() {
		return raining;
	}
	
	public boolean isSnowing() {
		return snowing;
	}
	
	public boolean isCloudy() {
		return cloudy;
	}

}
