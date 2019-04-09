package co.cosmose.room.reservation;

import java.time.LocalDate;
import co.cosmose.room.reservation.controller.ResultDto;
import co.cosmose.room.reservation.controller.customer.NewCustomerDto;
import co.cosmose.room.reservation.controller.reservation.NewReservationDto;
import co.cosmose.room.reservation.controller.room.RoomQueryResponse;
import org.springframework.web.client.RestTemplate;

public class ReservationFactory {

	private final RestTemplate restTemplate;
	private final int port;

	public ReservationFactory(RestTemplate restTemplate, int port) {
		this.restTemplate = restTemplate;
		this.port = port;
	}

	public ResultDto callToReserveRoom(String dateFrom, String dateTo) {
		Long customerId = createCustomer();
		Long sampleRoomId = findRoom();
		NewReservationDto newReservation = createNewReservation(sampleRoomId, customerId, LocalDate.parse(dateFrom),
				LocalDate.parse(dateTo));
		return bookRoom(newReservation);
	}

	private ResultDto bookRoom(NewReservationDto newReservation) {
		return this.restTemplate.postForObject(
				String.format("http://localhost:%d/reservations", port),
				newReservation,
				ResultDto.class);
	}

	private Long createCustomer() {
		NewCustomerDto request = createNewCustomer();
		ResultDto resultDto = this.restTemplate.postForObject(
				String.format("http://localhost:%d/customers", port),
				request,
				ResultDto.class);
		assert resultDto != null;
		return resultDto.getId();
	}

	private Long findRoom() {
		RoomQueryResponse rooms = this.restTemplate.getForObject(
				String.format("http://localhost:%d/rooms?pageNumber=0&pageSize=100", port),
				RoomQueryResponse.class);

		assert rooms != null;
		return rooms.getRooms()
				.stream()
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("There is no available rooms"))
				.getRoomId();
	}

	private NewReservationDto createNewReservation(Long roomId, Long customerId, LocalDate dateFrom,
			LocalDate dateTo) {
		NewReservationDto r = new NewReservationDto();
		r.setRoomId(roomId);
		r.setCustomerId(customerId);
		r.setDateFrom(dateFrom);
		r.setDateTo(dateTo);
		return r;
	}

	private NewCustomerDto createNewCustomer() {
		NewCustomerDto c = new NewCustomerDto();
		c.setEmail("blabla@mail.com");
		c.setLastName("LastName");
		c.setName("Name");
		return c;
	}

}