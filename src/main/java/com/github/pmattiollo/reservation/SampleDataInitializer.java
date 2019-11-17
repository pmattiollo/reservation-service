package com.github.pmattiollo.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Log4j2
@RequiredArgsConstructor
@Component
class SampleDataInitializer {

    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        var saved = this.reservationService
                .saveAll("Josh", "Madhura", "Mark", "Olga", "Spencer", "Ria", "Stéphane", "Violetta");

        this.reservationRepository.deleteAll()
                .thenMany(saved)
                .thenMany(this.reservationRepository.findAll())
                .subscribe(log::info);
    }
}
