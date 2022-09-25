package exithandler;

import java.security.Permission;

/**
 * Disallow calls to System.exit().
 * When System.exit() is attempted, throw {@link DisallowExitSecurityManager}.
 */
class DisallowExitSecurityManager extends SecurityManager {

    private final SecurityManager delegatedSecurityManager;

    /**
     * Construct a SecurityManager that prevents system exit.
     * All other security checks are delegated to the provided SecurityManager.
     * @param originalSecurityManager SecurityManager to delegate checks to. If this is null, no security checks are performed.
     */
    public DisallowExitSecurityManager(SecurityManager originalSecurityManager) {
        this.delegatedSecurityManager = originalSecurityManager;
    }

    @Override
    public void checkExit(int status) {
        throw new SystemExitException(status);
    }

    @Override
    public void checkPermission(Permission perm) {
        // If there's no SecurityManager to delegate to, allow all
        if (delegatedSecurityManager == null) return;

        // Delegate permission check
        delegatedSecurityManager.checkPermission(perm);
    }

}
