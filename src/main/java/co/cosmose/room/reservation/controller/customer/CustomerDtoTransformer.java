package co.cosmose.room.reservation.controller.customer;

import co.cosmose.room.reservation.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerDtoTransformer {
	public Customer transform(NewCustomerDto customerDto) {
		return new Customer(customerDto.getName(), customerDto.getLastName(), customerDto.getEmail());
	}
}
