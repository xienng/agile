package org.agileframework.core.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

/**
 * @author xienng
 * @create 2024-01-15 17:36
 */
@NoArgsConstructor
public abstract class AbstractException extends RuntimeException {

    protected ProblemDetail detail;

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(ProblemDetail detail) {
        super(detail.getDetail());
        this.detail = detail;
    }

    public AbstractException(HttpStatus status, String title, String message) {
        super(message);
        this.detail = ProblemDetail.forStatusAndDetail(status, message);
        this.detail.setTitle(title);
    }

    public AbstractException(HttpStatus status, String title) {
        super(title);
        this.detail = ProblemDetail.forStatus(status);
        this.detail.setTitle(title);
    }
}
