package org.agileframework.data.domain;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author xienng
 * @create 2023-12-29 20:17
 */
@Schema(enumAsRef = true, description =
        "* `ASC` - 升序\n" +
                "* `DESC` - 降序\n")
public enum Direction {
    ASC, DESC;
}
