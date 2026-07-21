package fsm;

public class Main {
  public static final Trigger[] TRIGGER_SEQ = {
      Trigger.CLICK_A,
      Trigger.CLICK_A,
      Trigger.CLICK_B,
      Trigger.CLICK_N,
      Trigger.CLICK_B,
      Trigger.CLICK_A,
      Trigger.CLICK_N
  };

  public static void main(String[] args) {
    // GoF FSM
    System.out.println("\nFSM GoF");
    FsmGof fsmGof = new FsmGof();
    System.out.println("Initial state:\t" + fsmGof.getState());
    for (Trigger trigger : TRIGGER_SEQ) {
      fsmGof.transition(trigger);
    }
    System.out.println("Final state:\t" + fsmGof.getState());
    System.out.println();

    // Switch
    System.out.println("FSM Switch");
    FsmSwitch fsmSwtich = new FsmSwitch();
    System.out.println("Initial state:\t" + fsmSwtich.getState());
    for (Trigger trigger : TRIGGER_SEQ) {
      fsmSwtich.transition(trigger);
    }
    System.out.println("Final state:\t" + fsmSwtich.getState());
    System.out.println();

    // 2D State Table
    System.out.println("FSM Table 2D");
    FsmTable2D fsmTable2d = new FsmTable2D();
    System.out.println("Initial state:\t" + fsmTable2d.getState());
    for (Trigger trigger : TRIGGER_SEQ) {
      fsmTable2d.transition(trigger.ordinal());
    }
    System.out.println("Final state:\t" + fsmTable2d.getState());
    System.out.println();

    // 1D State Table
    System.out.println("FSM Table 1D");
    FsmTable1D fsmTable1d = new FsmTable1D();
    System.out.println("Initial state:\t" + fsmTable1d.getState());
    for (Trigger trigger : TRIGGER_SEQ) {
      fsmTable1d.transition(trigger.ordinal());
    }
    System.out.println("Final state:\t" + fsmTable1d.getState());
    System.out.println();
  }
}
