package co.cosmose.room.reservation.controller.room;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class RoomQueryRequest {
	private int pageNumber;
	private int pageSize;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate periodFrom;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate periodTo;
	private String city;
	private BigDecimal dailyPriceFrom;
	private BigDecimal dailyPriceTo;

}
