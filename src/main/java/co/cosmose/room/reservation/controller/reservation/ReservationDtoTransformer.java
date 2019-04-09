package co.cosmose.room.reservation.controller.reservation;

import co.cosmose.room.reservation.domain.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationDtoTransformer {

	public Reservation transform(NewReservationDto newReservationDto) {
		return new Reservation(newReservationDto.getRoomId(), newReservationDto.getCustomerId(),
				newReservationDto.getDateFrom(), newReservationDto.getDateTo());
	}
}
