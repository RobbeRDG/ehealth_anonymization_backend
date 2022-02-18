package be.kul.useraccess.Utils.Exceptions;

import be.kul.useraccess.Utils.Exceptions.ExceptionClasses.ScriptFileToStringConversionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            ScriptFileToStringConversionException.class,
            ScriptParsingException.class,
            MultipartException.class
    })
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
            String bodyOfResponse = ex.getLocalizedMessage();

            //Create the error message
            ErrorMessage errorMessage = new ErrorMessage(bodyOfResponse);

            //Set the headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Exception-Type", String.valueOf(ex.getClass()));

            log.warn("Global exception handler handled: " + ex.getLocalizedMessage());

            return handleExceptionInternal(
                ex,
                errorMessage,
                headers,
                HttpStatus.BAD_REQUEST, request
            );
    }

    @ExceptionHandler(value = {}) //Handle all unexpected exceptions
    protected ResponseEntity<Object> handleSomethingWentWrong(RuntimeException ex, WebRequest request) {
            String bodyOfResponse = ex.getLocalizedMessage();

            //Create the error message
            ErrorMessage errorMessage = new ErrorMessage(bodyOfResponse);

            //Set the headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Exception-Type", String.valueOf(ex.getClass()));


            log.error("Handled unexpected error: " + ex.getLocalizedMessage());
            ex.printStackTrace();

            //Return to client
            return handleExceptionInternal(
                ex,
                errorMessage,
                headers,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
            );
    }
}
