package fsm;

import static fsm.FsmConstants.*;

public class FsmTable1D {

  private static final int NUM_TRIGGERS = 3;

  private static final int[] STATE_TABLE = {
      /* NULLSET */ INTERSECT, SET_A, SET_B,
      /* INTERSECT */ NULLSET, SET_A, SET_B,
      /* SET_A */ A_DIFF_B, NULLSET, UNION,
      /* SET_B */ B_DIFF_A, UNION, NULLSET,
      /* A_DIFF_B */ SET_A, NULLSET, SYMDIFF,
      /* B_DIFF_A */ SET_B, SYMDIFF, NULLSET,
      /* UNION */ SYMDIFF, SET_B, SET_A,
      /* SYMDIFF */ UNION, B_DIFF_A, A_DIFF_B
  };

  private int currentState;

  public FsmTable1D() {
    this.currentState = NULLSET;
  }

  public void transition(int trigger) {
    currentState = STATE_TABLE[(currentState * NUM_TRIGGERS) + trigger];
  }

  public FsmState getState() {
    return FsmState.values()[currentState];
  }
}
