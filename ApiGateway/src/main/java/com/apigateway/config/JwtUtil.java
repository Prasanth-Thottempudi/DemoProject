package com.apigateway.config;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

	private final String SECRET_KEY = "your_secret_key";

	// Extract username from token
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// Extract roles (authorities) from token
	public List<String> extractRoles(String token) {
		Claims claims = extractAllClaims(token);
		return (List<String>) claims.get("roles"); // Ensure roles are stored as List<String> in the token
	}

	// Validate the token (checks expiry)
	public boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

	// Helper methods

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean isTokenValid(String token) {
		final String username = extractUsername(token);
		return (username != null && !isTokenExpired(token));
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
