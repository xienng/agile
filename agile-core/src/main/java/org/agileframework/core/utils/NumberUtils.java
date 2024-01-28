package org.agileframework.core.utils;

import jakarta.validation.constraints.NotNull;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月28日 18:28
 */
public class NumberUtils {


    public static boolean bigger(Long first, @NotNull Long second) {
        return first != null && first.compareTo(second) > 0;
    }

    public static boolean biggerThanZero(Long value) {
        return value != null && value.compareTo(0L) > 0;
    }
}
