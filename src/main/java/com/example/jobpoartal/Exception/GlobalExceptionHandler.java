package com.example.jobpoartal.Exception;

import com.example.jobpoartal.Entity.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> store(MethodArgumentNotValidException ex){
        Map<String ,String>data=new HashMap<>();
      ex.getBindingResult().getFieldErrors().forEach(errors->
              data.put(errors.getField(),errors.getDefaultMessage()));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
    }

@ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>>cep(Exception ex){
    Map<String,Object>cal=new HashMap<>();
    cal.put("timestamp", LocalDateTime.now());
    cal.put("error", ex.getMessage());cal.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cal);

    }

}
