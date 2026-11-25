# Bug Fix Documentation

## Bug 1: Blocking the EDT
- **Symptom:** UI freezes when button clicked
- **Cause:** Long operation runs on EDT
- **Fix:** Run operation in background thread, update UI on EDT
- **Code Change:** Wrapped the `Thread.sleep(5000)` call in `new Thread(() -> {...}).start()` inside the button's ActionListener, and moved `statusLabel.setText("Done!")` into `SwingUtilities.invokeLater(() -> {...})` so the label is updated on the EDT. The window can now be moved and clicked during the 5-second wait.

## Bug 2: Missing Listener
- **Symptom:** Button click does nothing
- **Cause:** No ActionListener registered
- **Fix:** Added ActionListener to increment button
- **Code Change:** Added `incrementButton.addActionListener(e -> { count++; countLabel.setText("Count: " + count); });` so each click increases `count` and refreshes the label.

## Bug 3: Wrong Thread
- **Symptom:** Erratic behavior, potential crashes
- **Cause:** GUI updated from background thread
- **Fix:** Used SwingUtilities.invokeLater() for GUI updates
- **Code Change:** Inside the clock's background loop, the time text is built on the background thread and then `timeLabel.setText(...)` is called inside `SwingUtilities.invokeLater(() -> {...})`. Only the `Thread.sleep(1000)` stays on the background thread.

## Bug 4: Memory Leak
- **Symptom:** Memory usage grows over time
- **Cause:** References kept in list after removal from UI
- **Fix:** Clear the list when removing buttons
- **Code Change:** Added `createdButtons.clear();` to the "Remove All" button's ActionListener, right after `buttonPanel.removeAll()`. The removed buttons are no longer referenced, so they can be garbage collected, and new buttons are numbered from 0 again.
