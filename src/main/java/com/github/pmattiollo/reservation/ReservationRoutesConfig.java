package com.github.pmattiollo.reservation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class ReservationRoutesConfig {

    @Bean
    RouterFunction<ServerResponse> routes(final ReservationRepository reservationRepository) {
        return route()
                .GET("reservations", request -> ok().body(reservationRepository.findAll(), Reservation.class))
                .build();
    }
}
