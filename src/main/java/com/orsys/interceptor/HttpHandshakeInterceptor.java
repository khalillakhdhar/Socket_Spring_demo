package com.orsys.interceptor;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Component
public class HttpHandshakeInterceptor implements HandshakeInterceptor {
private static final Logger logger = LoggerFactory.getLogger(HttpHandshakeInterceptor.class);
//detection des accés à la session via adresse ip

@Override
public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
Map<String, Object> attributes) throws Exception {
logger.info("Call beforeHandshake");
if (request instanceof ServletServerHttpRequest) {
ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
HttpSession session = servletRequest.getServletRequest().getSession();
attributes.put("sessionId", session.getId());
}
return true;
}
// récupération de la liste de base des adresses connecté
public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
Exception ex) {
logger.info("Call afterHandshake");
}
}