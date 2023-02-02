package be.technifutur.java.timairport.exception;

public class PersonNotFoundException extends RessourceNotFoundException {

        //La personne n'a pas été trouvée
        public PersonNotFoundException(){
            super("Yo pa jwenn moun lan 🚒");
        }

        //La personne n'a pas été trouvée
        public PersonNotFoundException(Throwable cause){
            super("Yo pa jwenn moun lan 🚒", cause);
        }

}
