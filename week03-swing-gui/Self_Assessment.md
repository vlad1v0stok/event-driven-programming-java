# Week 3 - Final Self-Assessment

## Learning Outcome 1: Create a functional Swing application with a main window
Rate yourself 1–5: ___

Can you write from memory the minimal code to create a JFrame? Write it here:

```java
import javax.swing.*;

public class MinimalWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Window");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
```

## Learning Outcome 2: Add, customize, and arrange components using layouts
Rate yourself 1–5: ___

Name four layout managers and one use case for each:

| Layout | Use Case |
|--------|----------|
| FlowLayout | A toolbar - a row of buttons that wraps when the window is narrow |
| BorderLayout | The main application window: header (NORTH), sidebar (WEST), content (CENTER), status bar (SOUTH) |
| GridLayout | A calculator keypad - a 4 × 4 grid of equal-sized buttons |
| BoxLayout | Stacking items vertically, like the welcome message, cards and notifications in the Student Portal |

## Learning Outcome 3: Ensure GUI code runs on the EDT
Rate yourself 1–5: ___

Write the two golden rules of Swing threading:
1. All GUI creation and updates must happen on the EDT (use `SwingUtilities.invokeLater()`).
2. Never perform long operations on the EDT - run them on a background thread and send only the GUI update back to the EDT.

## Learning Outcome 4: Build a static, non-interactive UI layout
Rate yourself 1–5: ___

Did your StudentPortal.java run successfully?
- [x] Yes
- [ ] No (what error did you see? ____)
