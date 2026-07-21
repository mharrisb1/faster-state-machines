package fsm;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 5, time = 20)
@Fork(1)
public class FsmBenchmark {
  private static final int ITERATIONS = 10_000_000;
  private Trigger[] triggers;
  private int[] primitiveTriggers;

  private FsmGof fsmGof;
  private FsmSwitch fsmSwitch;
  private FsmTable2D fsmTable2D;
  private FsmTable1D fsmTable1D;

  @Setup(Level.Trial)
  public void setup() {
    primitiveTriggers = new int[ITERATIONS];

    triggers = new Trigger[ITERATIONS];
    Trigger[] values = Trigger.values();

    Random rand = new Random(1996);

    for (int i = 0; i < ITERATIONS; i++) {
      triggers[i] = values[rand.nextInt(values.length)];
    }

    for (int i = 0; i < ITERATIONS; i++) {
      primitiveTriggers[i] = triggers[i].ordinal();
    }

    fsmGof = new FsmGof();
    fsmSwitch = new FsmSwitch();
    fsmTable2D = new FsmTable2D();
    fsmTable1D = new FsmTable1D();
  }

  @Benchmark
  @OperationsPerInvocation(ITERATIONS)
  public FsmState measureGof() {
    for (int i = 0; i < ITERATIONS; i++) {
      fsmGof.transition(triggers[i]);
    }
    return fsmGof.getState();
  }

  @Benchmark
  @OperationsPerInvocation(ITERATIONS)
  public FsmState measureSwitch() {
    for (int i = 0; i < ITERATIONS; i++) {
      fsmSwitch.transition(triggers[i]);
    }
    return fsmSwitch.getState();
  }

  @Benchmark
  @OperationsPerInvocation(ITERATIONS)
  public FsmState measureTable2D() {
    for (int i = 0; i < ITERATIONS; i++) {
      fsmTable2D.transition(primitiveTriggers[i]);
    }
    return fsmTable2D.getState();
  }

  @Benchmark
  @OperationsPerInvocation(ITERATIONS)
  public FsmState measureTable1D() {
    for (int i = 0; i < ITERATIONS; i++) {
      fsmTable1D.transition(primitiveTriggers[i]);
    }
    return fsmTable1D.getState();
  }
}
