package be.technifutur.java.timairport.exception;

import java.text.Normalizer;

public class FormValidationException extends RuntimeException{
    public FormValidationException(String message){
        super(message);
    }
}
