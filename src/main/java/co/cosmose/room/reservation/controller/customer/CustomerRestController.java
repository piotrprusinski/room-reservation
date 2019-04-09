package co.cosmose.room.reservation.controller.customer;

import co.cosmose.room.reservation.application.CustomerService;
import co.cosmose.room.reservation.controller.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

	private final CustomerService customerService;
	private final CustomerDtoTransformer customerDtoTransformer;

	@Autowired
	public CustomerRestController(CustomerService customerService, CustomerDtoTransformer customerDtoTransformer) {
		this.customerService = customerService;
		this.customerDtoTransformer = customerDtoTransformer;
	}

	@PostMapping
	public ResultDto registerCustomer(@RequestBody NewCustomerDto customer) {
		return new ResultDto(customerService.addCustomer(customerDtoTransformer.transform(customer)));
	}
}
