package org.agileframework.core.exception;

/**
 * 远程调用异常
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月28日 19:08
 */
public class RemoteCallException extends RuntimeException {

    public RemoteCallException(Throwable cause) {
        super(cause);
    }

    public RemoteCallException(String message, Throwable cause) {
        super(message, cause);
    }
}
