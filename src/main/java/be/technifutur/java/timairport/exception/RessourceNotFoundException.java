package be.technifutur.java.timairport.exception;

public class RessourceNotFoundException extends RuntimeException{
    public RessourceNotFoundException(){
        super("Yo pa jwenn demann lan 🚒");
    }
    public RessourceNotFoundException(String s){
        super(s);
    }
    public RessourceNotFoundException(Throwable cause){
        super("Yo pa jwenn demann lan 🚒", cause);
    }
    public RessourceNotFoundException(String s, Throwable cause){
        super(s, cause);
    }
}
