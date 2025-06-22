package com.du.mongoDbCourse.resource.exception;

import com.du.mongoDbCourse.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handleObjectNotFound(ObjectNotFoundException e, HttpServletRequest req) {
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        StandardError error =
                new StandardError(System.currentTimeMillis(), statusCode.value(), "Not Found", e.getMessage(),
                        req.getRequestURI());

        return ResponseEntity.status(statusCode).body(error);
    }

}
