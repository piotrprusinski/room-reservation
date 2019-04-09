package co.cosmose.room.reservation.infrastructure;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity(name = "reservation")
@Data
@Table(name = "reservation_entity")
public class ReservationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_sequence")
	@SequenceGenerator(name = "reservation_sequence", sequenceName = "reservation_seq", initialValue = 100)
	private Long id;

	@ManyToOne(targetEntity = RoomEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "room_id")
	private RoomEntity room;

	@Column(name = "date_from")
	private LocalDate dateFrom;

	@Column(name = "date_to")
	private LocalDate dateTo;

	@ManyToOne(targetEntity = CustomerEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;

}
