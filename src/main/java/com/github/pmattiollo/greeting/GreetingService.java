package com.github.pmattiollo.greeting;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GreetingService {

    Flux<GreetingResponse> greet(final GreetingRequest request) {
        return Flux.fromStream(Stream.generate(() -> new GreetingResponse("Hello " + request.getName() + " @ " + Instant.now())))
                .delayElements(Duration.ofSeconds(1));
    }
}
