package org.agileframework.core.exception;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月28日 19:08
 */
public class MiddlewareException extends RuntimeException {

    public MiddlewareException(Throwable cause) {
        super(cause);
    }

    public MiddlewareException(String message, Throwable cause) {
        super(message, cause);
    }
}
