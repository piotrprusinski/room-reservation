package co.cosmose.room.reservation.controller.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No available room at requested period")
public class ThereIsReservationOnThatPeriod extends RuntimeException {
}
