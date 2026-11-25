# The Observer Pattern - One-Page Summary

## What it is
The Observer pattern sets up a one-to-many relationship: one object (the **Subject**) keeps a list of other objects (the **Observers**) and tells all of them whenever its state changes. The Subject does not know what the observers do; it only knows they have an update method. This is exactly how event-driven programming works - a button is a subject and its listeners are observers.

## The four roles
| Role | Job | Week 2 example |
|------|-----|----------------|
| Subject (interface) | Declares register / remove / notify | `Subject`, `DataSubject` |
| Concrete Subject | Holds the state and the observer list; calls notify when state changes | `WeatherData`, `DataModel` |
| Observer (interface) | Declares the update method | `Observer`, `DataObserver` |
| Concrete Observer | Reacts to the update | `CurrentConditionsDisplay`, `TextLogger` |

## How it works
1. Observers register with the subject (`registerObserver(o)`).
2. The subject's state changes (`setMeasurements(...)`, `setValue(...)`).
3. The subject loops through its list and calls `update(...)` on every observer.
4. An observer that is no longer needed is removed (`removeObserver(o)`), which also prevents memory leaks.

## Push vs. pull
- **Push:** the data travels in the update call (`update(temp, humidity, pressure)`). Simple and efficient.
- **Pull:** the observer is only told something changed and must call getters on the subject (the legacy `java.util.Observer` way).

## Why it matters
- **Loose coupling:** in Activity 3, `TextLogger` was added without touching `DataModel`. The tightly coupled version needed `DataModel` edited for every new display.
- **Easy to test:** the subject can be tested without any real display classes.
- **Flexible at runtime:** observers can be added or removed while the program runs (`ForecastDisplay` was removed in Activity 1).

## Four mechanisms in Java
1. **Custom Observer** - my own interfaces; full control; recommended for plain Java.
2. **java.util.Observer / Observable** - deprecated since Java 9 (forced inheritance, `setChanged()` trap, pull only, unspecified order, not thread-safe).
3. **PropertyChangeListener** - `java.beans`; `PropertyChangeSupport` manages listeners and each event carries the old and new value; used in JavaBeans/Swing.
4. **JavaFX Properties & Bindings** - the modern, reactive choice for JavaFX applications.
