package fsm;

import static fsm.FsmConstants.*;

public class FsmTable2D {

  private static final int[][] STATE_TABLE = {
      // [CLICK_N] [CLICK_A] [CLICK_B]
      /* NULLSET */ { INTERSECT, SET_A, SET_B },
      /* INTERSECT */ { NULLSET, SET_A, SET_B },
      /* SET_A */ { A_DIFF_B, NULLSET, UNION },
      /* SET_B */ { B_DIFF_A, UNION, NULLSET },
      /* A_DIFF_B */ { SET_A, NULLSET, SYMDIFF },
      /* B_DIFF_A */ { SET_B, SYMDIFF, NULLSET },
      /* UNION */ { SYMDIFF, SET_B, SET_A },
      /* SYMDIFF */ { UNION, B_DIFF_A, A_DIFF_B }
  };

  private int currentState;

  public FsmTable2D() {
    this.currentState = NULLSET;
  }

  public void transition(int trigger) {
    currentState = STATE_TABLE[currentState][trigger];
  }

  public FsmState getState() {
    return FsmState.values()[currentState];
  }
}
