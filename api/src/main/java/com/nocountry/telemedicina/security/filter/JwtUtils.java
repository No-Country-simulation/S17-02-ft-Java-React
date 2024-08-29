package com.nocountry.telemedicina.security.filter;
import com.nocountry.telemedicina.dto.response.ErrorResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.mapstruct.Javadoc;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * The type Jwt utils.
 */
@Configuration
public class JwtUtils {

    private SecretKey getKey() {
        String JWT_SECRET = "fGgqglUxy7KJoMO+b06brLZnK6qbzxDFpzrqE1OSHGI=";
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }

    /**
     * Expiration date.
     *
     * @return the date
     */
    public Date expiration() {
        return new Date(System.currentTimeMillis() + 1000 * 60 * 60);
    }

    /**
     * Create token string.
     *
     * @param username the username
     * @param claims   the claims
     * @return the string
     */
    public String createToken(String username, Map<String, Object> claims) {

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiration())
                .signWith(getKey())
                .compact();
    }

    /**
     * Generate token string.
     *
     * @param userDetails the user details
     * @return the string
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return createToken(userDetails.getUsername(), claims);
    }

    /**
     * Gets claim.
     *
     * @param <T>            the type parameter
     * @param token          the token
     * @param claimsResolver the claims resolver
     * @return the claim
     */
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
        } catch (ExpiredJwtException | MalformedJwtException e) {
            throw e;
        } catch (SignatureException e) {
            ErrorResponseDTO error = new ErrorResponseDTO("Invalid token", "Token is invalid or tampered with");
            throw new RuntimeException(String.valueOf(error));
        } catch (Exception e) {
            ErrorResponseDTO error = new ErrorResponseDTO("Internal Server Error", "Error interno del servidor");
            throw new RuntimeException(String.valueOf(error));
        }
    }

    /**
     * Gets username from token.
     *
     * @param token the token
     * @return the username from token
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Is token valid boolean.
     *
     * @param token       the token
     * @param userDetails the user details
     * @return the boolean
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Gets expiration.
     *
     * @param token the token
     * @return the expiration
     */
    public Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Is token expired boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    /**
     * Create token confirmation string.
     *
     * @param username the username
     * @return the string
     */
    public String createTokenConfirmation(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(getKey())
                .compact();
    }
}
