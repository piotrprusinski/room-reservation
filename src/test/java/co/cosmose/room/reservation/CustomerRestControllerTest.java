package co.cosmose.room.reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import co.cosmose.room.reservation.controller.ResultDto;
import co.cosmose.room.reservation.controller.customer.NewCustomerDto;
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
public class CustomerRestControllerTest {

	private RestTemplate restTemplate = new RestTemplateBuilder().build();

	@LocalServerPort
	private int port;

	@Test
	public void shouldAddNewCustomer() {
		// given
		NewCustomerDto request = createNewCustomer();
		// when
		ResultDto resultDto = this.restTemplate.postForObject(
				String.format("http://localhost:%d/customers", port),
				request,
				ResultDto.class);
		// then
		assertThat(resultDto).isNotNull();
		assertThat(resultDto.getId()).isGreaterThanOrEqualTo(100);
	}

	private NewCustomerDto createNewCustomer() {
		NewCustomerDto c = new NewCustomerDto();
		c.setEmail("blabla@mail.com");
		c.setLastName("LastName");
		c.setName("Name");
		return c;
	}

}
