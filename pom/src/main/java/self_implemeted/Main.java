package self_implemeted;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import java.util.Random;


public class Main {
    public static void main(String[] args) {

        // Declare and initialize my Subject
        WeatherData weather_station = new WeatherData();

        // Declare and initialize my Observer
        CurrentConditionsDisplay current_conditions = new CurrentConditionsDisplay(weather_station);
        WeatherStatisticsDisplay weather_stats = new WeatherStatisticsDisplay(weather_station);

        // Set weather conditions
        weather_station.setMeasurements(80, 70, 90);

        Random random = new Random();

        while (true) {
            // Generate random weather data
            float temp = 60 + random.nextFloat() * 40;       // 60°F to 100°F
            float humidity = 20 + random.nextFloat() * 80;   // 20% to 100%
            float pressure = 28 + random.nextFloat() * 2;    // 28 to 30 inHg

            weather_station.setMeasurements(temp, humidity, pressure);

            // Wait for 10 seconds before updating again
            try {
                Thread.sleep(10_000); // 10,000 milliseconds = 10 seconds
            } catch (InterruptedException e) {
                System.out.println("Update loop interrupted");
                break;
            }
        }
    }
}