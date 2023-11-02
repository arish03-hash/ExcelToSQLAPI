package com.api.exceltosql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadException{
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> hanldeMaxSizeException(MaxUploadSizeExceededException exc){
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File is too large");
	}
}
