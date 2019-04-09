package co.cosmose.room.reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import co.cosmose.room.reservation.controller.ResultDto;
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
public class ReservationRestControllerTest {

	private RestTemplate restTemplate = new RestTemplateBuilder().build();

	@LocalServerPort
	private int port;

	@Test
	public void shouldReserveRoomInHotel() {
		// given
		ReservationFactory reservationFactory = new ReservationFactory(restTemplate, port);
		// when
		ResultDto resultDto = reservationFactory.callToReserveRoom("2018-06-10", "2018-06-20");
		// then
		assertThat(resultDto).isNotNull();
		assertThat(resultDto.getId()).isGreaterThanOrEqualTo(100);
	}



}
