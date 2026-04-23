/**
 * Represents sensor data for a celestial body
 */
public class SensorData {
    private double temperature; // in Kelvin
    private double pressure; // in Pascals
    private double humidity; // percentage (0-100)
    private double radiation; // in Sieverts

    /**
     * Constructor for SensorData
     * @param temperature Temperature in Kelvin
     * @param pressure Pressure in Pascals
     * @param humidity Humidity percentage (0-100)
     * @param radiation Radiation in Sieverts
     * @throws IllegalArgumentException if humidity is not between 0-100
     */
    public SensorData(double temperature, double pressure, double humidity, double radiation) {
        if (humidity < 0 || humidity > 100) {
            throw new IllegalArgumentException("Humidity must be between 0 and 100");
        }
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.radiation = radiation;
    }

    // Getters
    public double getTemperature() { return temperature; }
    public double getPressure() { return pressure; }
    public double getHumidity() { return humidity; }
    public double getRadiation() { return radiation; }

    /**
     * Returns a formatted string of the sensor data
     * @return Formatted sensor data string
     */
    @Override
    public String toString() {
        return String.format("%.2f Kelvin, %.2f Pascals, %%%.2f Humidity, %.2f Sieverts", 
                            temperature, pressure, humidity, radiation);
    }
}
