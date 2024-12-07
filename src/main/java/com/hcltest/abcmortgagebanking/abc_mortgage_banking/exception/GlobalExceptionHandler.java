package com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(value=InvalidCustomerException.class)
//    ResponseEntity<ErrorResponse> handleInvalidCustomerException(InvalidCustomerException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage()));
//    }
@ExceptionHandler(InvalidCustomerException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public ResponseEntity<ErrorResponse> handleResourceNotFoundException(InvalidCustomerException ex) {
    ErrorResponse errorResponse = new ErrorResponse() {
        @Override
        public HttpStatusCode getStatusCode() {
            return null;
        }

        @Override
        public ProblemDetail getBody() {
            return null;
        }
    };
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
}

}
