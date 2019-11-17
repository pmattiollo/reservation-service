package com.github.pmattiollo.greeting;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

@Configuration
public class GreetingWebSocketConfig {

    @Bean
    WebSocketHandler webSocketHandler(final GreetingService greetingService) {
        return session -> {
            var receive = session
                    .receive()
                    .map(WebSocketMessage::getPayloadAsText)
                    .map(GreetingRequest::new)
                    .flatMap(greetingService::greet)
                    .map(GreetingResponse::getMessage)
                    .map(session::textMessage);

            return session.send(receive);
        };
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(final WebSocketHandler webSocketHandler) {
        return new SimpleUrlHandlerMapping(Map.of("/ws/greetings", webSocketHandler), 10);
    }
}
