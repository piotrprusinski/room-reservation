package co.cosmose.room.reservation.infrastructure;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity(name = "room")
@Data
@Table(name = "room_entity")
public class RoomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence")
	@SequenceGenerator(name = "room_sequence", sequenceName = "room_seq", initialValue = 100)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "daily_price")
	private BigDecimal dailyPrice;

	@ManyToOne(targetEntity = HotelEntity.class, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "hotel_id")
	private HotelEntity hotel;

	@OneToMany(targetEntity = ReservationEntity.class, fetch = FetchType.LAZY)
	private List<ReservationEntity> reservations;
}
