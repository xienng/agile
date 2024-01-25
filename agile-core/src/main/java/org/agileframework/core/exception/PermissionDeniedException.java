package org.agileframework.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * 权限异常
 * <p>
 * 当权限不足时，抛出次异常。默认的Http状态码为403
 * <p>
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月20日 15:57
 */
public class PermissionDeniedException extends CommonException {

    public PermissionDeniedException(String message) {
        super(message);
        this.detail = ProblemDetail.forStatus(FORBIDDEN);
        this.detail.setTitle("无操作权限");
        this.detail.setDetail(message);
    }

    public PermissionDeniedException(HttpStatus status, String title, String message) {
        super(status, title, message);
    }

    public PermissionDeniedException(HttpStatus status, String title) {
        super(title);
        this.detail = ProblemDetail.forStatus(status);
        this.detail.setTitle(title);
    }
}
