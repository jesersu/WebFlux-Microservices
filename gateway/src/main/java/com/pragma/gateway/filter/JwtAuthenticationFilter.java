package com.pragma.gateway.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
//public class JwtAuthenticationFilter implements GatewayFilter{
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // Extract JWT from Authorization header
//        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return Mono.error(new RuntimeException("Missing or invalid Authorization header"));
//        }
//        String token = authHeader.substring(7);
//
//        // Validate JWT (pseudo-code, implement actual validation)
//        if (!isValidToken(token)) {
//            return Mono.error(new RuntimeException("Invalid JWT token"));
//        }
//
//        // If valid, proceed with the request
//        return chain.filter(exchange);
//    }
//}
