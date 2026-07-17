import assert from "node:assert";
import test from "node:test";
import {
  transition__ObjectMap,
  transition__StateTable,
  transition__SwtichBased,
} from "../src/transition.js";
import { State, Trigger } from "../src/types.js";

test("State machines result in correct final states", () => {
  const MACHINES = [transition__StateTable, transition__SwtichBased, transition__ObjectMap];
  const TEST_CASES = [
    // From NULLSET
    [[Trigger.CLICK_N], State.INTERSECT],
    [[Trigger.CLICK_A], State.SET_A],
    [[Trigger.CLICK_B], State.SET_B],

    // From INTERSECT (reached via N)
    [[Trigger.CLICK_N, Trigger.CLICK_N], State.NULLSET],
    [[Trigger.CLICK_N, Trigger.CLICK_A], State.SET_A],
    [[Trigger.CLICK_N, Trigger.CLICK_B], State.SET_B],

    // From SET_A (reached via A)
    [[Trigger.CLICK_A, Trigger.CLICK_N], State.A_DIFF_B],
    [[Trigger.CLICK_A, Trigger.CLICK_A], State.NULLSET],
    [[Trigger.CLICK_A, Trigger.CLICK_B], State.UNION],

    // From SET_B (reached via B)
    [[Trigger.CLICK_B, Trigger.CLICK_N], State.B_DIFF_A],
    [[Trigger.CLICK_B, Trigger.CLICK_A], State.UNION],
    [[Trigger.CLICK_B, Trigger.CLICK_B], State.NULLSET],

    // From A_DIFF_B (reached via A, N)
    [[Trigger.CLICK_A, Trigger.CLICK_N, Trigger.CLICK_N], State.SET_A],
    [[Trigger.CLICK_A, Trigger.CLICK_N, Trigger.CLICK_A], State.NULLSET],
    [[Trigger.CLICK_A, Trigger.CLICK_N, Trigger.CLICK_B], State.SYMDIFF],

    // From B_DIFF_A (reached via B, N)
    [[Trigger.CLICK_B, Trigger.CLICK_N, Trigger.CLICK_N], State.SET_B],
    [[Trigger.CLICK_B, Trigger.CLICK_N, Trigger.CLICK_A], State.SYMDIFF],
    [[Trigger.CLICK_B, Trigger.CLICK_N, Trigger.CLICK_B], State.NULLSET],

    // From UNION (reached via A, B)
    [[Trigger.CLICK_A, Trigger.CLICK_B, Trigger.CLICK_N], State.SYMDIFF],
    [[Trigger.CLICK_A, Trigger.CLICK_B, Trigger.CLICK_A], State.SET_B],
    [[Trigger.CLICK_A, Trigger.CLICK_B, Trigger.CLICK_B], State.SET_A],

    // From SYMDIFF (reached via A, B, N)
    [
      [Trigger.CLICK_A, Trigger.CLICK_B, Trigger.CLICK_N, Trigger.CLICK_N],
      State.UNION,
    ],
    [
      [Trigger.CLICK_A, Trigger.CLICK_B, Trigger.CLICK_N, Trigger.CLICK_A],
      State.B_DIFF_A,
    ],
    [
      [Trigger.CLICK_A, Trigger.CLICK_B, Trigger.CLICK_N, Trigger.CLICK_B],
      State.A_DIFF_B,
    ],
  ];

  for (const machine of MACHINES) {
    TEST_CASES.forEach(([inputs, expectedState], ix) => {
      let state = State.NULLSET;
      for (const trigger of inputs) {
        state = machine(state, trigger);
      }
      assert(
        state === expectedState,
        `[Test=${ix + 1}] [Machine=${machine.name}] Actual (${state}) !== Expected (${expectedState})`,
      );
    });
  }
});
