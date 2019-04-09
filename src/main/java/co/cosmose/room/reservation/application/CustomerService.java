package co.cosmose.room.reservation.application;

import javax.transaction.Transactional;
import co.cosmose.room.reservation.domain.Customer;
import co.cosmose.room.reservation.infrastructure.CustomerEntity;
import co.cosmose.room.reservation.infrastructure.CustomerEntityTransformer;
import co.cosmose.room.reservation.infrastructure.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerEntityTransformer customerEntityTransformer;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, CustomerEntityTransformer customerEntityTransformer) {
		this.customerRepository = customerRepository;
		this.customerEntityTransformer = customerEntityTransformer;
	}

	@Transactional
	public long addCustomer(Customer customer) {
		CustomerEntity customerEntity = customerRepository.save(customerEntityTransformer.transform(customer));
		return customerEntity.getId();
	}
}
