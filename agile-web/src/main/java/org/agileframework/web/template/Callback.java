package org.agileframework.web.template;

/**
 * @author xienng
 * @create 2024-01-13 12:19
 */

import org.agileframework.web.result.RestApiResult;
import org.agileframework.web.validator.ValidateContext;

/**
 * 业务执行模板
 *
 * @param <R> 请求
 * @param <T> 响应结果
 * @author jianxing.xjx
 */
public interface Callback<C extends ValidateContext, T> {


    /**
     * 验证请求，返回可以重复使用的上下文，减少数据库查询或者远程调用
     *
     * @return
     */
    default C validate() {
        return null;
    }


    /**
     * 获取响应结果
     *
     * @param context
     * @return
     */
    RestApiResult<T> process(C context);
}
