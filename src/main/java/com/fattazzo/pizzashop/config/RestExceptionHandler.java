package com.fattazzo.pizzashop.config;

import static java.util.stream.Collectors.groupingBy;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fattazzo.pizzashop.exception.security.ExpiredTokenException;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.exception.security.UserNotActiveException;
import com.fattazzo.pizzashop.model.api.ConstraintError;
import com.fattazzo.pizzashop.model.api.ErrorData;
import com.fattazzo.pizzashop.model.api.ErrorInternal;
import com.fattazzo.pizzashop.model.api.ErrorResponse;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	protected final static Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<?> handleAccessDeniedException(Exception ex, WebRequest request) {

		final ErrorInternal internal = new ErrorInternal().exception(ex.getClass().getName()).stack(ex.getMessage());
		final ErrorData errorData = new ErrorData().internal(internal)
				.userMessage(localeUtilsMessage.getMessage("AccessDeniedException.detail", null, request))
				.userTitle(localeUtilsMessage.getMessage("AccessDeniedException.title", null, request));

		final ErrorResponse errorResponse = new ErrorResponse().error(errorData);

		return new ResponseEntity<>(errorResponse, null, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(ExpiredTokenException.class)
	public ResponseEntity<?> handleExpiredTokenException(ExpiredTokenException rnfe, HttpServletRequest request) {

		final ErrorInternal internal = new ErrorInternal().exception(rnfe.getClass().getName())
				.stack(rnfe.getMessage());
		final ErrorData errorData = new ErrorData().internal(internal)
				.userMessage(localeUtilsMessage.getMessage("ExpiredTokenException.detail", null, request))
				.userTitle(localeUtilsMessage.getMessage("ExpiredTokenException.title", null, request));

		final ErrorResponse errorResponse = new ErrorResponse().error(errorData);

		return new ResponseEntity<>(errorResponse, null, HttpStatus.ACCEPTED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		final ErrorInternal internal = new ErrorInternal().exception(ex.getClass().getName()).stack(ex.getMessage());
		final ErrorData errorData = new ErrorData().internal(internal)
				.userMessage(localeUtilsMessage.getMessage("HttpMessageNotReadableException.detail", null, request))
				.userTitle(localeUtilsMessage.getMessage("HttpMessageNotReadableException.title", null, request));

		final ErrorResponse errorResponse = new ErrorResponse().error(errorData);

		return handleExceptionInternal(ex, errorResponse, headers, status, request);
	}

	@ExceptionHandler(NoSuchEntityException.class)
	public ResponseEntity<?> handleInvalidGrantException(NoSuchEntityException rnfe, HttpServletRequest request) {

		final ErrorInternal internal = new ErrorInternal().exception(rnfe.getClass().getName())
				.stack(rnfe.getMessage());
		final ErrorData errorData = new ErrorData().internal(internal)
				.userMessage(localeUtilsMessage.getMessage("NoSuchEntityException.detail", null, request))
				.userTitle(localeUtilsMessage.getMessage("NoSuchEntityException.title", null, request));

		final ErrorResponse errorResponse = new ErrorResponse().error(errorData);

		return new ResponseEntity<>(errorResponse, null, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// Create ValidationError instances
		final List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
		List<ConstraintError> constraintsViolated = new ArrayList<>();
		if (fieldErrors != null) {
			final Map<String, List<FieldError>> errorsMap = fieldErrors.stream()
					.collect(groupingBy(FieldError::getField));
			constraintsViolated = errorsMap.keySet().stream().map((k) -> {
				return new ConstraintError().fieldName(k).constraintsNotRespected(errorsMap.get(k).stream().map((v) -> {
					return v.getDefaultMessage();
				}).collect(Collectors.toList()));
			}).collect(Collectors.toList());
		}

		final ErrorData errorData = new ErrorData()
				.userTitle(localeUtilsMessage.getMessage("ValidationFailed.title", null, request))
				.userMessage(localeUtilsMessage.getMessage("ValidationFailed.detail", null, request))
				.constraintErrors(constraintsViolated);

		final ErrorResponse errorResponse = new ErrorResponse().error(errorData);

		return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ RestException.class })
	public ResponseEntity<Object> handleRestApiException(final RestException ex, final WebRequest request) {
		log.error(ex.getMessage(), ex);
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		final String stack = sw.toString().substring(0, 300);
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final ErrorInternal internal = new ErrorInternal().exception(ex.getClass().getName()).stack(stack);
		final ErrorData errorData = new ErrorData().internal(internal).userMessage(ex.getDetail())
				.userTitle(ex.getTitle());

		final ErrorResponse errorResponse = new ErrorResponse().error(errorData);

		return new ResponseEntity<>(errorResponse, headers, ex.getStatus());
	}

	@ExceptionHandler(UserNotActiveException.class)
	public ResponseEntity<?> handleUserNotActiveException(UserNotActiveException rnfe, HttpServletRequest request) {

		final ErrorInternal internal = new ErrorInternal().exception(rnfe.getClass().getName())
				.stack(rnfe.getMessage());
		final ErrorData errorData = new ErrorData().internal(internal)
				.userMessage(localeUtilsMessage.getMessage("UserNotActiveException.detail", null, request))
				.userTitle(localeUtilsMessage.getMessage("UserNotActiveException.title", null, request));

		final ErrorResponse errorResponse = new ErrorResponse().error(errorData);

		return new ResponseEntity<>(errorResponse, null, HttpStatus.FORBIDDEN);
	}

}
