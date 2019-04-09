package co.cosmose.room.reservation.controller.reservation;

import java.time.LocalDate;
import lombok.Data;

@Data
public class NewReservationDto {
	private Long roomId;
	private Long customerId;
	private LocalDate dateFrom;
	private LocalDate dateTo;
}
