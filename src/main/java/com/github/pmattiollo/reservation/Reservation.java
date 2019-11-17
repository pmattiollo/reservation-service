package com.github.pmattiollo.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Reservation {

    @Id
    private Integer id;
    private String name;

    Reservation(String name) {
        this.name = name;
    }
}
