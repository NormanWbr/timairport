package be.technifutur.java.timairport.exception;

public class RessourceNotFoundException extends RuntimeException{

    //La requete n'a pas été trouvée
    public RessourceNotFoundException(){
        super("Yo pa jwenn demann lan");
    }

    //La requete n'a pas été trouvée
    public RessourceNotFoundException(Throwable cause){
        super("Yo pa jwenn demann lan", cause);
    }
}
