package be.technifutur.java.timairport.exception;

public class FlightNotFoundException extends RessourceNotFoundException{

    //La requete n'a pas été trouvée
    public FlightNotFoundException(){
        super("Yo pa jwenn demann lan 🚒");
    }

    //La requete n'a pas été trouvée
    public FlightNotFoundException(Throwable cause){
        super("Yo pa jwenn demann lan 🚒", cause);
    }

    //generate flight not found exception
}
