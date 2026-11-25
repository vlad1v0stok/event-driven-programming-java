# Limitations of java.util.Observer and java.util.Observable

## Limitation 1: Single Inheritance
Explain: Why is it a problem that Observable is a CLASS, not an INTERFACE?

Your answer: Java allows a class to extend only one other class. Because `Observable` is a class, `LegacyWeatherData` has to use its one `extends` on `Observable`, so it can never extend another class it might need (for example a base model class or `JPanel`). An existing class that already extends something else cannot become observable at all. You also cannot provide your own implementation of the methods behind an interface, so you are stuck with `Observable`'s behaviour.

## Limitation 2: Cannot Change the Notification Order
Explain: In what order are observers notified? Can you control this?

Your answer: The order is unspecified. The documentation only says observers are notified in some order, and the current implementation actually walks its internal list from the last-added observer to the first. You cannot control or rely on the order, so if one observer depends on another having run first, the code may break.

## Limitation 3: setChanged() is Confusing
Explain: Why must you call setChanged() before notifyObservers()?

Your answer: `notifyObservers()` only notifies anyone if the internal "changed" flag is set, and `setChanged()` is a protected method that sets it. If you forget to call it, `notifyObservers()` silently does nothing - no exception, no warning - so observers simply never update. Because it is `protected`, you also cannot call it from outside the subclass, which forces even more inheritance.

## Limitation 4: Pull Model by Default
Explain: How does the observer get the data? Is this efficient?

Your answer: `notifyObservers()` with no argument only passes a reference to the `Observable`. The observer must cast it (`(LegacyWeatherData) o`) and call getters such as `getTemperature()` to pull the data. This is not efficient: it needs extra method calls and casts, couples the observer to the concrete subject class, and the data could have changed again by the time it is pulled. The optional `Object arg` is untyped, so passing data that way still needs casting.

## Limitation 5: Deprecated Since Java 9
Explain: Why did Java deprecate these classes?

Your answer: Because of the problems above: `Observable` is a class (forcing inheritance), observers are notified in an unspecified order, `setChanged()` is easy to forget and fails silently, the model is pull-based and untyped (`Object arg`), and the API is not thread-safe or extensible. The Java documentation recommends `java.beans` (`PropertyChangeListener`), `java.util.concurrent` classes, or the Flow API instead, and in practice a custom Observer interface or JavaFX properties are used.

---

## Step 4: Self-Check

**1. Why can't you extend another class if you already extend Observable?**
Java supports only single inheritance of classes. A class can have one superclass, and once that is `Observable` there is no room for another.

**2. What is the difference between the push model and the pull model?**
In the push model, the subject sends the changed data directly in the notification (e.g. `update(temperature, humidity, pressure)`). In the pull model, the subject only says "something changed" and the observer has to call getters on the subject to fetch the data it needs.

**3. Why did Java deprecate these classes?**
Because the design is limited and error-prone: it forces inheritance, gives no control over notification order, silently skips notification if `setChanged()` is forgotten, only supports an untyped pull model, and is not thread-safe.

## Terminal Output

```
Legacy Display -> Temp: 26.0°C, Humidity: 60.0%
Legacy Display -> Temp: 30.0°C, Humidity: 75.0%
```
