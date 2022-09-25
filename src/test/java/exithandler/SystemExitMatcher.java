package exithandler;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Hamcrest matcher for checking System.exit() status code.
 * Use with {@link SystemExitRule} to handle System.exit() events and check the resulting {@link SystemExitException}
 */
public class SystemExitMatcher extends TypeSafeDiagnosingMatcher<SystemExitException> {

    private final int expectedStatusCode;

    private SystemExitMatcher(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }

    public static SystemExitMatcher systemExit(int expectedStatusCode) {
        return new SystemExitMatcher(expectedStatusCode);
    }

    @Override
    protected boolean matchesSafely(SystemExitException e, Description description) {
        description.appendText("was SystemExitException with statusCode ").appendValue(e.getStatusCode());
        return e.getStatusCode() == expectedStatusCode;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("SystemExitException with statusCode ").appendValue(expectedStatusCode);
    }
}
