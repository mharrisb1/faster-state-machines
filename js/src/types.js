/**
 * @readonly
 * @enum {number}
 */
export const Trigger = Object.freeze({
  CLICK_N: 0,
  CLICK_A: 1,
  CLICK_B: 2,
});

/**
 * @readonly
 * @enum {number}
 */
export const State = Object.freeze({
  NULLSET: 0,
  INTERSECT: 1,
  SET_A: 2,
  SET_B: 3,
  A_DIFF_B: 4,
  B_DIFF_A: 5,
  UNION: 6,
  SYMDIFF: 7,
});
