package be.technifutur.java.timairport.exception;

public class NoPilotAvailableException extends RuntimeException {

    //Aucun pilote n'est disponible
    public NoPilotAvailableException(){
        super("Pa gen pilòt disponib 🚒");
    }

    //Aucun pilote n'est disponible
    public NoPilotAvailableException(Throwable cause){
        super("Pa gen pilòt disponib 🚒", cause);
    }
}
