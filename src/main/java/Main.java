import es.uclm.esi.iso2.axx.command_Line_Interface.commandLine;

import es.uclm.esi.iso2.axx.command_Line_Interface.commandLineI;

public class Main {
	public static void main(String[] args) {
		commandLine cli = new commandLine();
        RecommendationService service = new RecommendationService();

        cli.printLine("=== Adventure Activity Recommendation System ===");

        cli.printLine("Are you in good health? (true/false)");
        boolean healthy = cli.readBoolean();

        cli.printLine("Have you had infectious symptoms in the last two weeks? (true/false)");
        boolean symptoms = cli.readBoolean();

        cli.printLine("Temperature (°C):");
        double temperature = cli.readDouble();

        cli.printLine("Humidity (%):");
        double humidity = cli.readPositiveDouble();

        cli.printLine("Is it raining? (true/false)");
        boolean raining = cli.readBoolean();

        cli.printLine("Is it snowing? (true/false)");
        boolean snowing = cli.readBoolean();

        cli.printLine("Is it cloudy? (true/false)");
        boolean cloudy = cli.readBoolean();

        cli.printLine("Venue capacity:");
        int capacity = cli.readPositiveInt();

        cli.printLine("Expected visitors:");
        int expectedVisitors = cli.readPositiveInt();

        
        Client client = new Client(healthy, symptoms);
        Weather weather = new Weather(temperature, humidity, raining, snowing, cloudy);
        Venue venue = new Venue(capacity, expectedVisitors);

        
        String recommendation = service.recommendActivity(client, weather, venue);

        cli.printLine("Recommendation: " + recommendation);
	}
}
