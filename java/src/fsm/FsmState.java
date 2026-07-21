package fsm;

public enum FsmState {
  NULLSET,
  INTERSECT,
  SET_A,
  SET_B,
  A_DIFF_B,
  B_DIFF_A,
  UNION,
  SYMDIFF
}
