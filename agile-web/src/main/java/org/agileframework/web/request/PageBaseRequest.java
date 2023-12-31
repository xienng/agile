package org.agileframework.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.agileframework.data.domain.Direction;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

/**
 * @author xienng
 * @Description 基础分页请求
 * @create 2023-12-29 16:39
 */
@Data
@Schema(description = "基础分页请求")
public class PageBaseRequest {
    @Schema(description = "分页序号", example = "1")
    @Min(1)
    private Integer pageNumber = 1;
    @Schema(description = "分页大小", example = "20")
    @Min(1)
    private Integer pageSize = 20;
    @Schema(description = "基础分页请求", example = "create_time", requiredMode = NOT_REQUIRED)
    private List<String> sortFields;
    @Schema(description = "基础分页请求", example = "1", enumAsRef = true)
    private Direction direction = Direction.ASC;
}
