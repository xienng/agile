package org.agileframework.web.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xienng
 * @Description 基础业务请求。这里的参数和外部的API一一对应
 * @create 2023-12-29 16:34
 */
@Data
@Schema(description = "基础请求")
public class BaseRequest {

    @Schema(description = "签名类型")
    private String signType;

    @Schema(description = "时间戳")
    private Long timeStamp;

    @Schema(description = "请求的签名")
    private String signature;

}
