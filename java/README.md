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

### Results

```
Benchmark                                             Mode  Cnt          Score         Error      Units
FsmBenchmark.measureGof                              thrpt    5   73440877.999 ± 1937151.041      ops/s
FsmBenchmark.measureGof:CPI                          thrpt               0.702                clks/insn
FsmBenchmark.measureGof:IPC                          thrpt               1.424                insns/clk
FsmBenchmark.measureGof:L1-dcache-load-misses        thrpt               0.337                     #/op
FsmBenchmark.measureGof:L1-dcache-loads              thrpt              76.565                     #/op
FsmBenchmark.measureGof:L1-icache-load-misses        thrpt              ≈ 10⁻⁴                     #/op
FsmBenchmark.measureGof:L1-icache-loads              thrpt               0.138                     #/op
FsmBenchmark.measureGof:branch-misses                thrpt               1.212                     #/op
FsmBenchmark.measureGof:branches                     thrpt              19.131                     #/op
FsmBenchmark.measureGof:cycles                       thrpt              67.137                     #/op
FsmBenchmark.measureGof:dTLB-load-misses             thrpt               0.005                     #/op
FsmBenchmark.measureGof:dTLB-loads                   thrpt               0.007                     #/op
FsmBenchmark.measureGof:gc.alloc.rate                thrpt    5       1120.607 ±      29.558     MB/sec
FsmBenchmark.measureGof:gc.alloc.rate.norm           thrpt    5         16.000 ±       0.001       B/op
FsmBenchmark.measureGof:gc.count                     thrpt    5        136.000                   counts
FsmBenchmark.measureGof:gc.time                      thrpt    5        140.000                       ms
FsmBenchmark.measureGof:iTLB-load-misses             thrpt              ≈ 10⁻⁴                     #/op
FsmBenchmark.measureGof:iTLB-loads                   thrpt              ≈ 10⁻³                     #/op
FsmBenchmark.measureGof:instructions                 thrpt              95.627                     #/op
FsmBenchmark.measureGof:stalled-cycles-frontend      thrpt              11.335                     #/op
FsmBenchmark.measureSwitch                           thrpt    5  134087417.225 ±  730050.936      ops/s
FsmBenchmark.measureSwitch:CPI                       thrpt               0.575                clks/insn
FsmBenchmark.measureSwitch:IPC                       thrpt               1.739                insns/clk
FsmBenchmark.measureSwitch:L1-dcache-load-misses     thrpt               0.067                     #/op
FsmBenchmark.measureSwitch:L1-dcache-loads           thrpt              28.727                     #/op
FsmBenchmark.measureSwitch:L1-icache-load-misses     thrpt              ≈ 10⁻⁴                     #/op
FsmBenchmark.measureSwitch:L1-icache-loads           thrpt               0.052                     #/op
FsmBenchmark.measureSwitch:branch-misses             thrpt               0.717                     #/op
FsmBenchmark.measureSwitch:branches                  thrpt              15.205                     #/op
FsmBenchmark.measureSwitch:cycles                    thrpt              36.647                     #/op
FsmBenchmark.measureSwitch:dTLB-load-misses          thrpt               0.001                     #/op
FsmBenchmark.measureSwitch:dTLB-loads                thrpt               0.001                     #/op
FsmBenchmark.measureSwitch:gc.alloc.rate             thrpt    5         ≈ 10⁻³                   MB/sec
FsmBenchmark.measureSwitch:gc.alloc.rate.norm        thrpt    5         ≈ 10⁻⁶                     B/op
FsmBenchmark.measureSwitch:gc.count                  thrpt    5            ≈ 0                   counts
FsmBenchmark.measureSwitch:iTLB-load-misses          thrpt              ≈ 10⁻⁴                     #/op
FsmBenchmark.measureSwitch:iTLB-loads                thrpt              ≈ 10⁻⁵                     #/op
FsmBenchmark.measureSwitch:instructions              thrpt              63.728                     #/op
FsmBenchmark.measureSwitch:stalled-cycles-frontend   thrpt               6.209                     #/op
FsmBenchmark.measureTable1D                          thrpt    5  695174019.858 ± 6265962.411      ops/s
FsmBenchmark.measureTable1D:CPI                      thrpt               0.881                clks/insn
FsmBenchmark.measureTable1D:IPC                      thrpt               1.136                insns/clk
FsmBenchmark.measureTable1D:L1-dcache-load-misses    thrpt               0.063                     #/op
FsmBenchmark.measureTable1D:L1-dcache-loads          thrpt               4.009                     #/op
FsmBenchmark.measureTable1D:L1-icache-load-misses    thrpt              ≈ 10⁻⁵                     #/op
FsmBenchmark.measureTable1D:L1-icache-loads          thrpt               0.011                     #/op
FsmBenchmark.measureTable1D:branch-misses            thrpt               0.001                     #/op
FsmBenchmark.measureTable1D:branches                 thrpt               1.255                     #/op
FsmBenchmark.measureTable1D:cycles                   thrpt               7.065                     #/op
FsmBenchmark.measureTable1D:dTLB-load-misses         thrpt               0.001                     #/op
FsmBenchmark.measureTable1D:dTLB-loads               thrpt               0.001                     #/op
FsmBenchmark.measureTable1D:gc.alloc.rate            thrpt    5          0.004 ±       0.001     MB/sec
FsmBenchmark.measureTable1D:gc.alloc.rate.norm       thrpt    5         ≈ 10⁻⁵                     B/op
FsmBenchmark.measureTable1D:gc.count                 thrpt    5            ≈ 0                   counts
FsmBenchmark.measureTable1D:iTLB-load-misses         thrpt              ≈ 10⁻⁶                     #/op
FsmBenchmark.measureTable1D:iTLB-loads               thrpt              ≈ 10⁻⁵                     #/op
FsmBenchmark.measureTable1D:instructions             thrpt               8.023                     #/op
FsmBenchmark.measureTable1D:stalled-cycles-frontend  thrpt               0.035                     #/op
FsmBenchmark.measureTable2D                          thrpt    5  443323204.106 ±  558672.631      ops/s
FsmBenchmark.measureTable2D:CPI                      thrpt               0.851                clks/insn
FsmBenchmark.measureTable2D:IPC                      thrpt               1.176                insns/clk
FsmBenchmark.measureTable2D:L1-dcache-load-misses    thrpt               0.064                     #/op
FsmBenchmark.measureTable2D:L1-dcache-loads          thrpt               6.010                     #/op
FsmBenchmark.measureTable2D:L1-icache-load-misses    thrpt              ≈ 10⁻⁵                     #/op
FsmBenchmark.measureTable2D:L1-icache-loads          thrpt               0.016                     #/op
FsmBenchmark.measureTable2D:branch-misses            thrpt               0.001                     #/op
FsmBenchmark.measureTable2D:branches                 thrpt               2.507                     #/op
FsmBenchmark.measureTable2D:cycles                   thrpt              11.090                     #/op
FsmBenchmark.measureTable2D:dTLB-load-misses         thrpt               0.001                     #/op
FsmBenchmark.measureTable2D:dTLB-loads               thrpt               0.001                     #/op
FsmBenchmark.measureTable2D:gc.alloc.rate            thrpt    5          0.002 ±       0.001     MB/sec
FsmBenchmark.measureTable2D:gc.alloc.rate.norm       thrpt    5         ≈ 10⁻⁵                     B/op
FsmBenchmark.measureTable2D:gc.count                 thrpt    5            ≈ 0                   counts
FsmBenchmark.measureTable2D:iTLB-load-misses         thrpt              ≈ 10⁻⁶                     #/op
FsmBenchmark.measureTable2D:iTLB-loads               thrpt              ≈ 10⁻⁵                     #/op
FsmBenchmark.measureTable2D:instructions             thrpt              13.037                     #/op
FsmBenchmark.measureTable2D:stalled-cycles-frontend  thrpt               0.046                     #/op
```

### System Info

```
H/W path            Device          Class          Description
==============================================================
                                    system         EliteMini Series (MGF7BSI)
/0                                  bus            F7BSI
/0/0                                memory         64KiB BIOS
/0/b                                memory         512KiB L1 cache
/0/c                                memory         8MiB L2 cache
/0/d                                memory         16MiB L3 cache
/0/e                                processor      AMD Ryzen 7 8745H w/ Radeon 780M Graphics
/0/10                               memory         32GiB System Memory
/0/10/0                             memory         16GiB SODIMM Synchronous Unbuffered (Unregistered) 5600 MHz (0.2 ns)
/0/10/1                             memory         16GiB SODIMM Synchronous Unbuffered (Unregistered) 5600 MHz (0.2 ns)
```
