package org.agileframework.core.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.agileframework.core.exception.code.ErrorCode;
import org.springframework.http.ProblemDetail;

/**
 * @author xienng
 * @create 2024-01-15 17:36
 */
@NoArgsConstructor
@Data
public class BusinessException extends RuntimeException {
    protected ProblemDetail problem;

    /**
     * @param code
     */
    public BusinessException(ErrorCode code) {
        super(code.title());
        this.problem = ProblemDetail.forStatus(code.status());
        this.problem.setTitle(code.title());
    }

    public BusinessException(ProblemDetail problem) {
        super(problem.getDetail());
        this.problem = problem;
    }


    public BusinessException(ErrorCode code, String detail) {
        super(detail);
        this.problem = ProblemDetail.forStatus(code.status());
        this.problem.setTitle(code.title());
        this.problem.setDetail(detail);
    }

    /**
     * @param code
     * @param cause
     */
    public BusinessException(ErrorCode code, Throwable cause) {
        super(cause.getMessage(), cause);
        this.problem = ProblemDetail.forStatus(code.status());
        this.problem.setTitle(code.title());
        this.problem.setDetail(cause.getMessage());
    }


    public BusinessException(ErrorCode code, String detail, Throwable cause) {
        super(cause);
        this.problem = ProblemDetail.forStatus(code.status());
        this.problem.setTitle(code.title());
        this.problem.setDetail(detail);
    }
}
