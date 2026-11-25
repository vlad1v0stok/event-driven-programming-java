# Activity 1: First Swing Window - Output

Running `java FirstWindow` opens a 500 × 400 window titled "My First Swing App", centered on the screen. The terminal shows:

```
Window created on thread: AWT-EventQueue-0
```

`AWT-EventQueue-0` is the Event Dispatch Thread, which proves the window was created on the EDT because the code was wrapped in `SwingUtilities.invokeLater()`.
