package be.technifutur.java.timairport.exception;

public class NoAirportFoundException extends RessourceNotFoundException {
    //Aucun aéroport n'a été trouvé
    public NoAirportFoundException() {
        super("Pa gen aeroport ki jwenn 🚒");
    }

    //Aucun aéroport n'a été trouvé
    public NoAirportFoundException(Throwable cause) {
        super("Pa gen aeroport ki jwenn 🚒", cause);
    }

    //generate no airport found exception
}
