# Activity 4: The EDT Rule - Observations

## FreezeDemo (BAD practice)
After clicking "Freeze the UI", the whole window stopped responding for about 5 seconds: it could not be moved or resized, and clicking the button again did nothing. The label never visibly showed "Working..." - it went straight from "Ready" to "Done!" when the 5 seconds were over. This happens because `Thread.sleep(5000)` runs inside the ActionListener, which runs on the EDT. While the EDT is sleeping, the event loop cannot repaint the label or process any other events.

## FixedDemo (GOOD practice)
After clicking "Do Long Operation", the label immediately changed to "Working..." and the button was greyed out. During the 5 seconds the window could still be moved and resized freely. After 5 seconds the label changed to "Done!" and the button was enabled again. This works because the `Thread.sleep(5000)` runs on a separate background thread, and the final GUI update is sent back to the EDT with `SwingUtilities.invokeLater()`.

## Conclusion
Both programs do the same "work", but only the second keeps the EDT free. The two golden rules are: (1) create and update the GUI only on the EDT, and (2) never run long operations on the EDT.
