package org.agileframework.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author xienng
 * @create 2024-01-19 18:17
 */
public class ServiceInternalException extends CommonException {

    public ServiceInternalException(String message) {
        super(message);
        this.detail = ProblemDetail.forStatus(INTERNAL_SERVER_ERROR);
        this.detail.setTitle("未知异常");
        this.detail.setDetail(message);
    }

    public ServiceInternalException(HttpStatus status,
                                    String title, String message) {
        super(status, title, message);
    }

    public ServiceInternalException(HttpStatus status, String title) {
        super(title);
        this.detail = ProblemDetail.forStatus(status);
        this.detail.setTitle(title);
    }
}
