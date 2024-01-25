package org.agileframework.web.result;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.OK;


/**
 * 基于RFC 7807规范的错误码
 *
 * @author xienng
 * @create 2024-01-13 12:19
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Builder
public class RestApiResult<T> {
    private T content;
    private String code;
    private String title;
    @JsonIgnore
    private HttpStatus status;
    @JsonIgnore
    private HttpHeaders headers;
    private ProblemDetail detail;


    /**
     * 成功
     *
     * @param content
     * @param <T>
     * @return
     */
    public static <T> RestApiResult success(T content) {
        return RestApiResult.builder().status(OK)
                .code(OK.getReasonPhrase())
                .title(OK.getReasonPhrase())
                .content(content)
                .build();
    }





    /**
     * 成功
     *
     * @return
     */
    public static RestApiResult success() {
        return success(null);
    }
}
