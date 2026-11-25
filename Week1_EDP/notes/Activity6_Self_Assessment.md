# Activity 6: Final Self-Assessment

## Learning Outcome 1: Define EDP Components

**1. What is an event? Give two examples.**
An event is something that happens that the program may need to respond to. Examples: a user clicking a button; a timer expiring.

**2. What is an event source? Give two examples.**
The object that generates (fires) the event. Examples: a `JButton` (fires an `ActionEvent` when clicked); a `javax.swing.Timer` (fires an event every time its delay runs out).

**3. What is an event listener? Give two examples.**
An object that registers with a source and waits to be notified when the event occurs. Examples: `ActionListener`; `MouseListener` (also `KeyListener`).

**4. What is an event handler? Give two examples.**
The code that runs when the event occurs. Examples: the body of `actionPerformed()`; the body of a lambda such as `btn.addActionListener(e -> display.setText("0"))`.

**5. Draw a diagram showing how these four components relate to each other.**

```
 +----------------+   fires    +---------------+  delivered to  +------------------+   calls   +-----------------+
 |  EVENT SOURCE  | ---------> |     EVENT     | -------------> |  EVENT LISTENER  | --------> |  EVENT HANDLER  |
 |  (JButton)     |            | (ActionEvent) |                | (ActionListener) |           | actionPerformed |
 +----------------+            +---------------+                +------------------+           +-----------------+
        ^                                                                |
        |_________________ addActionListener(listener) ___________________|
                           (listener registers with the source)
```

## Learning Outcome 2: Compare Paradigms

**6. How does procedural programming differ from event-driven programming?**
Procedural programming runs a fixed sequence of steps decided by the program. Event-driven programming waits for events and runs the matching handler, so the order of execution is decided by the user or the environment.

**7. What is the main advantage of event-driven programming for GUIs?**
The user can interact with any control at any time in any order, and the program stays responsive, reacting to each action as it happens.

**8. In which scenario would you choose procedural programming over event-driven?**
For tasks with no user interaction that run start to finish in a known order, such as a batch script that reads a file, processes the records and writes a report, or a simple command-line calculation.

## Learning Outcome 3: Event Loop and Queue

**9. What is the event loop? What does it do?**
A loop that runs for the whole life of the application. It repeatedly takes the next event from the event queue and dispatches it to the right handler, then waits for the next one.

**10. What is the event queue? How does it work?**
A FIFO (first-in, first-out) list of events waiting to be processed. New events are added at the back as they happen, and the event loop removes them from the front one at a time.

**11. Why should the event loop never be blocked?**
Because it is the only thing processing events. If a handler takes a long time, no other events are processed: the GUI cannot repaint or respond to clicks, and the application freezes.

**12. What is the EDT in Java Swing?**
The Event Dispatch Thread - the single thread that runs Swing's event loop. All Swing GUI creation and updates must happen on it (using `SwingUtilities.invokeLater()`), and long work must be moved off it.

## Learning Outcome 4: Real-World Examples

**13. List five real-world event-driven systems.**
ATM machine, microwave oven, traffic light system, elevator, video game (also self-checkout machines and online shopping websites).

**14. For one system, identify all four EDP components.**
Elevator: **Event source** - the hall call button; **Event** - the button is pressed on floor 3; **Listener** - the elevator controller; **Handler** - dispatch the nearest car to floor 3 and light the button.

**15. Why are most modern applications event-driven?**
Because modern applications are interactive: they must react to users, network messages, sensors and timers whose timing cannot be predicted. Event-driven design lets them stay responsive and handle many kinds of input without a fixed order.

## Step 2: Score Yourself

Score: ___ / 15
