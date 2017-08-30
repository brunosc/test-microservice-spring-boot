package com.example.demo.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.domain.ErrorDetail;
import com.example.demo.exception.RecordNotFoundException;

@ControllerAdvice
public class ResourceStateExceptionHandler {

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleRegistroNaoEncontradoException(RecordNotFoundException e, HttpServletRequest request) {
		
		ErrorDetail error = new ErrorDetail();
		
		error.setStatus(404);
		error.setTitle("Record not found");
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
}
