package com.pry20220262.augmentedanatomy.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.util.Collections.emptyList;

public class JwtUtil {
    static void addAuthentication(HttpServletResponse res, String username) {

        String token = Jwts.builder()
                .setSubject(username)
                .claim("email", username)
                .signWith(SignatureAlgorithm.HS512, "AugmentedAnatomySecret")
                .compact();

        res.addHeader("Authorization", "Bearer " + token);
    }

    static Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey("AugmentedAnatomySecret")
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}
