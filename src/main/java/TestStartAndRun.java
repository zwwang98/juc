import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestStartAndRun")
public class TestStartAndRun {
  public static void main(String[] args) {
    callStart();
  }

  /**
   * In this method, we call start() on the thread "t1".
   * This is the correct way to make the thread run.
   */
  public static void callStart() {
    Thread t1 = new Thread("t1") {
      @Override
      public void run() {
        log.debug("t1 thread...");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    t1.start();
    log.debug("main thread...");
  }

  /**
   * In this method, instead of start(), we call run() on the thread "t1".
   * We could see that the run() method is in the main thread, not in thread "t1" as it is supposed to be.
   * In this case, we are treating the thread "t1" as a simple object.
   * To make it acts as a thread, we should call start() on it.
   */
  public static void callRun() {
    Thread t1 = new Thread("t1") {
      @Override
      public void run() {
        log.debug("t1 thread...");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    t1.run();
    log.debug("main thread...");
  }

  /**
   * In this method, we print the state of the new thread "t1" twice.
   * One is before we call start() on thread "t1", the other is after we call start() on thread "t1".
   * We could tell that the state of this thread is changed from "NEW" into "Runnable".
   */
  public static void showThreadState() {
    Thread t1 = new Thread("t1") {
      @Override
      public void run() {
        log.debug("t1 thread...");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    log.debug("state of thread t1: {}", t1.getState());
    t1.start();
    log.debug("start thread t1");
    log.debug("state of thread t1: {}", t1.getState());
  }

  /**
   * Cannot call start() method on the same thread more than once.
   * In this method, we call start() on thread "t1" twice, we get a IllegalThreadStateException.
   */
  public static void cannotCallStartMoreThanOnce() {
    Thread t1 = new Thread("t1") {
      @Override
      public void run() {
        log.debug("t1 thread...");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    t1.start();
    t1.start();
  }
}
