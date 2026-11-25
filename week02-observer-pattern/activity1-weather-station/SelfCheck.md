# Activity 1: Weather Station - Self-Check

**1. What is the role of the Subject interface?**
It defines the contract every publisher must follow: `registerObserver()`, `removeObserver()` and `notifyObservers()`. Code that uses a Subject only depends on these three methods, not on how a particular subject (like `WeatherData`) stores its observers or data.

**2. What happens if you call notifyObservers() without registering any observers?**
Nothing happens and there is no error. The `for` loop runs over an empty list, so no `update()` calls are made.

**3. What is the difference between registerObserver() and removeObserver()?**
`registerObserver()` adds an observer to the list so it starts receiving `update()` calls. `removeObserver()` takes it out of the list so it stops receiving them. In the demo, after `ForecastDisplay` was removed, the last measurement only reached the Current and Statistics displays.

**4. Why is the observers list stored as an instance variable?**
Because it has to survive between method calls: observers are added in one call, and the list is used later every time `setMeasurements()` triggers `notifyObservers()`. Each `WeatherData` object also needs its own list of observers.

## Terminal Output

```
[WeatherData] Observer registered: CurrentConditionsDisplay
[WeatherData] Observer registered: StatisticsDisplay
[WeatherData] Observer registered: ForecastDisplay

[WeatherData] New measurements received!
  [Current] Temp: 25.0°C, Humidity: 65.0%
  [Statistics] Max: 25.0°C, Min: 25.0°C
  [Forecast] More of the same.

[WeatherData] New measurements received!
  [Current] Temp: 28.0°C, Humidity: 70.0%
  [Statistics] Max: 28.0°C, Min: 25.0°C
  [Forecast] Cooler, rainy weather coming.

[WeatherData] New measurements received!
  [Current] Temp: 22.0°C, Humidity: 60.0%
  [Statistics] Max: 28.0°C, Min: 22.0°C
  [Forecast] Improving weather on the way!

--- Removing ForecastDisplay ---
[WeatherData] Observer removed: ForecastDisplay

[WeatherData] New measurements received!
  [Current] Temp: 20.0°C, Humidity: 55.0%
  [Statistics] Max: 28.0°C, Min: 20.0°C
```
