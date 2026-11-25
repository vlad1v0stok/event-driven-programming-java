# Activity 3: "Procedural vs. Event-Driven" - Side-by-Side Coding

## Step 2: Procedural Calculator Questions

**1. What is the exact order of steps in this program?**
Print the title → ask for and read the first number → ask for and read the operator → ask for and read the second number → calculate with the `switch` → print the result → close the scanner and end.

**2. What happens if the user enters the wrong input at step 2?**
If the operator is not `+`, `-`, `*` or `/`, the program still asks for the second number, then the `switch` reaches `default`, prints "Invalid operator" and the program ends. The user has to start again from the beginning. (If the user types text where a number is expected, `nextDouble()` throws an `InputMismatchException` and the program crashes.)

**3. Can the user calculate again without restarting the program?**
No. The program runs from top to bottom exactly once and then exits.

**4. Who controls the flow of this program - the user or the program?**
The program. It decides what is asked and in which order, and the user can only answer each question when asked.

## Step 3: Event-Driven Calculator Questions

**1. Can the user click buttons in any order?**
Yes. Any button can be clicked at any time, and each click is handled by that button's own handler.

**2. What happens if the user makes a mistake?**
They click **C**, which resets the display, the first operand, the operator and the `startNewNumber` flag, and they continue. Dividing by zero shows "Error" instead of crashing.

**3. Can the user calculate again without restarting?**
Yes. After a result is shown, the next number or operator click starts a new calculation, and the window stays open until the user closes it.

**4. Who controls the flow - the user or the program?**
The user. The program sets up the window and handlers and then waits; the user's clicks decide what code runs next.

**5. How many event handlers are in this program?**
16 ActionListeners are registered, one per button: 10 number buttons, 4 operator buttons, 1 equals button and 1 clear button. They come from 4 kinds of handler (number, operator, equals, clear), created by the four factory methods `createNumberButton()`, `createOperatorButton()`, `createEqualsButton()` and `createClearButton()`.

## Step 4: Comparison Table

| ASPECT | PROCEDURAL | EVENT-DRIVEN |
|--------|------------|--------------|
| Who controls the flow? | The program | The user |
| Can user make mistakes and recover easily? | No - must restart the program | Yes - click "C" and continue |
| Can user interact in any order? | No - fixed sequence (number, operator, number) | Yes - any button at any time |
| How many possible execution paths? | One fixed path | Many - depends on the order of clicks |
| Code organization | Everything in `main()`, top to bottom | Window setup plus factory methods, each attaching a handler |
| User experience | Console, rigid, one calculation per run | GUI, flexible, unlimited calculations |

## Step 5: Reflection

The main difference between the two approaches is who controls the flow: the procedural calculator runs its steps in a fixed order from `main()`, while the event-driven calculator sets up its buttons and then waits for the user to trigger handlers. In the procedural version a single mistake means restarting, and only one calculation is possible per run. In the event-driven version the user can click in any order, correct mistakes with the Clear button and keep calculating as long as the window is open. For interactive applications I prefer the event-driven approach because it lets the user decide what happens next. It is better for GUIs because a GUI shows many controls at once and the program cannot know in advance which one will be used. Event-driven code also keeps each response in its own small handler, which makes the code easier to read and extend. Procedural code is still useful for simple scripts and batch jobs that run once without user interaction.
