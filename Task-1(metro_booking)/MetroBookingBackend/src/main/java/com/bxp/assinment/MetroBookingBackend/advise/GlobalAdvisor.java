package com.bxp.assinment.MetroBookingBackend.advise;

import com.bxp.assinment.MetroBookingBackend.Dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.BindException;
import java.util.concurrent.TimeoutException;

@RestControllerAdvice
@Slf4j
public class GlobalAdvisor {

    @ExceptionHandler(
            value = {
                    MethodArgumentNotValidException.class,
                    HttpMediaTypeNotSupportedException.class,
                    MissingServletRequestParameterException.class,
                    HttpRequestMethodNotSupportedException.class,
                    BindException.class
            })
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(Exception exception) {
        log.error("Bad request exception: ", exception);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorResponseDto> handleTimeoutException(TimeoutException exception) {
        log.error("Request timed out: ", exception);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage("Request timed out: " + exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleInternalServerException(Exception exception) {
        log.error("Internal server exception: ", exception);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
