package co.cosmose.room.reservation.controller.reservation;

import lombok.Data;

@Data
public class ReservationDto extends NewReservationDto {
	private Long roomId;
}
