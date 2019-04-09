package co.cosmose.room.reservation.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity(name = "hotel")
@Data
@Table(name = "hotel_entity")
public class HotelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_sequence")
	@SequenceGenerator(name = "hotel_sequence", sequenceName = "hotel_seq", initialValue = 100)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "city")
	private String city;
}
