package org.shemenev.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = {IncorrectDataHandler.class})
    public ResponseEntity<IncorrectData> handleException(IncorrectDataHandler handler){

        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(handler.getMessage());

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

}
