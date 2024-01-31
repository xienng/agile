package org.agileframework.core.exception.business;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.agileframework.core.exception.code.ErrorCode;
import org.springframework.http.ProblemDetail;

import static org.springframework.http.ProblemDetail.forStatus;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月28日 14:55
 */
@NoArgsConstructor
@Data
public class BusinessException extends RuntimeException {
    protected ProblemDetail problem;
    protected ErrorCode code;

    /**
     * @param code
     */
    public BusinessException(ErrorCode code) {
        super(code.title());
        this.code = code;
        this.problem = forStatus(code.status());
        this.problem.setTitle(code.title());
    }

    public BusinessException(ProblemDetail problem) {
        super(problem.getDetail());
        this.problem = problem;
    }


    public BusinessException(ErrorCode code, String detail) {
        super(detail);
        this.code = code;
        this.problem = forStatus(code.status());
        this.problem.setTitle(code.title());
        this.problem.setDetail(detail);
    }

    /**
     * @param code
     * @param cause
     */
    public BusinessException(ErrorCode code, Throwable cause) {
        super(cause.getMessage(), cause);
        this.code = code;
        this.problem = forStatus(code.status());
        this.problem.setTitle(code.title());
        this.problem.setDetail(cause.getMessage());
    }


    public BusinessException(ErrorCode code, String detail, Throwable cause) {
        super(cause);
        this.code = code;
        this.problem = forStatus(code.status());
        this.problem.setTitle(code.title());
        this.problem.setDetail(detail);
    }
}
