package fsm;

public class FsmGof {
  public interface IStateNode {
    IStateNode clickN();

    IStateNode clickA();

    IStateNode clickB();

    FsmState getState();
  }

  public static class NullSetState implements IStateNode {
    @Override
    public IStateNode clickN() {
      return new IntersectState();
    }

    @Override
    public IStateNode clickA() {
      return new SetAState();
    }

    @Override
    public IStateNode clickB() {
      return new SetBState();
    }

    @Override
    public FsmState getState() {
      return FsmState.NULLSET;
    }
  }

  public static class IntersectState implements IStateNode {
    @Override
    public IStateNode clickN() {
      return new NullSetState();
    }

    @Override
    public IStateNode clickA() {
      return new SetAState();
    }

    @Override
    public IStateNode clickB() {
      return new SetBState();
    }

    @Override
    public FsmState getState() {
      return FsmState.INTERSECT;
    }
  }

  public static class SetAState implements IStateNode {
    @Override
    public IStateNode clickN() {
      return new ADiffBState();
    }

    @Override
    public IStateNode clickA() {
      return new NullSetState();
    }

    @Override
    public IStateNode clickB() {
      return new UnionState();
    }

    @Override
    public FsmState getState() {
      return FsmState.SET_A;
    }
  }

  public static class SetBState implements IStateNode {
    @Override
    public IStateNode clickN() {
      return new BDiffAState();
    }

    @Override
    public IStateNode clickA() {
      return new UnionState();
    }

    @Override
    public IStateNode clickB() {
      return new NullSetState();
    }

    @Override
    public FsmState getState() {
      return FsmState.SET_B;
    }
  }

  public static class ADiffBState implements IStateNode {
    @Override
    public IStateNode clickN() {
      return new SetAState();
    }

    @Override
    public IStateNode clickA() {
      return new NullSetState();
    }

    @Override
    public IStateNode clickB() {
      return new SymDiffState();
    }

    @Override
    public FsmState getState() {
      return FsmState.A_DIFF_B;
    }
  }

  public static class BDiffAState implements IStateNode {
    @Override
    public IStateNode clickN() {
      return new SetBState();
    }

    @Override
    public IStateNode clickA() {
      return new SymDiffState();
    }

    @Override
    public IStateNode clickB() {
      return new NullSetState();
    }

    @Override
    public FsmState getState() {
      return FsmState.B_DIFF_A;
    }
  }

  public static class UnionState implements IStateNode {
    @Override
    public IStateNode clickN() {
      return new SymDiffState();
    }

    @Override
    public IStateNode clickA() {
      return new SetBState();
    }

    @Override
    public IStateNode clickB() {
      return new SetAState();
    }

    @Override
    public FsmState getState() {
      return FsmState.UNION;
    }
  }

  public static class SymDiffState implements IStateNode {
    @Override
    public IStateNode clickN() {
      return new UnionState();
    }

    @Override
    public IStateNode clickA() {
      return new BDiffAState();
    }

    @Override
    public IStateNode clickB() {
      return new ADiffBState();
    }

    @Override
    public FsmState getState() {
      return FsmState.SYMDIFF;
    }
  }

  private IStateNode currentState;

  public FsmGof() {
    this.currentState = new NullSetState();
  }

  public void transition(Trigger trigger) {
    switch (trigger) {
      case CLICK_A:
        currentState = currentState.clickA();
        break;
      case CLICK_B:
        currentState = currentState.clickB();
        break;
      case CLICK_N:
        currentState = currentState.clickN();
        break;
    }
  }

  public FsmState getState() {
    return this.currentState.getState();
  }
}
