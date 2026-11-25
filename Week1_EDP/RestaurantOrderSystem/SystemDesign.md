# Event-Driven System Design

## System Name: Restaurant Order System

---

## Part 1: Event Sources
List all components that can generate events:

1. **Waiter's order tablet** - Generates "Order Placed" and "Order Cancelled" events when a waiter submits or cancels a table's order.
2. **Kitchen display screen** - Generates "Order Started" and "Order Ready" events when the chef begins and finishes cooking.
3. **Payment terminal (POS / card machine)** - Generates "Payment Received" and "Payment Failed" events.
4. **Table call button** - Generates a "Waiter Requested" event when a customer presses it.
5. **Inventory sensor / stock system** - Generates an "Item Out of Stock" event when an ingredient runs out.

---

## Part 2: Events
List all possible events in the system:

| Event Name | Source | Description |
|------------|--------|-------------|
| Order Placed | Waiter's order tablet | Waiter selects a table and dishes and presses "Place Order" |
| Order Ready | Kitchen display screen | Chef presses "Ready" when the dish is cooked |
| Payment Received | Payment terminal | Customer's card or cash payment is accepted |
| Waiter Requested | Table call button | Customer presses the call button on the table |
| Item Out of Stock | Inventory sensor / stock system | Stock level of an ingredient reaches zero |

---

## Part 3: Event Handlers
For each event, describe what should happen:

| Event | Handler Action |
|-------|----------------|
| Order Placed | Add the order to the kitchen queue, mark the table as "Ordered", and log the time |
| Order Ready | Notify the waiter that the order for that table can be served, and mark the table as "Served" |
| Payment Received | Print the receipt, mark the table as "Free", and record the sale |
| Waiter Requested | Show an alert on the waiter's tablet with the table number |
| Item Out of Stock | Disable that dish on the order tablet so it cannot be ordered |

---

## Part 4: Event Flow Diagram
Draw a diagram showing how events flow through the system:

```
 Waiter taps        Order Tablet       OrderPlaced       Event        Event         Handler:
 "Place Order"  →   (Event Source) →   Event Object  →   Queue    →   Loop     →    add to kitchen queue
                                                                                         │
                                                                                         ▼
                                                                                 GUI Update: kitchen
                                                                                 screen shows new order
                                                                                         │
 Chef taps          Kitchen Screen     OrderReady        Event        Event              ▼
 "Ready"        →   (Event Source) →   Event Object  →   Queue    →   Loop     →    Handler: notify waiter
                                                                                         │
                                                                                         ▼
 Customer pays      Payment Terminal   PaymentReceived   Event        Event         Handler: print receipt,
 by card        →   (Event Source) →   Event Object  →   Queue    →   Loop     →    free the table
                                                                                         │
                                                                                         ▼
                                                                                 GUI Update: table
                                                                                 status shows "Free"
```

General flow:

User Action → Event Source → Event Object → Event Queue → Event Loop → Handler → GUI Update

---

## Part 5: Potential Issues
What could go wrong with this system?

1. **Slow payment processing blocks the screen** - Contacting the bank can take several seconds. Run the payment request on a background thread and update the screen on the EDT when the result comes back, so the tablet never freezes.
2. **Duplicate orders from double-tapping "Place Order"** - Disable the button as soon as it is clicked and re-enable it only after the order is accepted, or give each order a unique ID and ignore repeats.
3. **Events arriving in the wrong order (e.g. "Payment Received" before "Order Ready")** - Check the table's current state in each handler and reject events that are not valid for that state (for example, only allow payment after the order is served).
