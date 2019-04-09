package co.cosmose.room.reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import co.cosmose.room.reservation.controller.room.RoomQueryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Configuration
public class RoomRestControllerTest {

	private RestTemplate restTemplate = new RestTemplateBuilder().build();

	@LocalServerPort
	private int port;

	@Test
	public void shouldFindRooms() {
		// given
        // when
		RoomQueryResponse rooms = this.restTemplate.getForObject(
				String.format("http://localhost:%d/rooms" +
						"?pageNumber=0&pageSize=100&city=Warszawa&dailyPriceFrom=100.0&dailyPriceTo=200.10" +
						"&periodFrom=%s&periodTo=%s", port, "2019-06-01", "2019-06-10"),
				RoomQueryResponse.class);

		// then
		assertThat(rooms).isNotNull();
		assertThat(rooms.getRooms().size()).isGreaterThanOrEqualTo(1);
	}

	@Test
	public void shouldFindLessRoomsCauseOfReserved() {
		// given
		String dateFrom = "2019-06-01";
		String dateTo = "2019-06-10";

		RoomQueryResponse roomsBefore = this.restTemplate.getForObject(
				String.format("http://localhost:%d/rooms" +
						"?pageNumber=0&pageSize=100&city=Warszawa&dailyPriceFrom=100.0&dailyPriceTo=200.10" +
						"&periodFrom=%s&periodTo=%s", port, dateFrom, dateTo),
				RoomQueryResponse.class);

		new ReservationFactory(restTemplate, port).callToReserveRoom(dateFrom, dateTo);
		// when
		RoomQueryResponse rooms = this.restTemplate.getForObject(
				String.format("http://localhost:%d/rooms" +
						"?pageNumber=0&pageSize=100&city=Warszawa&dailyPriceFrom=100.0&dailyPriceTo=200.10" +
							"&periodFrom=%s&periodTo=%s", port, dateFrom, dateTo),
				RoomQueryResponse.class);

		// then
		assertThat(rooms).isNotNull();
		assertThat(roomsBefore).isNotNull();
		assertThat(roomsBefore.getRooms().size()).isGreaterThan(rooms.getRooms().size());
	}

}
