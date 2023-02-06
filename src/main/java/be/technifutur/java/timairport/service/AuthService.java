package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.dto.JWTHolderDTO;
import be.technifutur.java.timairport.model.form.LoginForm;
import be.technifutur.java.timairport.model.form.RegistrationForm;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService {

    void register(RegistrationForm form);

    JWTHolderDTO login(LoginForm form);
}
