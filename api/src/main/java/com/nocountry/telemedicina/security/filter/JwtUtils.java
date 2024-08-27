package com.nocountry.telemedicina.security.filter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class JwtUtils {

    private SecretKey getKey() {
        String JWT_SECRET = "fGgqglUxy7KJoMO+b06brLZnK6qbzxDFpzrqE1OSHGI=";
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }

    public Date expiration() {
        return new Date(System.currentTimeMillis() + 1000 * 60 * 60);
    }

    public String createToken(String username, Map<String, Object> claims) {

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiration())
                .signWith(getKey())
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return createToken(userDetails.getUsername(), claims);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claimis = getAllClaims(token);
        return claimsResolver.apply(claimis);

    }

    private Claims getAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (MalformedJwtException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    public String createTokenConfirmation(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(getKey())
                .compact();
    }
}
