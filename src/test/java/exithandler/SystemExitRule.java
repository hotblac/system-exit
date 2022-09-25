package exithandler;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * A JUnit 4 {@link org.junit.Rule} that handles calls to {@link System#exit(int)}.
 * If System.exit() is called in code under test, {@link DisallowExitSecurityManager} is thrown.
 * Without this Rule, calls to System.exit() would exit the test execution without completing remaining
 * tests in the suite and without reporting.
 */
public class SystemExitRule extends TestWatcher {

    private SecurityManager originalSecurityManager;

    @Override
    protected void starting(Description description) {
        originalSecurityManager = System.getSecurityManager();
        DisallowExitSecurityManager testSecurityManager = new DisallowExitSecurityManager(originalSecurityManager);
        System.setSecurityManager(testSecurityManager);
    }

    @Override
    protected void finished(Description description) {
        System.setSecurityManager(originalSecurityManager);
    }
}
