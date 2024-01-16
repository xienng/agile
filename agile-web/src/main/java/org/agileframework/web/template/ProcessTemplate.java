package org.agileframework.web.template;


import org.agileframework.web.result.RestApiResult;
import org.agileframework.web.validator.ValidateContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author xienng
 * @create 2024-01-13 12:19
 */

public class ProcessTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessTemplate.class);


    public static <C extends ValidateContext, T> RestApiResult<T> process(Callback<C, T> callback) {
        try {
            C context = callback.validate();
            return callback.process(context);
        } catch (Throwable cause) {
            LOGGER.error("系统未知异常", cause);
            throw cause;
        }
    }
}