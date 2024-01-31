package org.agileframework.data.enums;

import org.agileframework.core.exception.business.IllegalArgumentException;
import org.agileframework.core.utils.Assert;

import java.util.stream.Stream;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月31日 10:19
 */
public interface ValueEnum<T> {


    T getValue();


    static <V, E extends Enum<E> & ValueEnum<V>> E valueToEnum(Class<E> enumType, V value) {
        Assert.notNull(enumType, "枚举类型不能为空");
        Assert.notNull(value, "枚举值不能为空");
        return Stream.of(enumType.getEnumConstants())
                .filter(item -> item.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未知的枚举值: " + value));
    }
}
