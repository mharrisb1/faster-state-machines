import { State, Trigger } from "./types.js";

/**
 * @callback Transition
 * @param {State} state
 * @param {Trigger} trigger
 * @returns {State}
 */

/**
 * @type {Transition}
 */
export function transition__SwtichBased(state, trigger) {
  switch (state) {
    case State.NULLSET:
      switch (trigger) {
        case Trigger.CLICK_N:
          return State.INTERSECT;
        case Trigger.CLICK_A:
          return State.SET_A;
        case Trigger.CLICK_B:
          return State.SET_B;
      }
      break;

    case State.INTERSECT:
      switch (trigger) {
        case Trigger.CLICK_N:
          return State.NULLSET;
        case Trigger.CLICK_A:
          return State.SET_A;
        case Trigger.CLICK_B:
          return State.SET_B;
      }
      break;

    case State.SET_A:
      switch (trigger) {
        case Trigger.CLICK_N:
          return State.A_DIFF_B;
        case Trigger.CLICK_A:
          return State.NULLSET;
        case Trigger.CLICK_B:
          return State.UNION;
      }
      break;

    case State.SET_B:
      switch (trigger) {
        case Trigger.CLICK_N:
          return State.B_DIFF_A;
        case Trigger.CLICK_A:
          return State.UNION;
        case Trigger.CLICK_B:
          return State.NULLSET;
      }
      break;

    case State.A_DIFF_B:
      switch (trigger) {
        case Trigger.CLICK_N:
          return State.SET_A;
        case Trigger.CLICK_A:
          return State.NULLSET;
        case Trigger.CLICK_B:
          return State.SYMDIFF;
      }
      break;

    case State.B_DIFF_A:
      switch (trigger) {
        case Trigger.CLICK_N:
          return State.SET_B;
        case Trigger.CLICK_A:
          return State.SYMDIFF;
        case Trigger.CLICK_B:
          return State.NULLSET;
      }
      break;

    case State.UNION:
      switch (trigger) {
        case Trigger.CLICK_N:
          return State.SYMDIFF;
        case Trigger.CLICK_A:
          return State.SET_B;
        case Trigger.CLICK_B:
          return State.SET_A;
      }
      break;

    case State.SYMDIFF:
      switch (trigger) {
        case Trigger.CLICK_N:
          return State.UNION;
        case Trigger.CLICK_A:
          return State.B_DIFF_A;
        case Trigger.CLICK_B:
          return State.A_DIFF_B;
      }
      break;
  }
}

/**
 * @static
 * @type {State[][]}
 */
const STATE_TABLE = [
  //    [CLICK_N]     [CLICK_A]     [CLICK_B]
  // NULLSET
  [State.INTERSECT, State.SET_A, State.SET_B],
  // INTERSECT
  [State.NULLSET, State.SET_A, State.SET_B],
  // SET_A
  [State.A_DIFF_B, State.NULLSET, State.UNION],
  // SET_B
  [State.B_DIFF_A, State.UNION, State.NULLSET],
  // A_DIFF_B
  [State.SET_A, State.NULLSET, State.SYMDIFF],
  // B_DIFF_A
  [State.SET_B, State.SYMDIFF, State.NULLSET],
  // UNION
  [State.SYMDIFF, State.SET_B, State.SET_A],
  // SYMDIFF
  [State.UNION, State.B_DIFF_A, State.A_DIFF_B],
];

/**
 * @type {Transition}
 */
export function transition__StateTable(state, trigger) {
  return STATE_TABLE[state][trigger];
}

/**
 * @type {Record<State, Record<Trigger, State>>}
 */
const OBJECT_MAP = {
  [State.NULLSET]: {
    [Trigger.CLICK_N]: State.INTERSECT,
    [Trigger.CLICK_A]: State.SET_A,
    [Trigger.CLICK_B]: State.SET_B,
  },
  [State.INTERSECT]: {
    [Trigger.CLICK_N]: State.NULLSET,
    [Trigger.CLICK_A]: State.SET_A,
    [Trigger.CLICK_B]: State.SET_B,
  },
  [State.SET_A]: {
    [Trigger.CLICK_N]: State.A_DIFF_B,
    [Trigger.CLICK_A]: State.NULLSET,
    [Trigger.CLICK_B]: State.UNION,
  },
  [State.SET_B]: {
    [Trigger.CLICK_N]: State.B_DIFF_A,
    [Trigger.CLICK_A]: State.UNION,
    [Trigger.CLICK_B]: State.NULLSET,
  },
  [State.A_DIFF_B]: {
    [Trigger.CLICK_N]: State.SET_A,
    [Trigger.CLICK_A]: State.NULLSET,
    [Trigger.CLICK_B]: State.SYMDIFF,
  },
  [State.B_DIFF_A]: {
    [Trigger.CLICK_N]: State.SET_B,
    [Trigger.CLICK_A]: State.SYMDIFF,
    [Trigger.CLICK_B]: State.NULLSET,
  },
  [State.UNION]: {
    [Trigger.CLICK_N]: State.SYMDIFF,
    [Trigger.CLICK_A]: State.SET_B,
    [Trigger.CLICK_B]: State.SET_A,
  },
  [State.SYMDIFF]: {
    [Trigger.CLICK_N]: State.UNION,
    [Trigger.CLICK_A]: State.B_DIFF_A,
    [Trigger.CLICK_B]: State.A_DIFF_B,
  },
};

/**
 * @type {Transition}
 */
export function transition__ObjectMap(state, trigger) {
  return OBJECT_MAP[state][trigger];
}
