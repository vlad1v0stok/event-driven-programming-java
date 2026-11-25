# Week 1 Summary: Event-Driven Programming

## Key Definitions
- **Event:** something that happens that the program can respond to (a click, a key press, a timer tick).
- **Event Source:** the object that generates the event (`JButton`, `Timer`, a sensor).
- **Event Listener:** the object registered with the source that waits for the event (`ActionListener`, `MouseListener`).
- **Event Handler:** the code that runs in response (the body of `actionPerformed()` or of a lambda).
- **Event Queue:** a FIFO list of events waiting to be processed.
- **Event Loop:** runs continuously, takes events from the queue one at a time and dispatches them to handlers.
- **EDT (Event Dispatch Thread):** the single Swing thread that runs the event loop; all GUI work happens on it.
- **Procedural vs. event-driven:** procedural code follows a fixed order set by the program; event-driven code waits and reacts, so the user controls the flow.

## Important Diagrams

```
User Action → Event Source → Event Object → Event Queue → Event Loop → Handler → GUI Update
```

```
 Event Queue (FIFO)                         Event Loop (EDT)
 [Key 'A'] [Timer] [Button B] [Button A]  →  take next → dispatch to handler → repeat
```

## Code Examples I Found Helpful

Registering a handler with a lambda (from the event-driven calculator):
```java
btn.addActionListener(e -> {
    display.setText("0");
    startNewNumber = true;
});
```

Starting Swing on the EDT:
```java
SwingUtilities.invokeLater(() -> new EventDrivenCalculator().setVisible(true));
```

Keeping the EDT free (Bug 1 fix):
```java
new Thread(() -> {
    // long work here, off the EDT
    SwingUtilities.invokeLater(() -> statusLabel.setText("Done!"));
}).start();
```

## Lessons From the Bug Fix Challenge
1. Never block the EDT with long work such as `Thread.sleep()`.
2. A button does nothing until a listener is registered.
3. Only update Swing components on the EDT.
4. Remove references (e.g. clear lists) to components that are no longer used, or they cannot be garbage collected.

## Questions I Still Have
- When should I use `SwingWorker` instead of a plain `Thread` with `invokeLater()`?
- How does JavaFX's event loop compare to Swing's EDT?
- How do event-driven systems handle events that arrive faster than they can be processed?
