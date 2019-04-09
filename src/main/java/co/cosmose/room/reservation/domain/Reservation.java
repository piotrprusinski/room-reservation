package co.cosmose.room.reservation.domain;

import java.time.LocalDate;
import lombok.Value;

@Value
public class Reservation {
	private Long roomId;
	private Long customerId;
	private LocalDate dateFrom;
	private LocalDate dateTo;
}
