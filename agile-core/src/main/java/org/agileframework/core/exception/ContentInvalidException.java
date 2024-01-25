package org.agileframework.core.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月21日 17:49
 */
public class ContentInvalidException extends CommonException {
    private static final String DEFAULT_TITLE = "文件内容错误";
    private static final HttpStatus DEFAULT_STATUS = BAD_REQUEST;

    public ContentInvalidException(String message) {
        super(DEFAULT_STATUS, DEFAULT_TITLE, message);
    }

    public ContentInvalidException(String message, Throwable cause) {
        super(DEFAULT_STATUS, DEFAULT_TITLE, message, cause);
    }


    public ContentInvalidException(HttpStatus status, String title,
                                   String message) {
        super(status, title, message);
    }
}
