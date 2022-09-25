import exithandler.SystemExitRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static exithandler.SystemExitMatcher.systemExit;

public class ExitWithCodeTest {

    @Rule public final SystemExitRule systemExitRule = new SystemExitRule();
    @Rule public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void noArgs_exitsNormally() {
        ExitWithCode.main(new String[]{});
        // Expect normal exit, no exception
    }

    @Test
    public void argZero_exitsWithExitCodeZero() {
        exceptionRule.expect(systemExit(0));
        ExitWithCode.main(new String[]{"0"});
    }

    @Test
    public void argNegative_exitsWithNegativeCode() {
        exceptionRule.expect(systemExit(-1));
        ExitWithCode.main(new String[]{"-1"});
    }

    @Test
    public void argPositive_exitsWithPositiveCode() {
        exceptionRule.expect(systemExit(1));
        ExitWithCode.main(new String[]{"1"});
    }

    @Test
    public void nonNumericArg_exitsWithException() {
        exceptionRule.expect(NumberFormatException.class);
        ExitWithCode.main(new String[]{"NaN"});
    }
}