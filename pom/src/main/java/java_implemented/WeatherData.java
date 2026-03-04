package java_implemented;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class WeatherData extends Observable {

    // Declaring Variables
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    // Constructor
    public WeatherData() {
        observers = new ArrayList<>();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        // Save to DB
        WeatherLogger.log(temperature, humidity, pressure);

        setChanged();              // Required before notifyObservers!
        notifyObservers(observers);         // You can also pass data here if needed
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
