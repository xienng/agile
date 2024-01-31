package org.agileframework.core.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import org.agileframework.core.exception.business.BusinessException;
import org.agileframework.core.exception.business.IllegalArgumentException;
import org.agileframework.core.exception.code.ErrorCode;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

import static org.agileframework.core.exception.code.CommonBusinessCodes.ILLEGAL_ARGUMENT;

/**
 * 参数校验助手类
 *
 * @author xienng
 * @date 2024-01-18 21:50
 */
public class Assert {

    /**
     * 断言结果为true
     *
     * @param expression
     * @param detail
     */
    public static void isTrue(boolean expression, String detail) {
        if (!expression) {
            throw new IllegalArgumentException(detail);
        }
    }

    /**
     * 断言结果为true
     *
     * @param expression
     * @param supplier
     */
    public static void isTrue(boolean expression, Supplier<? extends BusinessException> supplier) {
        if (!expression) {
            throw supplier.get();
        }
    }

    public static <T> void beanValidate(@Valid T bean) {
        Set<ConstraintViolation<@Valid T>> constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(bean);
        if (!constraintViolations.isEmpty()) {
            String detail = constraintViolations.iterator().next().getMessage();
            throw new IllegalArgumentException(detail);
        }
    }


    public static void isNull(Object value) {
        if (value != null) {
            throw new BusinessException(ILLEGAL_ARGUMENT);
        }
    }

    /**
     * 断言非空
     *
     * @param value
     * @param detail
     */
    public static void isNull(Object value, String detail) {
        isNull(value, ILLEGAL_ARGUMENT, detail);
    }

    public static void isNull(Object value, ErrorCode errorCode, String detail) {
        if (value != null) {
            throw new BusinessException(errorCode, detail);
        }
    }

    /**
     * 断言非空
     *
     * @param value
     * @param detail
     */
    public static void notNull(Object value, String detail) {
        notNull(value, ILLEGAL_ARGUMENT, detail);
    }


    /**
     * 断言非空
     *
     * @param value
     * @param errorCode
     * @param detail
     */
    public static void notNull(Object value, ErrorCode errorCode, String detail) {
        if (value == null) {
            throw new BusinessException(errorCode, detail);
        }
    }

    /**
     * 断言非空
     *
     * @param value
     * @param supplier
     */
    public static void notNull(Object value, Supplier<? extends BusinessException> supplier) {
        if (value == null) {
            throw supplier.get();
        }
    }

    /**
     * 断言不能都为空
     *
     * @param first
     * @param second
     * @param detail
     */
    public static void notBothNull(Object first, Object second, String detail) {
        if (first == null && second == null) {
            throw new IllegalArgumentException(detail);
        }
    }

    /**
     * 断言第一个参数需要大于第二个参数
     *
     * @param bigger
     * @param smaller
     * @param detail
     */
    public static void bigger(Long bigger, Long smaller, String detail) {
        if (bigger == null || smaller == null || bigger.compareTo(smaller) <= 0) {
            throw new IllegalArgumentException(detail);
        }
    }

    /**
     * 断言需要为空数组
     *
     * @param collection
     * @param detail
     */
    public static void empty(Collection<?> collection, String detail) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(detail);
        }
    }


}
