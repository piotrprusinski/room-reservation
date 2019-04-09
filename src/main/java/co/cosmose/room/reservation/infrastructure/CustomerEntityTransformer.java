package co.cosmose.room.reservation.infrastructure;

import co.cosmose.room.reservation.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityTransformer {
	public CustomerEntity transform(Customer customer) {
		CustomerEntity ce = new CustomerEntity();
		ce.setEmail(customer.getEmail());
		ce.setLastName(customer.getLastName());
		ce.setName(customer.getName());
		return ce;
	}
}
