package com.zsh.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zsh on 2023/3/6
 *
 * @author zsh
 */
@Slf4j
public class JwtTokenUtils {
    /**
     * 请求头
     */
    public static final String TOKEN_HEADER = "Authorization";
    /**
     * TOKEN 前缀
     */
    public static final String TOKEN_PREFIX = "Bearer";
    /**
     * 私钥
     */
    private static final String TOKEN_SECRET = "secret";
    /**
     * 令牌过期时间 : one day
     * 测试：改为7天
     */
    private static final long TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7;
    /**
     * 用户id定义
     */
    public static final String USER_ID = "userId";
    /**
     * 密码名定义
     */
    private static final String USERNAME = "username";


    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";


    /**
     * 根据负责生成JWT的token
     */
    public static String generateToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();
        return TOKEN_PREFIX + " " + token;
    }

    public static String generateToken(String userId, String username) {
        Map<String, Object> map = new HashMap<>();
        map.put(USER_ID, userId);
        map.put(USERNAME, username);
        return generateToken(map);
    }
    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }


    /**
     * 生成token的过期时间
     */
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_EXPIRATION);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    /**
     * 解析令牌
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
    }


    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    /**
     * 当原来的token没过期时是可以刷新的
     *
     * @param oldToken 带tokenHead的token
     */
    public String refreshHeadToken(String oldToken) {
        if (StringUtils.isEmpty(oldToken)) {
            return null;
        }
        String token = oldToken.substring(TOKEN_HEADER.length());
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        //token校验不通过
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        //如果token已经过期，不支持刷新
        if (isTokenExpired(token)) {
            return null;
        }
        //如果token在30分钟之内刚刷新过，返回原token
        if (tokenRefreshJustBefore(token)) {
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     *
     * @param token 原token
     */
    private boolean tokenRefreshJustBefore(String token) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Instant refreshInstant = Instant.now();
        Instant createdInstant = created.toInstant();
        //刷新时间在创建时间的指定时间内
        if (refreshInstant.isAfter(createdInstant)) {
            return true;
        }
        return false;
    }
}




