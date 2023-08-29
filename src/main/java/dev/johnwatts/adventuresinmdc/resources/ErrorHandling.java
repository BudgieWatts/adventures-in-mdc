package dev.johnwatts.adventuresinmdc.resources;

import dev.johnwatts.adventuresinmdc.exceptions.ProcessingError;
import dev.johnwatts.adventuresinmdc.exceptions.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@ControllerAdvice
public class ErrorHandling {
    @ExceptionHandler(ValidationError.class)
    public ResponseEntity<String> handleValidationException(ValidationError validationError) {
        MDC.put("stacktrace", stackTraceToString(validationError));
        log.error(validationError.getMessage());
        return ResponseEntity
                .badRequest()
                .body(validationError.getMessage());
    }

    @ExceptionHandler(ProcessingError.class)
    @ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleProcessingException(ProcessingError processingError) {
        MDC.put("stacktrace", stackTraceToString(processingError));
        log.error(processingError.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(processingError.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleOtherErrors(RuntimeException exception) {
        MDC.put("stacktrace", stackTraceToString(exception));
        log.error(exception.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(exception.getMessage());
    }

    private String stackTraceToString(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
