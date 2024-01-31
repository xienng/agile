package org.agileframework.web.template;


import org.agileframework.core.exception.business.BusinessException;
import org.agileframework.core.exception.system.SystemException;
import org.agileframework.web.domain.result.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.agileframework.core.exception.code.CommonBusinessCodes.INTERNAL_SERVER_ERROR;

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
    public static <C extends Context, T> ApiResult<T> process(Callback<C, T> callback) {
        C context;
        try {
            context = callback.validateParameters();
            callback.validateAuthority(context);
            callback.validateBusinessLogic(context);
        } catch (Exception ex) {
            if (ex instanceof BusinessException) {
                LOGGER.warn("未通过前置校验", ex);
                throw ex;
            }
            if (ex instanceof SystemException) {
                LOGGER.error("系统异常", ex);
            }
            throw new BusinessException(INTERNAL_SERVER_ERROR);
        }
        try {
            return callback.process(context);
        } catch (Exception ex) {
            if (ex instanceof BusinessException) {
                LOGGER.error("未完全生效的前置校验", ex);
                throw ex;
            }
            if (ex instanceof SystemException) {
                LOGGER.error("系统异常", ex);
            }
            throw new BusinessException(INTERNAL_SERVER_ERROR);
        }
    }
}