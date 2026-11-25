# Activity 1: "Spot the Components" - Real-World EDP Scavenger Hunt

## Step 2 and Step 3: EDP Components Table

| # | System | Event Source | Event | Listener | Handler/Action |
|---|--------|--------------|-------|----------|----------------|
| 1 | ATM Machine Operation | Keypad button | Button pressed | ATM software | Verify PIN |
| 1 | ATM Machine Operation | Card slot | Card inserted | Card reader | Read card data |
| 1 | ATM Machine Operation | Cash dispenser | Cash taken | Sensor | End transaction |
| 2 | Microwave Oven Use | Start button | Start pressed | Microwave controller | Turn on magnetron, start countdown |
| 2 | Microwave Oven Use | Door sensor | Door opened | Safety interlock | Stop cooking immediately |
| 2 | Microwave Oven Use | Internal timer | Time reaches zero | Microwave controller | Stop heating and beep |
| 3 | Traffic Light System | Timer | Time interval expires | Traffic controller | Change light (green → amber → red) |
| 3 | Traffic Light System | Pedestrian push button | Crossing requested | Traffic controller | Schedule the pedestrian "walk" phase |
| 3 | Traffic Light System | Road sensor (induction loop) | Vehicle detected | Traffic controller | Extend green time for that lane |
| 4 | Video Game Gameplay | Keyboard / controller | Key or button pressed | Input manager | Move or make the character jump |
| 4 | Video Game Gameplay | Game object (enemy) | Collision with player | Collision/physics system | Reduce player health |
| 4 | Video Game Gameplay | Game timer | Level timer expires | Game manager | End the level / show "Time's up" |
| 5 | Elevator Operation | Hall call button | Button pressed | Elevator controller | Dispatch a car to that floor |
| 5 | Elevator Operation | Floor button inside car | Floor selected | Elevator controller | Add floor to the stop list |
| 5 | Elevator Operation | Door sensor | Obstruction detected | Door controller | Re-open the doors |
| 6 | Smartphone Touchscreen | Touchscreen | Screen tapped on an app icon | Operating system (launcher) | Open the app |
| 6 | Smartphone Touchscreen | Touchscreen | Swipe gesture | Gesture recognizer | Scroll the page / switch screen |
| 7 | Online Shopping Website | "Add to Cart" button | Button clicked | Cart service | Add the item and update the cart count |
| 7 | Online Shopping Website | "Checkout" button | Button clicked | Order service | Process payment and create the order |
| 8 | Security Alarm System | Motion sensor | Motion detected | Alarm control panel | Sound the siren and alert the owner |
| 8 | Security Alarm System | Keypad | Correct code entered | Alarm control panel | Disarm the system |
| 9 | Self-Checkout at Supermarket | Barcode scanner | Item scanned | Checkout software | Look up price and add item to the bill |
| 9 | Self-Checkout at Supermarket | Bagging-area scale | Weight changed | Checkout software | Verify the weight matches the scanned item |
| 9 | Self-Checkout at Supermarket | Card reader | Card tapped | Payment module | Process payment and print receipt |

Systems analysed: ATM, microwave, traffic light, video game, elevator, smartphone touchscreen, online shopping website, security alarm, self-checkout (9 systems).

## Step 4: Self-Check

**1. What is the difference between an event source and an event listener?**
The event source is the object where the event happens and which creates the event (for example a button, a keypad or a sensor). The event listener is the object that has registered interest in that source and waits to be told when the event happens, so it can respond. The source announces; the listener reacts.

**2. Can one event source have multiple listeners? Give an example.**
Yes. On an online shopping website, the "Checkout" button can be listened to by the payment service (charge the card), the inventory service (reduce stock) and the email service (send a confirmation). In Swing, a single `JButton` can have several `ActionListener`s added with `addActionListener()`, and all of them are called when it is clicked.

**3. Can one listener handle events from multiple sources? Give an example.**
Yes. An elevator controller listens to every hall call button on every floor and every floor button inside the car. In code, one `ActionListener` can be added to several buttons and use `e.getSource()` to tell which button was clicked, like the number buttons of a calculator sharing the same logic.
