package be.technifutur.java.timairport.exception;

public class FlightNotFoundException extends RessourceNotFoundException{
    public FlightNotFoundException(){
        super("Vòl pa jwenn");
    }
    public FlightNotFoundException(String s){
        super(s);
    }
    public FlightNotFoundException(Throwable cause){
        super("Vòl pa jwenn", cause);
    }
    public FlightNotFoundException(String s, Throwable cause){
        super(s, cause);
    }
}
