# Activity 3: Refactor a Tightly-Coupled System - Notes

## Step 2: Identify the Coupling Problem

**1. What happens if we want to add a third display class? Do we have to modify DataModel?**
Yes. `DataModel.setValue()` calls each display directly, so adding a third display means editing `DataModel` to add another `new ThirdDisplay().show(value)` line.

**2. What happens if LabelDisplay is deleted or renamed?**
`DataModel` no longer compiles, because it refers to `LabelDisplay` by name. A change to a display class breaks the model.

**3. Can we easily test DataModel in isolation?**
No. Every call to `setValue()` also creates and runs the display classes, so you cannot test the model without them, and you cannot swap in a test double.

## Step 4: Comparison of the Two Approaches

| Aspect | Tightly Coupled | Observer Pattern |
|--------|-----------------|------------------|
| Adding a new display | Modify DataModel | Just register a new observer |
| Testing DataModel in isolation | Hard — it needs display classes | Easy — no display classes required |
| Removing a display | Modify DataModel | Just don't register it |

In the refactored version, `DataModel` only knows the `DataObserver` interface. `TextLogger` was added without changing a single line of `DataModel`, which shows the loose coupling.

## Terminal Output

`java TightlyCoupledSystem`:
```
Label shows: 10
Bar chart height: 10
Label shows: 20
Bar chart height: 20
```

`java RefactoredMain`:
```
Label shows: 10
Bar chart height: 10
LOG: value changed to 10
Label shows: 20
Bar chart height: 20
LOG: value changed to 20
Label shows: 30
Bar chart height: 30
LOG: value changed to 30
```
