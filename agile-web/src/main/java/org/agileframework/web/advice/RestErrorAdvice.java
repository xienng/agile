package org.agileframework.web.advice;

import org.agileframework.core.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.net.URISyntaxException;

import static org.agileframework.core.exception.code.CommonBusinessCodes.PARAM_INVALID;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月28日 19:48
 */
@RestControllerAdvice
public class RestErrorAdvice extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(PARAM_INVALID.status(),
                ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        detail.setTitle(PARAM_INVALID.title());
        return new ResponseEntity(detail, headers, PARAM_INVALID.status());
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, HttpHeaders headers, WebRequest request) {
        ProblemDetail detail = ex.getProblem();
        try {
            detail.setInstance(new URI(request.getContextPath()));
        } catch (URISyntaxException e) {
        }
        return new ResponseEntity(detail, headers, detail.getStatus());
    }
}
