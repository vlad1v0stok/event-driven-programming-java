# Week 4 - Final Self-Assessment

## Learning Outcome 1: Implement ActionListener using multiple techniques
Rate yourself 1–5: ___

Name three ways to implement an ActionListener:
1. A named class that implements `ActionListener` (e.g. `NamedClassListener`)
2. An anonymous inner class: `new ActionListener() { public void actionPerformed(ActionEvent e) { ... } }`
3. A lambda expression: `e -> { ... }` (a method reference such as `ListenerDemo::handleButtonClick` also works)

## Learning Outcome 2: Handle state changes in checkboxes, radio buttons, and text fields
Rate yourself 1–5: ___

Which listener handles checkbox state changes? `ItemListener` (`itemStateChanged()`)

Which listener handles text field changes? `DocumentListener` (`insertUpdate()`, `removeUpdate()`, `changedUpdate()`), added to the field's `Document`

## Learning Outcome 3: Differentiate between listener interfaces
Rate yourself 1–5: ___

Match the component to the listener:

| Component | Listener |
|-----------|----------|
| JButton | ActionListener |
| JCheckBox | ItemListener |
| JTextField | DocumentListener (ActionListener for the Enter key) |
| Mouse hover | MouseListener (`mouseEntered` / `mouseExited`); MouseMotionListener (`mouseMoved`) |
| Key press | KeyListener (`keyPressed`) |

## Learning Outcome 4: Build an interactive Swing application
Rate yourself 1–5: ___

Did your Interactive Registration Form work?
- [x] Yes
- [ ] No (what error? ____)
