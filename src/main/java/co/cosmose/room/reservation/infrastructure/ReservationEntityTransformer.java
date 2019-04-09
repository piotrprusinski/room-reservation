package co.cosmose.room.reservation.infrastructure;

import co.cosmose.room.reservation.controller.reservation.ReservationDto;
import co.cosmose.room.reservation.domain.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationEntityTransformer {
	public ReservationEntity transform(Reservation reservation, RoomEntity roomEntity, CustomerEntity customer) {
		ReservationEntity reservationEntity = new ReservationEntity();
		reservationEntity.setDateFrom(reservation.getDateFrom());
		reservationEntity.setDateTo(reservation.getDateTo());
		reservationEntity.setRoom(roomEntity);
		reservationEntity.setCustomer(customer);
		return reservationEntity;
	}

	public ReservationDto transform(ReservationEntity reservationEntity) {
		ReservationDto dto = new ReservationDto();
		dto.setDateFrom(reservationEntity.getDateFrom());
		dto.setDateTo(reservationEntity.getDateTo());
		dto.setRoomId(reservationEntity.getRoom().getId());
		dto.setCustomerId(reservationEntity.getCustomer().getId());
		return dto;
	}

}
