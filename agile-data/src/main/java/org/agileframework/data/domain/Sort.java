package org.agileframework.data.domain;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xienng
 * @create 2023-12-30 21:52
 */
@AllArgsConstructor
public class Sort {

    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private final List<Order> orders;

    public static Sort by(Direction direction, String... properties) {
        return new Sort(Arrays.stream(properties).map(it -> new Order(direction, it))
                .collect(Collectors.toList()));
    }


    public static Sort by(String... properties) {
        return by(DEFAULT_DIRECTION, properties);
    }

    @AllArgsConstructor
    public static class Order implements Serializable {
        private final Direction direction;
        private final String property;
    }
}