package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.exception.RessourceNotFoundException;
import be.technifutur.java.timairport.model.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleFlightNotFoundException(RessourceNotFoundException e, HttpServletRequest request){
        ErrorDTO errorDTO = new ErrorDTO(
                request.getMethod(),
                request.getContextPath(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDTO);
    }
}
