package org.agileframework.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月27日 14:57
 */
@Data
@NoArgsConstructor
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

    protected C third;

    public ThreeTuple(A first, B second, C third) {
        super(first, second);
        this.third = third;
    }
}
