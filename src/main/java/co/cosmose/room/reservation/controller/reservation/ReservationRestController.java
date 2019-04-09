package co.cosmose.room.reservation.controller.reservation;

import java.util.List;
import java.util.stream.Collectors;
import co.cosmose.room.reservation.application.ReservationService;
import co.cosmose.room.reservation.controller.ResultDto;
import co.cosmose.room.reservation.infrastructure.ReservationEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationRestController {

	private final ReservationService reservationService;
	private final ReservationDtoTransformer reservationDtoTransformer;
	private final ReservationEntityTransformer reservationEntityTransformer;

	@Autowired
	public ReservationRestController(ReservationService reservationService,
			ReservationDtoTransformer reservationDtoTransformer,
			ReservationEntityTransformer reservationEntityTransformer) {
		this.reservationService = reservationService;
		this.reservationDtoTransformer = reservationDtoTransformer;
		this.reservationEntityTransformer = reservationEntityTransformer;
	}

	@PostMapping
	public ResultDto bookRoom(@RequestBody NewReservationDto reservation) {
		return new ResultDto(reservationService.bookRoom(reservationDtoTransformer.transform(reservation)));
	}

	@GetMapping("/{id}/cancel")
	public ResultDto cancel(@PathVariable Long id) {
		return new ResultDto(reservationService.cancel(id));
	}

	@GetMapping
	public List<ReservationDto> findAllReservationByCustomerId(@RequestParam("customerId") Long customerId) {
		return reservationService.findByCustomer(customerId)
				.stream()
				.map(reservationEntityTransformer::transform)
				.collect(Collectors.toList());
	}
}
