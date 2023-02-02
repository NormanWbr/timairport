package be.technifutur.java.timairport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

import java.util.List;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.httpBasic();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /**
         * RequestMatchers:
         *      - * : n'importe quelle valeur dans un segment
         *      - ** : n'importe quelle valeur dans 0 -> N segment(s)
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
         */

        http.authorizeHttpRequests( (authorize) -> {
            authorize
                    .requestMatchers("/plane/all").anonymous()
                    .requestMatchers("/plane/add").authenticated()
                    .requestMatchers("/plane/*/update").hasRole("ADMIN") // .hasAuthority("ROLE_ADMIN")
                    .anyRequest().permitAll();
        });

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        List<UserDetails> userDetails = List.of(
                User.builder()
                        .username("user")
                        .password(encoder().encode("pass"))
                        .roles("USER")
                        .build(),
                User.builder()
                        .username("admin")
                        .password(encoder().encode("pass"))
                        .roles("ADMIN", "USER")
                        .build()
        );
        return new InMemoryUserDetailsManager(userDetails);
    }

}