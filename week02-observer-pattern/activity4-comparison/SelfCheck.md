# Activity 4: Compare Event Notification Mechanisms - Self-Check

**1. Which mechanism carries both the old and new value of a property?**
PropertyChangeListener. Each `PropertyChangeEvent` has `getOldValue()` and `getNewValue()` (the demo printed "name changed from Alice to Bob").

**2. Which mechanism requires a helper class to manage the listener list?**
PropertyChangeListener, via `PropertyChangeSupport`, which stores the listeners and fires the events.

**3. Which two mechanisms are recommended for modern Java?**
Custom Observer and JavaFX Bindings.

**4. Which mechanism is deprecated and should be avoided?**
`java.util.Observer` / `java.util.Observable`.

## Terminal Output

```
Listener A: name changed from null to Alice
Listener B: noticed name is now Alice
Listener A: name changed from Alice to Bob
Listener B: noticed name is now Bob
```
