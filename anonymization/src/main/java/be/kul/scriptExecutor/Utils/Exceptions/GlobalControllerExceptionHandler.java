package be.kul.scriptExecutor.Utils.Exceptions;


/*
@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
    /*
    @ExceptionHandler(value = {
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

*/
