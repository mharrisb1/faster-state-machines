import {
  transition__StateTable,
  transition__SwtichBased,
  transition__ObjectMap,
} from "../src/transition.js";
import { State } from "../src/types.js";

// Generate a massive array of random triggers for a robust benchmark
const ITERATIONS = 10_000_000;
console.log(`Generating ${ITERATIONS.toLocaleString()} random triggers...`);
const triggers = new Uint8Array(ITERATIONS); // Use Uint8Array to save memory on the input array
for (let i = 0; i < ITERATIONS; i++) {
  triggers[i] = Math.floor(Math.random() * 3);
}

const MACHINES = [transition__StateTable, transition__SwtichBased, transition__ObjectMap];

console.log("Starting benchmark...\n");

for (const machine of MACHINES) {
  const startTime = process.hrtime.bigint();

  let state = State.NULLSET;
  for (let i = 0; i < ITERATIONS; i++) {
    state = machine(state, triggers[i]);
  }

  const endTime = process.hrtime.bigint();

  const durationMs = Number(endTime - startTime) / 1_000_000;
  const opsPerSec = ITERATIONS / (durationMs / 1000);

  console.log(`==================================================`);
  console.log(`[Machine: ${machine.name}]`);
  console.log(`Final State : ${state}`); // Prevents the engine from optimizing away the loop
  console.log(`Time taken  : ${durationMs.toFixed(2)} ms`);
  console.log(`Throughput  : ${opsPerSec.toLocaleString(undefined, { maximumFractionDigits: 0 })} ops/sec`);
}
console.log(`==================================================`);
