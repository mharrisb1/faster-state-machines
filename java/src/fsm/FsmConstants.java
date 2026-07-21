package fsm;

public final class FsmConstants {
  // Triggers
  public static final int CLICK_N = 0;
  public static final int CLICK_A = 1;
  public static final int CLICK_B = 2;

  // States
  public static final int NULLSET = 0;
  public static final int INTERSECT = 1;
  public static final int SET_A = 2;
  public static final int SET_B = 3;
  public static final int A_DIFF_B = 4;
  public static final int B_DIFF_A = 5;
  public static final int UNION = 6;
  public static final int SYMDIFF = 7;

  private FsmConstants() {
  } // Prevent instantiation
}
