package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.form.DemoForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    //Récupérable depuis la requête HTTP:
    // - URL/URI: chemin
    //      - variable de chemin: segment de l'URI dont on a laissé le choix de la valeur
    //      - paramètre: (ex: http://localhost:8080/machin/truc.param=value
    // - method: type de requête envoyée, détermine l'action attendue
    //      - GET   : récupérer une/pls ressource.s
    //      - POST  : envoyer/créer une ressource
    //      - PUT   : remplacer une ressource (modifier intégralement)
    //      - PATCH : modifier partiellement une ressource
    //      - DELETE: supprimer une ressource
    // - headers: meta-données sur la requête (MultiValueMap: 1 key potentiellement N valeurs)
    // - body: contenu de la requête

    //
    // Dans la réponse HTTP :
    // - status : code représentant succès/échec de la requête
    // - headers: méta-données de la réponse;
    // - body   : contenue de la réponse

    // CODE 100: information
    // CODE 200: succès de la requête
    // CODE 300: redirection
    // CODE 400: client
    // CODE 500: serveur

    // Ce qui se trouve dans l'URL
    @GetMapping("/url/{var:[A-Z]{1,3}]}")
    public void displayUrlInfo(@PathVariable String var, @RequestParam int param) {
        System.out.println(var); //variable de chemin {var}
        System.out.println(param); //variable du paramètre param
    }

    @PostMapping("/body")
    public void displayBody(@RequestBody @Valid DemoForm body) {
        System.out.println(body);
    }

    @GetMapping("/other")
    public void displayRequest(HttpServletRequest request){
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());;
        request.getMethod();
    }

    @GetMapping("/header")
    public void displayHeaders(@RequestHeader("accept") String accept) {
        System.out.println(accept);
    }

    @GetMapping("/param/all")
    public void displayAllParams(@RequestParam Map<String, String> params) {
        params.forEach(
                (key, value) -> System.out.printf("%s : %s\n", key, value)
        );
    }

    @GetMapping("/header/all")
    public void displayAllheaders(@RequestHeader HttpHeaders headers){
        headers.forEach(
                (key, value) -> System.out.printf("%s : %s\n", key, value)
        );
    }

}
