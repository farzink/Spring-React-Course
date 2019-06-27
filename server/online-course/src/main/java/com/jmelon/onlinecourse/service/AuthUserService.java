package com.jmelon.onlinecourse.service;

import com.jmelon.onlinecourse.Util.SimpleStorageManager;
import com.jmelon.onlinecourse.model.AuthUser;
import com.jmelon.onlinecourse.model.JWTModel;
import com.jmelon.onlinecourse.model.Person;
import com.jmelon.onlinecourse.model.viewmodels.AuthUserViewModel;
import com.jmelon.onlinecourse.model.viewmodels.TokenViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

@Service
public class AuthUserService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private SimpleStorageManager simpleStorageManager;

    @Autowired
    private PersonService userService;

    public boolean register(AuthUserViewModel model) {
        try {
            if (simpleStorageManager.userExists(fromAuthUserViewModel(model)))
                throw new Exception();
            Person person = new Person(model.firstname, model.lastname, model.birthday);
            userService.create(person);
            var authUser = fromAuthUserViewModel(model);
            authUser.setId(person.getId());
            simpleStorageManager.addUser(authUser);
            return true;
        } catch (Exception ex) {
            System.out.println("user creation error");
            return false;
        }
    }

    /*public boolean logout(AuthUserViewModel model) {
        try {
            if (simpleStorageManager.userExists(fromAuthUserViewModel(model)))
                throw new Exception();
            simpleStorageManager.addUser(fromAuthUserViewModel(model));
            return true;
        } catch (Exception ex) {
            System.out.println("user creation error");
            return false;
        }
    }*/


    public TokenViewModel token(AuthUserViewModel model) {
        try {
            var user = simpleStorageManager.findUserWithEmailAndPassword(fromAuthUserViewModel(model));
            if (user != null) {
                //token creation
                var result = new TokenViewModel();
                result.accessToken = createJWT("12312", "me", "wtf", 234554632);
                user.setToken(result.accessToken);
                simpleStorageManager.registerToken(user);
                return result;
            }
            return null;
        } catch (Exception ex) {
            System.out.println("user creation error");
            return null;
        }
    }

    private String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    private JWTModel parseJWT(String jwt) {

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                    .parseClaimsJws(jwt).getBody();
            return new JWTModel(claims.getId(), claims.getSubject(), claims.getIssuer(), claims.getExpiration().toString(), true);
        } catch (Exception ex) {
            return new JWTModel("", "", "", "", false);
        }
    }

    public AuthUser getUserByToken(String token) {
        try {
            return simpleStorageManager.getUserByToken(token);
        } catch (Exception ex) {
            return null;
        }
    }

    AuthUser fromAuthUserViewModel(AuthUserViewModel model) {
        return new AuthUser(0, model.email, model.password, "");
    }
}
