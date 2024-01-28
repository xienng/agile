package org.agileframework.core.exception.code;

import org.springframework.http.HttpStatus;

/**
 * 统一的结果码
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月28日 13:47
 */
public interface ErrorCode {
    /**
     * 错误码
     *
     * @return
     */
    String code();

    /**
     * 错误信息
     *
     * @return
     */
    String title();

    /**
     * http状态码
     *
     * @return
     */
    HttpStatus status();
}
