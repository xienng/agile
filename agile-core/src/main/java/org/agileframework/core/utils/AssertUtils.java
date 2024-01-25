package org.agileframework.core.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import org.agileframework.core.exception.CommonException;
import org.agileframework.core.exception.ParamInvalidException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

/**
 * 参数校验助手类
 *
 * @author xienng
 * @date 2024-01-18 21:50
 */
public class AssertUtils {

    public static <T> void validateBean(@Valid T bean) {
        Set<ConstraintViolation<@Valid T>> constraintViolations = Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(bean);
        if (!constraintViolations.isEmpty()) {
            String detail = constraintViolations.iterator().next().getMessage();
            throw new ParamInvalidException(detail);
        }
    }


    /**
     * 断言非空
     *
     * @param value
     * @param message
     */
    public static void isNull(Object value, String message) {
        if (value != null) {
            throw new ParamInvalidException(message);
        }
    }

    /**
     * 断言非空
     *
     * @param value
     * @param message
     */
    public static void notNull(Object value, String message) {
        if (value == null) {
            throw new ParamInvalidException(message);
        }
    }

    /**
     * 断言非空
     *
     * @param value
     * @param supplier
     */
    public static void notNull(Object value, Supplier<? extends CommonException> supplier) {
        if (value == null) {
            throw supplier.get();
        }
    }

    /**
     * 断言不能都为空
     *
     * @param value1
     * @param value2
     * @param message
     */
    public static void notBothNull(Object value1, Object value2, String message) {
        if (value1 == null && value2 == null) {
            throw new ParamInvalidException(message);
        }
    }

    /**
     * 断言第一个参数需要大于第二个参数
     *
     * @param bigger
     * @param smaller
     * @param message
     */
    public static void biggerThan(Long bigger, Long smaller, String message) {
        if (bigger.compareTo(smaller) <= 0) {
            throw new ParamInvalidException(message);
        }
    }

    /**
     * 断言需要为空数组
     *
     * @param collection
     * @param message
     */
    public static void empty(Collection<?> collection, String message) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new ParamInvalidException(message);
        }
    }

    /**
     * 断言结果为true
     *
     * @param value
     * @param message
     */
    public static void isTrue(boolean value, String message) {
        if (!value) {
            throw new ParamInvalidException(message);
        }

    }

    /**
     * 断言结果为true
     *
     * @param value
     * @param supplier
     */
    public static void isTrue(boolean value, Supplier<? extends CommonException> supplier) {
        if (!value) {
            throw supplier.get();
        }
    }
}
