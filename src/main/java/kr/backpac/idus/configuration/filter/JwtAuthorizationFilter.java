package kr.backpac.idus.configuration.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		
		SecurityContextHolder.getContext().setAuthentication(getAuthentication(header));
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder()
			.setSigningKey(Keys.hmacShaKeyFor("GAYOUNG0909ASDFABCAD6CA5SD0C16ASDC56ASD40C65ASD40C6A51SD0C6A1SD5".getBytes(StandardCharsets.UTF_8)))
			.build()
			.parseClaimsJws(token.replace("Bearer ", ""));
			
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			return new UsernamePasswordAuthenticationToken((String) claims.getBody().get("sub"), null, authorities);
		} catch (Exception e) {
			return null;
		}
	}

}
