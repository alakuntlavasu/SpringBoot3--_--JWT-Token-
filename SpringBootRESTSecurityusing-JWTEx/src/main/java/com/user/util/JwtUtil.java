package com.user.util;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUtil {
	
	@Value("${app.secret}")
	private String secret;
	
	// 6. validate user name is token and database, ExpDate
	public boolean validaTetoken(String token,String username) {
		String tokenUserName= getUsername(token);
		return username.equals(tokenUserName)&& !isTokenExp(token);
		}
	
	//5. validate Expire date
	public boolean isTokenExp(String token) {
		
		Date expDate=getExpireDate(token);
		return expDate.after(new Date(System.currentTimeMillis()) );
		
//		return expDate.before(new Date(System.currentTimeMillis())));
		
	}
	
	//4. Red subject/username
	public String getUsername(String token) {
		return getclaims(token).getSubject();
		
	}
	
	//3 Read Expire Date from given token
	public Date getExpireDate(String token) {
		
		return (Date) getclaims(token).getExpiration();
		
	}
	
	//2.Read claims
	public Claims getclaims(String token ) {
		
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	//1. Generate Token
	
	public String generateToken(String subject) {
		
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("Alakuntlavasu")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes() )
				.compact();
		
	}

}
