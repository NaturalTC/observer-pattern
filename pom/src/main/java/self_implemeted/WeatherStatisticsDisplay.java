package self_implemeted;

public class WeatherStatisticsDisplay implements Observer, DisplayElement {

    // Declare Variables
    double heatIndex;
    Subject weather_station;

    public WeatherStatisticsDisplay(Subject weatherData) {

        // Retrieves Weather Data from Concrete Subject
        weather_station = weatherData;
        weather_station.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("\n-----------------------------\n\nHeat Index: " + heatIndex + "\n\n-----------------------------");
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        heatIndex = -42.379 + 2.04901523 * temperature +
                10.14333127 * humidity -
                0.22475541 * temperature * humidity;

        display();
    }
}
