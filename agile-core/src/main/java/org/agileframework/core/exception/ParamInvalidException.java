package org.agileframework.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 非法参数异常
 *
 * @author xienng
 * @create 2024-01-18 20:49
 */
public class ParamInvalidException extends CommonException {
    private static final String DEFAULT_TITLE = "参数错误";
    private static final HttpStatus DEFAULT_STATUS = BAD_REQUEST;

    public ParamInvalidException(String message) {
        super(message);
        this.detail = ProblemDetail.forStatus(DEFAULT_STATUS);
        this.detail.setTitle(DEFAULT_TITLE);
        this.detail.setDetail(message);
    }

    public ParamInvalidException(String message, Throwable cause) {
        super(DEFAULT_STATUS, DEFAULT_TITLE, message, cause);
    }

    public ParamInvalidException(HttpStatus status,
                                 String title, String message) {
        super(status, title, message);
    }

    public ParamInvalidException(HttpStatus status, String title) {
        super(title);
        this.detail = ProblemDetail.forStatus(status);
        this.detail.setTitle(title);
    }
}
