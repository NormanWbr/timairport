package be.technifutur.java.timairport.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtProvider {

    private final UserDetailsService userDetailsService;

    private long sexpiresAt = 86400000;

    public JwtProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(Authentication auth) {

        Date issuedAt = new Date();
        Date expiresAt = new Date(System.currentTimeMillis() + sexpiresAt);

        try {
            return JWT.create()
                    .withSubject(auth.getName())
                    .withExpiresAt(expiresAt)
                    .withIssuedAt(issuedAt)
                    .sign(Algorithm.HMAC512("s3cr3t"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("internal error", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512("s3cr3t"))
                    .acceptExpiresAt(sexpiresAt)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

//            String subject = decodedJWT.getSubject();
//            Claim claim = decodedJWT.getClaim("claim_name");
//            return subject.length() > 50 && claim.asBoolean();

            return true;
        } catch (JWTVerificationException e) {
            throw new RuntimeException("internal error", e);
        } catch (UnsupportedEncodingException ex) {
        throw new RuntimeException("internal error", ex);
        }
    }

    public Authentication generateAuthentication(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String username = jwt.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new AnonymousAuthenticationToken(userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }

}
