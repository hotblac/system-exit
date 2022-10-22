import exithandler.SystemExitException;
import exithandler.SystemExitExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SystemExitExtension.class)
class ExitWithCodeTest {

    @Test
    public void noArgs_exitsNormally() {
        ExitWithCode.main(new String[]{});
        // Expect normal exit, no exception
    }

    @Test
    public void argZero_exitsWithExitCodeZero() {
        SystemExitException exitException = assertThrows(SystemExitException.class, () ->
                ExitWithCode.main(new String[]{"0"})
        );
        assertEquals(0, exitException.getStatusCode());
    }

    @Test
    public void argNegative_exitsWithNegativeCode() {
        SystemExitException exitException = assertThrows(SystemExitException.class, () ->
            ExitWithCode.main(new String[]{"-1"})
        );
        assertEquals(-1, exitException.getStatusCode());
    }

    @Test
    public void argPositive_exitsWithPositiveCode() {
        SystemExitException exitException = assertThrows(SystemExitException.class, () ->
            ExitWithCode.main(new String[]{"1"})
        );
        assertEquals(1, exitException.getStatusCode());
    }

    @Test
    public void nonNumericArg_exitsWithException() {
        assertThrows(NumberFormatException.class, () ->
            ExitWithCode.main(new String[]{"NaN"})
        );
    }
}