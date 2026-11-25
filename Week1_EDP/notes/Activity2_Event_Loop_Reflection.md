# Activity 2: "Build the Event Loop" - Physical Simulation

## Round 3 Questions: Simulating a Blocked Event Loop

**1. What happened to the other events while you were "stuck" on the slow handler?**
They stayed in the queue waiting. None of them could be processed, no matter how simple they were, because the loop handles only one event at a time and it was busy with the slow card.

**2. What would a user see if this happened in a real application?**
The application would look frozen: buttons would not respond, the window would not repaint, move or resize, and on Windows the title bar might show "Not Responding". When the slow handler finally finished, all the waiting clicks would suddenly be processed at once.

**3. How could this problem be fixed?**
Move the slow work off the event loop: run it on a background thread and, when it finishes, send only the small GUI update back to the event loop (in Swing, with `SwingUtilities.invokeLater()`). The event loop is then free to keep processing the other events in the queue.

## Step 5: Reflection

The event queue is a first-in, first-out waiting line that stores events such as clicks, key presses, timer ticks and window resizes in the order they happen. The event loop is the part of the system that runs continuously, takes the next event from the front of the queue, dispatches it to the handler that is registered for it, and then goes back for the next one. Because there is only one loop and it processes one event at a time, every handler is sharing the same worker. In the simulation, when I held one card and counted slowly, every other card just sat in the queue, even the quick ones. In a real application this means the screen cannot redraw and no button responds, so the program appears frozen to the user. That is why the event loop should never be blocked: long tasks such as file downloads or database queries must run on a background thread, and only the short GUI update should be sent back to the loop.
