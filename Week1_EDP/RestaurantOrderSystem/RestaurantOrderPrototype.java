import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RestaurantOrderPrototype extends JFrame {
    private JLabel statusLabel;
    private JTextArea eventLog;
    private JComboBox<String> tableBox;
    private JComboBox<String> dishBox;
    private JButton placeOrderButton;
    private JButton orderReadyButton;
    private JButton payButton;

    // System state: the order currently being handled
    private String currentTable = null;
    private String currentDish = null;
    private boolean orderReady = false;

    public RestaurantOrderPrototype() {
        setTitle("Restaurant Order System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Status Panel (NORTH)
        statusLabel = new JLabel("System Ready - no active order");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(statusLabel, BorderLayout.NORTH);

        // Event Log (CENTER)
        eventLog = new JTextArea(10, 30);
        eventLog.setEditable(false);
        add(new JScrollPane(eventLog), BorderLayout.CENTER);

        // Control Panel (SOUTH)
        JPanel controlPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Order selection: table and dish
        JPanel selectionPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        tableBox = new JComboBox<>(new String[]{"Table 1", "Table 2", "Table 3", "Table 4"});
        dishBox = new JComboBox<>(new String[]{"Pilau", "Ugali & Fish", "Chicken Curry", "Chapati & Beans"});
        selectionPanel.add(tableBox);
        selectionPanel.add(dishBox);

        // Place Order Button (Waiter's tablet)
        placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(e -> {
            if (currentTable != null) {
                logEvent("Order rejected - " + currentTable + " still has an active order");
                statusLabel.setText("Finish the current order first");
                return;
            }
            currentTable = (String) tableBox.getSelectedItem();
            currentDish = (String) dishBox.getSelectedItem();
            orderReady = false;
            logEvent("Order placed: " + currentDish + " for " + currentTable);
            logEvent("Order sent to kitchen queue");
            statusLabel.setText(currentTable + ": order in kitchen");
        });

        // Order Ready Button (Kitchen screen)
        orderReadyButton = new JButton("Kitchen: Mark Order Ready");
        orderReadyButton.addActionListener(e -> {
            if (currentTable == null) {
                logEvent("No order in the kitchen");
                statusLabel.setText("Nothing to prepare");
                return;
            }
            orderReady = true;
            logEvent("Order ready: " + currentDish + " for " + currentTable);
            logEvent("Waiter notified to serve " + currentTable);
            statusLabel.setText(currentTable + ": served, awaiting payment");
        });

        // Pay Bill Button (Payment terminal)
        payButton = new JButton("Payment Received");
        payButton.addActionListener(e -> {
            if (currentTable == null || !orderReady) {
                logEvent("Payment rejected - order not yet served");
                statusLabel.setText("Serve the order before payment");
                return;
            }
            logEvent("Payment received from " + currentTable + " - receipt printed");
            logEvent(currentTable + " is now free");
            statusLabel.setText(currentTable + " is free");
            currentTable = null;
            currentDish = null;
            orderReady = false;
        });

        controlPanel.add(selectionPanel);
        controlPanel.add(placeOrderButton);
        controlPanel.add(orderReadyButton);
        controlPanel.add(payButton);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(450, 450);
        setLocationRelativeTo(null);
    }

    private void logEvent(String event) {
        String timestamp = new Date().toString();
        eventLog.append("[" + timestamp + "] " + event + "\n");
        eventLog.setCaretPosition(eventLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RestaurantOrderPrototype().setVisible(true);
        });
    }
}
