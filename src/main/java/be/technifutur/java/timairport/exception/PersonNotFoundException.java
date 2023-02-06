package be.technifutur.java.timairport.exception;

public class PersonNotFoundException extends RessourceNotFoundException {
    public PersonNotFoundException(){
        super("Yo pa jwenn moun lan");
    }
    public PersonNotFoundException(String s){
        super(s);
    }
    public PersonNotFoundException(Throwable cause){
        super("Yo pa jwenn moun lan", cause);
    }
    public PersonNotFoundException(String s, Throwable cause){
        super(s, cause);
    }
}
