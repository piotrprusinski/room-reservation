package co.cosmose.room.reservation.controller.room;

import java.util.stream.Collectors;
import co.cosmose.room.reservation.infrastructure.NameHotelEqualsSpec;
import co.cosmose.room.reservation.infrastructure.PeriodBetweenSpec;
import co.cosmose.room.reservation.infrastructure.PriceBetweenSpec;
import co.cosmose.room.reservation.infrastructure.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rooms")
public class RoomRestController {

	private final RoomRepository roomRepository;
	private final RoomDtoTransformer roomDtoTransformer;

	@Autowired
	public RoomRestController(RoomRepository roomRepository, RoomDtoTransformer roomDtoTransformer) {
		this.roomRepository = roomRepository;
		this.roomDtoTransformer = roomDtoTransformer;
	}

	@GetMapping
	public RoomQueryResponse searchRooms(RoomQueryRequest roomQuery) {
		return new RoomQueryResponse(roomRepository.findAll(Specification
						.where(new NameHotelEqualsSpec(roomQuery.getCity(), "city"))
						.and(new PeriodBetweenSpec(roomQuery.getPeriodFrom(), roomQuery.getPeriodTo()))
						.and(new PriceBetweenSpec(roomQuery.getDailyPriceFrom(), roomQuery.getDailyPriceTo()))
				, PageRequest.of(roomQuery.getPageNumber(),
						roomQuery.getPageSize()))
				.get()
				.map(roomDtoTransformer::transform)
				.collect(Collectors.toList()));
	}
}
