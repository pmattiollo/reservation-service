package com.github.pmattiollo.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TransactionalOperator transactionalOperator;

    Flux<Reservation> saveAll(String... names) {
        return this.transactionalOperator.transactional(
            Flux.fromArray(names)
                    .map(Reservation::new)
                    .flatMap(this.reservationRepository::save)
                    .doOnNext(ReservationService::accept));
    }

    private static void accept(final Reservation reservation) {
        Assert.isTrue(Character.isUpperCase(reservation.getName().charAt(0)), "The name must have a capital first letter");
    }

}
