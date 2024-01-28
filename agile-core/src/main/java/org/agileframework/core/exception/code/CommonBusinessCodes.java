package org.agileframework.core.exception.code;

import org.springframework.http.HttpStatus;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月28日 14:55
 */
public enum CommonBusinessCodes implements ErrorCode {
    PARAM_INVALID("参数错误", HttpStatus.BAD_REQUEST),

    UNAUTHORIZED("未认证", HttpStatus.UNAUTHORIZED),

    AUTHENTICATION_FAILED("认证失败", HttpStatus.UNAUTHORIZED),

    PERMISSION_DENIED("无操作权限", HttpStatus.FORBIDDEN),

    NOT_FOUND("资源未找到", HttpStatus.NOT_FOUND),

    REMOTE_CONTENT_INVALID("远程内容错误", HttpStatus.BAD_REQUEST),

    SERVICE_INTERNAL_ERROR("服务器内部异常", HttpStatus.INTERNAL_SERVER_ERROR);
    private final HttpStatus status;
    private final String message;

    CommonBusinessCodes(String message, HttpStatus status) {
        this.message = message;
        this.status = status;

    }


    @Override
    public String code() {
        return name();
    }

    @Override
    public String title() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return status;
    }
}
