package co.cosmose.room.reservation.infrastructure;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class NameHotelEqualsSpec implements Specification<RoomEntity> {

	private String valueToSearch;
	private String filedName;

	@Override
	public Predicate toPredicate(Root<RoomEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (valueToSearch == null) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}

		Join<HotelEntity, RoomEntity> hotel = root.join("hotel");
		return criteriaBuilder.equal(hotel.get(filedName), this.valueToSearch );
	}
}
