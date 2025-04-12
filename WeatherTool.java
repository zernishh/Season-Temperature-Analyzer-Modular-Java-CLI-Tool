import java.util.Scanner;

public class WeatherTool {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select scenario:");
		System.out.println("1. Finding the season of the year for a given country and month");
		System.out.println("2. Finding whether a given temperature reading is above or below the average temperature of a city");
		int choice = scanner.nextInt();
		switch (choice) {
			case 1:
				handleScenarioA();
				break;
			case 2:
				handleScenarioB();
				break;
			default:
				System.out.println("Invalid choice");
		}
	}
	public static void handleScenarioA() {
		Scanner scanner = new Scanner(System.in);
		String country;
		do {
			System.out.println("Enter country name:");
			System.out.println("Valid options: Australia, Noongar, UAE, Malaysia, Singapore");
			country = scanner.nextLine();
		}
	       	while (!country.equalsIgnoreCase("Australia") && !country.equalsIgnoreCase("Noongar") && !country.equalsIgnoreCase("UAE") && !country.equalsIgnoreCase("Malaysia") && !country.equalsIgnoreCase("Singapore"));
		
		System.out.println("Enter a month of the year:");
		String month = scanner.nextLine();
		if (SeasonModule.validateInput(country, month)) {
			String season = SeasonModule.findSeason(country, month);
			System.out.println("Season: " + season);
		}
	}

	public static void handleScenarioB() {
    Scanner scanner = new Scanner(System.in);
    String city;
    do {
        System.out.println("Enter city name:");
        System.out.println("Valid options: Perth, Dubai");
        city = scanner.nextLine();
    } while (!city.equalsIgnoreCase("Perth") && !city.equalsIgnoreCase("Dubai"));

    System.out.println("Enter temperature:");
    double temperature = scanner.nextDouble();

    // First, validate the temperature
    TemperatureModule.validateTemperature(temperature, city);

    // Then, compare the temperature
    String message = TemperatureModule.compareTemperature(temperature, city);

    // Print the message
    System.out.println(message);
}

        
	
}

class SeasonModule {
	
	private static final String[] AUSTRALIA_MONTHS = {"December", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November"};
	private static final String[] AUSTRALIA_SEASONS = {"Summer", "Summer", "Summer", "Autumn", "Autumn", "Autumn", "Winter", "Winter", "Winter", "Spring", "Spring", "Spring"};
    	private static final String[] NOONGAR_MONTHS = {"December", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November"};
    	private static final String[] NOONGAR_SEASONS = {"Birak", "Birak", "Bunuru", "Bunuru", "Djeran", "Djeran", "Makuru", "Makuru", "Dijiba", "Dijiba", "Kambarang", "Kambarang"};
	private static final String[] UAE_MONTHS = {"May", "June", "July", "August", "September", "October", "November", "December", "January", "February", "March", "April"};
    	private static final String[] UAE_SEASONS = {"Summer", "Summer", "Summer", "Summer", "Summer", "Winter", "Winter", "Winter", "Winter", "Winter", "Winter", "Winter"};
    	private static final String[] MALAYSIA_SINGAPORE_MONTHS = {"December", "January", "February", "March", "April", "May", "September", "October", "November"};
    	private static final String[] MALAYSIA_SINGAPORE_SEASONS = {"Northeast Monsoon", "Northeast Monsoon", "Northeast Monsoon", "Inter-monsoon", "Inter-monsoon", "Northeast Monsoon", "Northeast Monsoon", "Inter-monsoon", "Inter-monsoon"};
	
	public static boolean validateInput(String country, String month) {
		String[] months = null;
		switch (country.toLowerCase()) {
				case "australia":
				months = AUSTRALIA_MONTHS;
				break;
				case "noongar":
                		months = NOONGAR_MONTHS;
				break;
            			case "uae":
                		months = UAE_MONTHS;
                		break;
            			case "malaysia":
            			case "singapore":
                		months = MALAYSIA_SINGAPORE_MONTHS;
                		break;
				default:
                		System.out.println("Invalid input: Country not recognized.");
				return false;
	}
	for (String validMonth : months) {
		if (validMonth.equalsIgnoreCase(month)) {
			return true;
		}
	}
	
	System.out.println("Invalid input: Month not recognized for the selected country.");
	return false;
}


public static String findSeason(String country, String month) {
	String[] months = null;
	String[] seasons = null;
	switch (country.toLowerCase()) {
		case "australia":
			months = AUSTRALIA_MONTHS;
                	seasons = AUSTRALIA_SEASONS;
			break;
		case "noongar":
                	months = NOONGAR_MONTHS;
                	seasons = NOONGAR_SEASONS;
                	break;
            	case "uae":
                	months = UAE_MONTHS;
                	seasons = UAE_SEASONS;
                	break;
            	case "malaysia":
            	case "singapore":
                	months = MALAYSIA_SINGAPORE_MONTHS;
                	seasons = MALAYSIA_SINGAPORE_SEASONS;
                	break;
		default:
			return "Season not found.";
	}
	
	for (int i = 0; i < months.length; i++) {
		if (months[i].equalsIgnoreCase(month)) { 
			return seasons[i];
		}
	}
	return "Season not found.";
}
}

class TemperatureModule {

	// Method to get the daily mean temperature based on the city
	public static double getDailyMeanTemperature(String city) {
		switch (city.toLowerCase()) {
			case "perth":
				return 18.2;
			case "dubai":
                		return 26.9;
            		default:
                	// Handle invalid city
                	throw new IllegalArgumentException("Invalid city");
		}
	}

//-------------

public static void validateTemperature(double temperatureReading, String city) {
    double minTemperature, maxTemperature, dailyMeanTemperature;

    // Assign the daily mean temperature based on the city
    try {
        dailyMeanTemperature = getDailyMeanTemperature(city);
    } catch (IllegalArgumentException e) {
        // Handle invalid city
        System.out.println("Invalid city");
        return; // Exit the method if the city is invalid
    }

    // Assign minTemperature and maxTemperature based on the city
    if (city.equalsIgnoreCase("perth")) {
        minTemperature = 0.7;
        maxTemperature = 46.0;
    } else if (city.equalsIgnoreCase("dubai")) {
        minTemperature = 1.5;
        maxTemperature = 49.0;
    } else {
        // This block should not be reached if the city is valid,
        // but including it for completeness
        System.out.println("Invalid city");
        return; // Exit the method if the city is invalid
    }

    // Check if the temperature reading is within the valid range
    if (temperatureReading < minTemperature || temperatureReading > maxTemperature) {
        System.out.println("Invalid temperature reading. Valid range is " + minTemperature + "°C to " + maxTemperature + "°C.");
    }
}



//---------------

public static String compareTemperature(double temperatureReading, String city) {
    double dailyMeanTemperature = getDailyMeanTemperature(city);

    // Calculate the temperature difference
    double temperatureDifference = temperatureReading - dailyMeanTemperature;

    // Check if the temperature difference is more than 6 degrees
    if (Math.abs(temperatureDifference) > 6.0) {
        String aboveOrBelow = temperatureDifference > 0 ? "above" : "below";
        return "Temperature reading is " + aboveOrBelow + " the average by more than 6 degrees";
    } else {
        if (temperatureDifference > 0) {
            return "Temperature reading is above the average";
        } else if (temperatureDifference < 0) {
            return "Temperature reading is below the average";
        } else {
            return "Temperature reading is at the average";
        }
    }
}
}




















