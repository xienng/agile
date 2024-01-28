package org.agileframework.web.domain.result;


import lombok.Builder;
import lombok.Getter;


/**
 * Web层返回基类
 *
 * @author xienng
 * @date 2024年01月13日 12:19
 */

@Getter
@Builder
public class ApiResult<T> {
    private T content;
    private String trace;

    /**
     * 成功
     *
     * @param content
     * @param <T>
     * @return
     */
    public static <T> ApiResult success(T content) {
        return ApiResult.builder().content(content).build();
    }

    /**
     * 成功
     *
     * @return
     */
    public static ApiResult success() {
        return success(new Object());
    }
}
