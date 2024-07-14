package org.zerock.apiserver.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.NoSuchElementException;

//예외처리를
@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> notExist(NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg",e.getMessage()));
    }
    /*localhost:8080/api/todo/list?page=AAA
      MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<?> notExist(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("msg",e.getMessage()));
    }

}
