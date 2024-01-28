package org.agileframework.web.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.agileframework.data.domain.Direction;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

/**
 * @author xienng
 * @Description 基础分页请求
 * @create 2023-12-29 16:39
 */
@Data
@Schema(description = "基础分页请求")
public class PageBaseRequest {
    @Schema(description = "分页序号，从1开始", example = "1")
    @Min(1)
    private Integer pageNumber = 1;

    @Schema(description = "分页大小", defaultValue = "20")
    @Min(1)
    private Integer pageSize = 20;

    @Schema(description = "排序字段", example = "createTime", requiredMode = NOT_REQUIRED)
    private String sortField;

    @Schema(description = "排序", example = "ASC", enumAsRef = true, requiredMode = NOT_REQUIRED)
    private Direction direction = Direction.ASC;
}
