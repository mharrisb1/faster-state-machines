# State Machine Designs

Common implementations:

1. Switch-Case
2. State Table
3. Object Map

Results:

```bash
node --expose-gc perf/transition.benchmark.js
Generating 10,000,000 random triggers...
Starting benchmark...

==================================================
[Machine: transition__StateTable]
Final State : 4
Time taken  : 31.31 ms
Throughput  : 319,392,531 ops/sec
==================================================
[Machine: transition__SwtichBased]
Final State : 4
Time taken  : 92.52 ms
Throughput  : 108,088,216 ops/sec
==================================================
[Machine: transition__ObjectMap]
Final State : 4
Time taken  : 45.71 ms
Throughput  : 218,784,271 ops/sec
==================================================
```
