package exithandler;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * A JUnit Extension that handles calls to {@link System#exit(int)}.
 * If System.exit() is called in code under test, {@link DisallowExitSecurityManager} is thrown.
 * Without this Extension, calls to System.exit() would exit the test execution without completing remaining
 * tests in the suite and without reporting.
 */
public class SystemExitExtension implements BeforeEachCallback, AfterEachCallback {

    private SecurityManager originalSecurityManager;

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        originalSecurityManager = System.getSecurityManager();
        DisallowExitSecurityManager testSecurityManager = new DisallowExitSecurityManager(originalSecurityManager);
        System.setSecurityManager(testSecurityManager);
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        System.setSecurityManager(originalSecurityManager);
    }
}
