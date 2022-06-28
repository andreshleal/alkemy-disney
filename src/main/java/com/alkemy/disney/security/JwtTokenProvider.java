package com.alkemy.disney.security;



import java.util.Date;

import com.alkemy.disney.exceptions.BlogAppException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	
	public String generarToken(Authentication authentication) {
		String username = authentication.getName();
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
		
		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(fechaExpiracion).signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
		
		return token;
	}
	
	public String obtenerUsernameDelJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			System.out.println("firma JWT no valida");
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "firma JWT no valida");
		} catch (MalformedJwtException ex) {
			System.out.println("token JWT no valida");
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "token JWT no valida");
		} catch (ExpiredJwtException ex) {
			System.out.println("token JWT caducado");
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "token JWT caducado");
		} catch (UnsupportedJwtException ex) {
			System.out.println("token JWT no compatible");
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "token JWT no compatible");
		} catch (IllegalArgumentException ex) {
			System.out.println("la cadena claims JWT esta vaci");
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "la cadena claims JWT esta vacia");
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
