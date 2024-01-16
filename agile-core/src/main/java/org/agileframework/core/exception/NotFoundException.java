package org.agileframework.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * 资源未找到异常
 *
 * @author xienng
 * @create 2024-01-16 14:03
 */
public class NotFoundException extends AbstractException {


    public NotFoundException(String message) {
        super(message);
        this.detail = ProblemDetail.forStatus(NOT_FOUND);
        this.detail.setTitle("资源未找到");
        this.detail.setDetail(message);
    }

    public NotFoundException(HttpStatus status, String title, String message) {
        super(status, title, message);
    }

    public NotFoundException(HttpStatus status, String title) {
        super(title);
        this.detail = ProblemDetail.forStatus(status);
        this.detail.setTitle(title);
    }
}
