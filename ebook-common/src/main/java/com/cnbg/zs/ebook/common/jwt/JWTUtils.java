package com.cnbg.zs.ebook.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
@Component
public class JWTUtils {
    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
    // 过期时间 8h
    public static final long EXPIRE_TIME = 8 * 1 * 60 * 60 * 1000;
    // 颁发者
    private static final String SING_USER_NAME = "open-free-team";
    // API 接口访问token key
    public static final String AUTH_HEADER_KEY = "token";
    // 接口token信息 KEY 基于UUID 用户实际信息存储在 redis中
    public static final String SESSION_ID_KEY = "sessionId";

    private static   String jwtAccessSecret;


    /**
    * 生成 jwt token
    *
    * @param claims payload 数据声明
    * @return
    */
    public static String createSingleJwtToken(Map<String, String> claims, Long expireTime) {

        // 设置私钥加密方式
        Algorithm algorithm = Algorithm.HMAC256(jwtAccessSecret);

        long nowMillis = System.currentTimeMillis();// 生成JWT的时间
        Date now = new Date(nowMillis);
        // token有效期
        Date endTime = new Date(nowMillis + expireTime);

        JWTCreator.Builder builder = JWT.create();
        builder.withIssuer(SING_USER_NAME) // 设置签发者
        .withIssuedAt(now) // 设置签发时间
        // .withSubject(subject)
        // //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
        .withJWTId(claims.get("sessionId").toString()) // 设置jti(JWT
        // ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
        .withExpiresAt(endTime); // 设置过期时间

    claims.forEach((key, value) -> {
        builder.withClaim(key, value);
    });
        String jwtToken = builder.sign(algorithm);
        System.out.println("JWT生成-" + jwtToken);

        return jwtToken;
    }

    /**
    * 验证token是否正确
    *
    * @param token
    * @return
    */
    public static DecodedJWT verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtAccessSecret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(SING_USER_NAME).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;

        } catch (JWTVerificationException e) {
            logger.error("非法token:" + token);
            logger.error(e.getMessage());
            e.printStackTrace();
        return null;
    }

    }
    /**
    * 根据token中存储对象获取对应value
    *
    * @param jwt
    * @param key
    * @return
    */
    public static String getTokenObjectValue(DecodedJWT jwt,String key) {
        return jwt.getClaim(key).asString();
    }
    /**
    * 根据token中存储对象获取对应value
    *
    * @param token
    * @param key
    * @return
    */
    public static String getTokenObjectValue(String token, String key) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(key).asString();
    }

    public String getJwtAccessSecret() {
        return jwtAccessSecret;
    }
    @Value("${jwt.access.token.secret}")
    public void setJwtAccessSecret(String jwtAccessSecret) {
        this.jwtAccessSecret = jwtAccessSecret;
        }
}
