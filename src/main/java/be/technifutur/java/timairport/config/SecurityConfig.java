package be.technifutur.java.timairport.config;

import be.technifutur.java.timairport.jwt.JwtAuthentificationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.Objects;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthentificationFilter jwtFilter) throws Exception {

        http.csrf().disable();
        http.httpBasic().disable();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        List<HttpMethod> amdinMethods = List.of(HttpMethod.POST, HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE);

        http.authorizeHttpRequests( (authorize) -> {
                    authorize
                            .requestMatchers(HttpMethod.POST, "/auth/*").anonymous()
                            .requestMatchers(request -> request.getRequestURI().length() > 50).hasRole("ADFMIN")
                            .requestMatchers("/plane/all").anonymous()
                            .requestMatchers("/plane/add").authenticated()
                            .requestMatchers("/plane/{id:[0-9]+}/?pdate").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                            .anyRequest().permitAll();
        });

        /*/
            /**
             * Les premiers matchers ont la priorité
             * '.anyrequest', s'il est mis, doit être le dernier matcher
             *
             * RequestMatchers:
             *      Lambda RequestMatchers:
             *      - prend une HttpServletRequest en paramètre, renvoie un boolean
             *
             *      Method:
             *      - une valeur de l'enum HttpMethod
             *
             *      Pattern:
             *      - * : n'importe quelle valeur dans un segment
             *      - ** : n'importe quelle valeur dans 0 -> N segment(s) (seulement en dernière position)
             *      - {i:regex} : n'importe quelle valeur correspondant au paterne regex pour un segment
             *      - ? : remplace une lettre
             *
             * Authorization:
             *      - permitAll()
             *      - denyAll()
             *      - authenticated()
             *      - anonymous()
             *      - hasRole(?)
             *      - hasAnyRole(...?)
             *      - hasAuthority(?)
             *      - hasAnyAuthority(...?)
             *
             *      Une authority c'est un droit sous forme de String
             *      Un Role est une Authority qui commence par 'ROLE_'
             *
            http.authorizeHttpRequests( (authorize) -> {
                authorize
                        // via URI
                        .requestMatchers("/plane/all").anonymous()
                        .requestMatchers("/plane/add").authenticated()
                        .requestMatchers("/plane/{id:[0-9]+}/update").hasRole("ADMIN") // .hasAuthority("ROLE_ADMIN")
                        // via HttpMethod
                        .requestMatchers(HttpMethod.POST).hasRole("USER")
                        // via HttpMethod + Mapping
                        .requestMatchers(HttpMethod.GET, "/plane/*").hasAnyRole("USER", "ADMIN") //.hasAnyAuthority("ROLE_USE", "ROLE_ADMIN")
                        // via RequestMatchers
                        .requestMatchers(request -> request.getRequestURI().length() > 50).hasRole("ADMIN")
                        .anyRequest().permitAll();
            });
        //*/

        return http.build();
    }

//    @Bean //C'est faux Norman
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//        List<UserDetails> userDetails = List.of(
//                User.builder()
//                        .username("user")
//                        .password(encoder().encode("pass"))
//                        .roles("USER")
//                        .build(),
//                User.builder()
//                        .username("admin")
//                        .password(encoder().encode("pass"))
//                        .roles("ADMIN", "USER")
//                        .build()
//        );
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}