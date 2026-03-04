package self_implemeted;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    // Declaring Variables
    float temperature;
    float humidity;
    Subject weather_station;

    public CurrentConditionsDisplay(Subject weatherData) {
        weather_station = weatherData;
        weather_station.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
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
