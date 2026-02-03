package com.example.jobpoartal.Security;

import com.example.jobpoartal.Entity.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

@Value("${jwt.secret}")
private String SecreteKey;

    public String generateToken(Users users){
return Jwts.builder()
        .setSubject(users.getEmail())
        .claim("userid",users.getId())
        .claim("username",users.getName())
        .claim("roles",users.getRoles())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
        .signWith(getsiginingkey())
        .compact();

}
    private Key getsiginingkey() {
byte[] bytes= Decoders.BASE64.decode(SecreteKey);
return Keys.hmacShaKeyFor(bytes);
    }

    public String ExtractUsername(String Token ){
      return  Jwts.parserBuilder()
            .setSigningKey(getsiginingkey())
            .build()
            .parseClaimsJws(Token)
            .getBody()
            .getSubject();

    }
    public boolean validateToken(String token , UserDetails userDetails){
     String name=ExtractUsername(token);
     return name.equals(userDetails.getUsername())&&istokenvalid(token);
    }

    private boolean istokenvalid(String token) {
        Date Expiration=Jwts.parserBuilder()
                .setSigningKey(getsiginingkey())
                .build()
                .parseClaimsJws(token)
                .getBody().getExpiration();
        return Expiration.after(new Date());

    }

//    public Long getUserid(String token){
//        return Jwts.parserBuilder()
//                .setSigningKey(getsiginingkey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .get("user_id", Long.class);
//    }




}
