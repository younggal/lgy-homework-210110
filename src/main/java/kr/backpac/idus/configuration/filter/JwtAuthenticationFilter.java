package kr.backpac.idus.configuration.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import kr.backpac.idus.entity.Member;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    	this.setFilterProcessesUrl("/api/login");
        this.authenticationManager = authenticationManager;
    }
	
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
	    	ObjectMapper mapper = new ObjectMapper();
	    	Member creds = mapper.readValue(request.getInputStream(), Member.class);
	    	
	    	return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	    			creds.getUsername(),
	    			creds.getPassword()
			));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(((Member) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24)))
                .signWith(Keys.hmacShaKeyFor("GAYOUNG0909ASDFABCAD6CA5SD0C16ASDC56ASD40C65ASD40C6A51SD0C6A1SD5".getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
                .compact();
        response.addHeader("Authorization", "Bearer " + token);
    }
}