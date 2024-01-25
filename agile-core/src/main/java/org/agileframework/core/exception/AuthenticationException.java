package org.agileframework.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * 认证失败异常
 *
 * @author xienng
 * @create 2024-01-18 22:29
 */
public class AuthenticationException extends CommonException {

    public AuthenticationException(String message) {
        super(message);
        this.detail = ProblemDetail.forStatus(UNAUTHORIZED);
        this.detail.setTitle("认证失败");
        this.detail.setDetail(message);
    }

    public AuthenticationException(HttpStatus status, String title, String message) {
        super(status, title, message);
    }

    public AuthenticationException(HttpStatus status, String title) {
        super(title);
        this.detail = ProblemDetail.forStatus(status);
        this.detail.setTitle(title);
    }
}
