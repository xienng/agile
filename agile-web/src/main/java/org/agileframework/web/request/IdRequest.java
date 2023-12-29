package org.agileframework.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xienng
 * @Description 基础分页请求
 * @create 2023-12-29 16:39
 */
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class IdRequest<T> extends BaseRequest {
    private T id;
}
