# Week 2 - Activity 5: Final Self-Assessment

## Learning Outcome 1: Design and Implement the Observer Pattern

**1. Draw the UML diagram of the Observer pattern. Label Subject, Observer, ConcreteSubject, ConcreteObserver.**

```
 +----------------------------+          observers          +-------------------------+
 |     <<interface>>          | 1                         * |     <<interface>>       |
 |        Subject             |---------------------------->|        Observer         |
 +----------------------------+                             +-------------------------+
 | + registerObserver(o)      |                             | + update(temp, hum, p)  |
 | + removeObserver(o)        |                             +-------------------------+
 | + notifyObservers()        |                                          ^
 +----------------------------+                                          | implements
              ^                                                          |
              | implements                              +----------------+-----------------+
              |                                         |                                  |
 +----------------------------+          +----------------------------+  +----------------------------+
 |      ConcreteSubject       |          |     ConcreteObserver       |  |     ConcreteObserver       |
 |       (WeatherData)        |          | (CurrentConditionsDisplay) |  |    (StatisticsDisplay)     |
 +----------------------------+          +----------------------------+  +----------------------------+
 | - observers: List<Observer>|          | + update(...)              |  | + update(...)              |
 | - temperature, humidity,   |          +----------------------------+  +----------------------------+
 |   pressure                 |
 | + setMeasurements(...)     |   notifyObservers(): for each o in observers -> o.update(...)
 +----------------------------+
```

**2. Write the Java code (from memory) for a Subject interface.**

```java
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
```

**3. Why does the Subject need a list of observers?**
Because it has a one-to-many relationship: any number of observers can be interested in its state. The list remembers who to notify, so `notifyObservers()` can loop through it and call `update()` on each one.

## Learning Outcome 2: Evaluate Legacy java.util.Observer

**4. List three problems with java.util.Observable.**
1. It is a class, so you must extend it and cannot extend anything else.
2. If you forget `setChanged()`, observers are silently not notified.
3. Observers are notified in an unspecified order (also: pull model only, untyped `Object` argument, not thread-safe).

**5. Why was this API deprecated?**
Its design is limited and error-prone (forced inheritance, unspecified order, silent `setChanged()` failures, untyped pull model, no thread safety), so it was deprecated in Java 9 in favour of better alternatives.

**6. What is the "pull model" and why is it harder to use?**
In the pull model the subject only signals that something changed, and each observer must call the subject's getters to fetch the data. It is harder because the observer has to cast the `Observable` to the concrete class, make extra calls, and is coupled to the subject's API.

## Learning Outcome 3: Create a Custom Event Notification System

**7. What methods must a custom Subject interface provide?**
A method to add/register an observer, a method to remove an observer, and a method to notify all observers (e.g. `addObserver()`, `removeObserver()`, `notifyObservers()`).

**8. How does the Subject notify all observers?**
It loops over its list of observers and calls each observer's update method, passing the new data (e.g. `for (DataObserver o : observers) o.onDataChanged(value);`).

**9. How do you prevent a memory leak when observers are no longer needed?**
Call `removeObserver()` when an observer is no longer needed. Otherwise the subject's list keeps a reference to it and it can never be garbage collected.

## Learning Outcome 4: Compare Different Mechanisms

**10. Name four event notification mechanisms in Java.**
Custom Observer interfaces, `java.util.Observer`/`Observable`, `PropertyChangeListener` (java.beans), and JavaFX Properties/Bindings.

**11. Which one is best for JavaFX?**
JavaFX Properties and Bindings.

**12. Which one is deprecated?**
`java.util.Observer` / `java.util.Observable` (deprecated since Java 9).

## Score Yourself

Score: ___ / 12
