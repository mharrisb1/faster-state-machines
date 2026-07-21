# Optimizing State Machines

> [!NOTE]
> The choice of using a state machine that can be represented as a dense state table is intentional in order to illustrate a particular optimization scenario. Always run your own benchmarks.

## State Table Implementations

1. [Gang of Four (GoF) State Pattern](https://gameprogrammingpatterns.com/state.html#the-state-pattern)
2. [Naive Switch-Based State Pattern](https://gameprogrammingpatterns.com/state.html#enums-and-switches)
3. State Table Pattern
   - 2D Array
   - 1D Array

## Climbing the Optimization Ladder

1. Starting with naive GoF implementation to highlight cost of allocations
2. Move on to switch statements but hightlight branch misses
3. Move to 2D array to avoid branch misses but show cache misses
4. End with 1D array to highlight improvement

## Testing

Just to ensure that the state machines all act the way we expect them too (or at least all fail in the same way), there is a quick script in [Main.java](./src/fsm/Main.java):

```bash
mvn clean compile exec:java -q -Dexec.mainClass="fsm.Main"
```

## Benchmarking

To run the benchmarks:

```bash
mvn clean package && \
   java -jar target/benchmarks.jar -prof gc -prof perfnorm
```
