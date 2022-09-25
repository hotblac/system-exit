package exithandler;

public class SystemExitException extends SecurityException {

    private final int statusCode;

    SystemExitException(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
