package work.course.notificationsystem.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.security.model.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String SECRET;

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(
            new HashMap<>(), userDetails
    );
  }

  public String generateToken(Map<String, Object> claims, UserDetails details) {
    return Jwts
            .builder()
            .claims(claims)
            .subject((details).getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 100))
            .signWith(getSigningKey(), Jwts.SIG.HS256)
            .compact();
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
            .parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

  private boolean isTokenExpired(String token) {
    return getExpirationDate(token).before(new Date());
  }

  private Date getExpirationDate(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private SecretKey getSigningKey() {
    byte[] bytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(bytes);
  }

}
