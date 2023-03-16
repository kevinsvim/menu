package com.zsh.gateway.filter;

import com.google.gson.JsonObject;
import com.zsh.common.result.CommonResultCode;
import com.zsh.common.utils.JwtTokenUtils;
import com.zsh.gateway.constant.IgnoreWhiteProperties;
import com.zsh.gateway.constant.ResponseStatusName;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by zsh on 2023/3/6
 *
 * @author zsh
 */
@Component
public class MenuGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private IgnoreWhiteProperties whiteProperties;
    private final PathMatcher pathMatcher = new AntPathMatcher();

    /**
     *
     * @param exchange spring封装的web相关对象(request、response、session等)
     * @param chain 过滤器链
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 1.放行白名单
        String path = request.getPath().value();
        if (isWhiteURL(path)) {
            return chain.filter(exchange);
        }
        // 2.校验token
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst(JwtTokenUtils.TOKEN_HEADER);
        if (StringUtils.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            String token = authorizationHeader.substring(JwtTokenUtils.TOKEN_PREFIX.length());
            try {
                Claims claims = JwtTokenUtils.parseToken(token);
                String userId = (String) claims.get(JwtTokenUtils.USER_ID);
                // token检测成功后,将用户id设置到请求头中
                // 设置用户信息到请求头中
                addHeader(request.mutate(), userId);
                return chain.filter(exchange);
            } catch (JwtException e) {
                return out(exchange.getResponse(), CommonResultCode.TOKEN_INVALID.getCode(), CommonResultCode.TOKEN_INVALID.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        return out(exchange.getResponse(), CommonResultCode.UNAUTHORIZED.getCode(), CommonResultCode.UNAUTHORIZED.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    private boolean isWhiteURL(String currentURL) {
        List<String> whites = whiteProperties.getWhites();
        for (String whiteURL : whites) {
            if (pathMatcher.match(whiteURL, currentURL)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private void addHeader(ServerHttpRequest.Builder mutate, Object value) {
        if (value == null) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = urlEncode(valueStr);
        mutate.header(JwtTokenUtils.USER_ID, valueEncode);
    }
    static String urlEncode(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }

    /**
     * 鉴权失败自定义响应
     */
    private Mono<Void> out(ServerHttpResponse response, Integer code, String message, HttpStatus status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ResponseStatusName.SUCCESS.getName(), false);
        jsonObject.addProperty(ResponseStatusName.CODE.getName(), code);
        jsonObject.addProperty(ResponseStatusName.DATA.getName(), message);
        byte[] bits = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(status);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
