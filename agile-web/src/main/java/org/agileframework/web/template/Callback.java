package org.agileframework.web.template;

/**
 * @author xienng
 * @create 2024-01-13 12:19
 */

import org.agileframework.web.domain.result.ApiResult;

/**
 * 执行模板回调
 *
 * @param <C> 上下文类型
 * @param <T> 返回结果类型
 * @author xienng
 * @version 1.0
 * @date 2024年01月16日 15:57
 */
public interface Callback<C extends Context, T> {


    /**
     * 参数校验。返回可以重复使用的上下文，减少数据库查询或者远程调用
     *
     * @return 上下文
     */
    default C validateParameters() {
        return null;
    }

    /**
     * 当前请求是否有权限
     *
     * @param context 上下文
     */
    default void validateAuthority(C context) {

    }

    /**
     * 业务逻辑校验
     *
     * @param context 上下文
     * @return 业务逻辑校验结果，true 代表通过，false 代表未通过
     */
    default void validateBusinessLogic(C context) {

    }

    /**
     * 获取响应结果
     *
     * @param context 上下文
     * @return 响应结果
     */
    ApiResult<T> process(C context);
}
