package co.cosmose.room.reservation.application;

import java.util.List;
import javax.transaction.Transactional;
import co.cosmose.room.reservation.controller.customer.ThereIsNoCustomer;
import co.cosmose.room.reservation.controller.reservation.ThereIsReservationOnThatPeriod;
import co.cosmose.room.reservation.controller.room.RoomNotFound;
import co.cosmose.room.reservation.domain.Reservation;
import co.cosmose.room.reservation.infrastructure.CustomerEntity;
import co.cosmose.room.reservation.infrastructure.CustomerRepository;
import co.cosmose.room.reservation.infrastructure.ReservationEntity;
import co.cosmose.room.reservation.infrastructure.ReservationEntityTransformer;
import co.cosmose.room.reservation.infrastructure.ReservationRepository;
import co.cosmose.room.reservation.infrastructure.RoomEntity;
import co.cosmose.room.reservation.infrastructure.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final ReservationEntityTransformer reservationEntityTransformer;
	private final RoomRepository roomRepository;
	private final CustomerRepository customerRepository;

	@Autowired
	public ReservationService(ReservationRepository reservationRepository,
			ReservationEntityTransformer reservationEntityTransformer, RoomRepository roomRepository,
			CustomerRepository customerRepository) {
		this.reservationRepository = reservationRepository;
		this.reservationEntityTransformer = reservationEntityTransformer;
		this.roomRepository = roomRepository;
		this.customerRepository = customerRepository;
	}

	@Transactional
	public long bookRoom(Reservation reservation) {
		List<ReservationEntity> byRoomIdAndPeriods =
				reservationRepository.findByRoomIdAndPeriods(reservation.getRoomId(),
						reservation.getDateFrom(), reservation.getDateTo());

		if (byRoomIdAndPeriods.size() > 0) {
			throw new ThereIsReservationOnThatPeriod();
		}

		CustomerEntity customer = customerRepository.findById(reservation.getCustomerId())
				.orElseThrow(ThereIsNoCustomer::new);

		RoomEntity roomEntity = roomRepository.findById(reservation.getRoomId())
				.orElseThrow(() -> new RoomNotFound(reservation.getRoomId()));
		ReservationEntity reservationEntity =
				reservationRepository.save(reservationEntityTransformer.transform(reservation, roomEntity, customer));
		return reservationEntity.getId();
	}

	public long cancel(Long id) {
		reservationRepository.findById(id)
				.ifPresent(i -> reservationRepository.deleteById(id));
		return id;
	}

	public List<ReservationEntity> findByCustomer(Long customerId) {
		return reservationRepository.findByCustomerId(customerId);
	}
}
