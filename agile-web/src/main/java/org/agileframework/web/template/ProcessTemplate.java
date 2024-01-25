package org.agileframework.web.template;


import org.agileframework.core.exception.*;
import org.agileframework.web.result.RestApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行模板
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月13日 15:57
 */
public class ProcessTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessTemplate.class);

    /**
     * 执行通用模板
     *
     * @param callback
     * @param <C>
     * @param <T>
     * @return
     */
    public static <C extends Context, T> RestApiResult<T> process(Callback<C, T> callback) {
        try {
            C context = validateParameters(callback);
            validateAuthority(callback, context);
            validateBusinessLogic(callback, context);
            return callback.process(context);
        } catch (Exception ex) {
            if (ex instanceof CommonException) {
                if (ex instanceof ParamInvalidException || ex
                        instanceof NotFoundException) {
                    LOGGER.warn("参数校验未通过", ex);
                } else if (ex instanceof AuthenticationException) {
                    LOGGER.warn("认证失败", ex);
                } else if (ex instanceof PermissionDeniedException) {
                    LOGGER.warn("权限校验未通过", ex);
                } else {
                    LOGGER.error("执行业务流程出错", ex);
                }
                throw ex;
            }
            LOGGER.error("执行业务流程出错", ex);

            //其他异常，统一包装成服务执行异常
            throw new ServiceInternalException("系统出错");

        }
    }

    private static <C extends Context, T> C validateParameters(Callback<C, T> callback) {
        return callback.validateParameters();
    }

    private static <C extends Context, T> void validateAuthority(Callback<C, T> callback, C context) {
        callback.validateAuthority(context);
    }

    private static <C extends Context, T> void validateBusinessLogic(Callback<C, T> callback, C context) {
        callback.validateBusinessLogic(context);
    }
}