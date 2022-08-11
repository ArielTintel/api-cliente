package br.com.arieltintel.cliente.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends DefaultErrorAttributes {
    private final MessageSource messageSource;
    @ExceptionHandler(ClienteNotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(ClienteNotFoundException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClienteBadRequestException.class)
    public final ResponseEntity<Object> handleAllExceptions(ClienteBadRequestException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EnderecoNotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(EnderecoNotFoundException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EnderecoBadRequestException.class)
    public final ResponseEntity<Object> handleAllExceptions(EnderecoBadRequestException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handle(ApplicationException ex,
                                                      WebRequest request) {
        log.info("Required request body is missing {}", ex.getMessage());
        return ofType(request, ex.getErrorResponse().getHttpStatus(), ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Map<String, Object>> handle(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        List<ConstraintsViolationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ConstraintsViolationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage(), null, validationErrors);
    }

    protected ResponseEntity<Map<String, Object>> ofType(WebRequest request,
                                                         HttpStatus status, ApplicationException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        return ofType(request, status, ex.getLocalizedMessage(locale, messageSource), ex.getErrorResponse().getKey(),
                Collections.EMPTY_LIST);
    }

    private ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message,
                                                       String key, List validationErrors) {
        Map<String, Object> attributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        attributes.put(HttpResponseConstants.STATUS, status.value());
        attributes.put(HttpResponseConstants.ERROR, status);
        attributes.put(HttpResponseConstants.MESSAGE, message);
        attributes.put(HttpResponseConstants.ERRORS, validationErrors);
        attributes.put(HttpResponseConstants.ERROR_KEY, key);
        attributes.put(HttpResponseConstants.PATH, ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(attributes, status);
    }

}
