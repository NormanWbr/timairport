package be.technifutur.java.timairport.exception;

public class NoPlaneAvailableException extends RessourceNotFoundException{
    public NoPlaneAvailableException(){
        super("Plis avyon disponib");
    }

    public NoPlaneAvailableException(Throwable cause){
        super("Plis avyon disponib", cause);
    }
}
