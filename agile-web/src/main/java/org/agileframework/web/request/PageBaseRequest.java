package org.agileframework.web.request;

import lombok.Data;
import org.agileframework.data.domain.Direction;

/**
 * @author xienng
 * @Description 基础分页请求
 * @create 2023-12-29 16:39
 */
@Data
public class PageBaseRequest {
    private Integer pageNumber = 1;
    private Integer pageSize = 20;
    private String sortField;
    private Direction direction = Direction.ASC;
}
