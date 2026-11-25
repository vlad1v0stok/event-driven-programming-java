import javax.swing.*;

public class Bug1_BlockingEDT extends JFrame {
    private JLabel statusLabel;
    private JButton processButton;

    public Bug1_BlockingEDT() {
        setTitle("Bug 1 - Blocking EDT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        statusLabel = new JLabel("Ready");
        processButton = new JButton("Process");

        processButton.addActionListener(e -> {
            // FIX: The long operation now runs in a background thread,
            // so the EDT stays free to repaint and handle other events.
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                // FIX: The GUI update is sent back to the EDT.
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Done!");
                });
            }).start();
        });

        add(statusLabel);
        add(processButton);

        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug1_BlockingEDT().setVisible(true);
        });
    }
}
