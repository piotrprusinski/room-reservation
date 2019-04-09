package co.cosmose.room.reservation.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity(name = "customer")
@Data
@Table(name = "customer_entity")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
	@SequenceGenerator(name = "customer_sequence", sequenceName = "cust_seq", initialValue = 100)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;

}
