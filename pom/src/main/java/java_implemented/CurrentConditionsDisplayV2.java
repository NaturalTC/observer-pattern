package java_implemented;

import self_implemeted.DisplayElement;
import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplayV2 implements Observer, DisplayElement {

    // Declaring Variables
    float temperature;
    float humidity;
    Observable weather_station;

    public CurrentConditionsDisplayV2(WeatherData weatherData) {
        weather_station = weatherData;
        weather_station.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            // Get the updated values from the WeatherData object
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
        }
        display();
    }

    @Override
    public void display() {
        System.out.println("\nCurrent Conditions Display\n");
        System.out.println("-----------------------------");
        System.out.println("\nTemperature: " + temperature);
        System.out.println("Humidity: " + humidity);
    }


}
