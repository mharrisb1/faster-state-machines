package fsm;

public class FsmSwitch {

  private FsmState currentState;

  public FsmSwitch() {
    this.currentState = FsmState.NULLSET;
  }

  public void transition(Trigger trigger) {
    switch (currentState) {
      case NULLSET:
        switch (trigger) {
          case CLICK_N:
            currentState = FsmState.INTERSECT;
            break;
          case CLICK_A:
            currentState = FsmState.SET_A;
            break;
          case CLICK_B:
            currentState = FsmState.SET_B;
            break;
        }
        break;

      case INTERSECT:
        switch (trigger) {
          case CLICK_N:
            currentState = FsmState.NULLSET;
            break;
          case CLICK_A:
            currentState = FsmState.SET_A;
            break;
          case CLICK_B:
            currentState = FsmState.SET_B;
            break;
        }
        break;

      case SET_A:
        switch (trigger) {
          case CLICK_N:
            currentState = FsmState.A_DIFF_B;
            break;
          case CLICK_A:
            currentState = FsmState.NULLSET;
            break;
          case CLICK_B:
            currentState = FsmState.UNION;
            break;
        }
        break;

      case SET_B:
        switch (trigger) {
          case CLICK_N:
            currentState = FsmState.B_DIFF_A;
            break;
          case CLICK_A:
            currentState = FsmState.UNION;
            break;
          case CLICK_B:
            currentState = FsmState.NULLSET;
            break;
        }
        break;

      case A_DIFF_B:
        switch (trigger) {
          case CLICK_N:
            currentState = FsmState.SET_A;
            break;
          case CLICK_A:
            currentState = FsmState.NULLSET;
            break;
          case CLICK_B:
            currentState = FsmState.SYMDIFF;
            break;
        }
        break;

      case B_DIFF_A:
        switch (trigger) {
          case CLICK_N:
            currentState = FsmState.SET_B;
            break;
          case CLICK_A:
            currentState = FsmState.SYMDIFF;
            break;
          case CLICK_B:
            currentState = FsmState.NULLSET;
            break;
        }
        break;

      case UNION:
        switch (trigger) {
          case CLICK_N:
            currentState = FsmState.SYMDIFF;
            break;
          case CLICK_A:
            currentState = FsmState.SET_B;
            break;
          case CLICK_B:
            currentState = FsmState.SET_A;
            break;
        }
        break;

      case SYMDIFF:
        switch (trigger) {
          case CLICK_N:
            currentState = FsmState.UNION;
            break;
          case CLICK_A:
            currentState = FsmState.B_DIFF_A;
            break;
          case CLICK_B:
            currentState = FsmState.A_DIFF_B;
            break;
        }
        break;
    }
  }

  public FsmState getState() {
    return currentState;
  }
}
