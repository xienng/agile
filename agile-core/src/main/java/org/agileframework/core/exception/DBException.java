package org.agileframework.core.exception;

/**
 * 数据库异常
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月28日 18:54
 */
public class DBException extends MiddlewareException {
    public DBException(Throwable cause) {
        super(cause);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
