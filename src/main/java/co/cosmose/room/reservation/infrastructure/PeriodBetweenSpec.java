package co.cosmose.room.reservation.infrastructure;

import java.time.LocalDate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

public class PeriodBetweenSpec implements Specification<RoomEntity> {

	private final LocalDate dateFrom;
	private final LocalDate dateTo;

	public PeriodBetweenSpec(LocalDate dateFrom, LocalDate dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	@Override
	public Predicate toPredicate(Root<RoomEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (dateFrom == null && dateTo == null) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}
		Subquery<RoomEntity> subquery = query.subquery(RoomEntity.class);
		Root<ReservationEntity> reservationEntityRoot = subquery.from(ReservationEntity.class);
		Join<ReservationEntity, RoomEntity> reservationSubJoin = reservationEntityRoot.join("room", JoinType.LEFT);
		Predicate dateFromPredicate = criteriaBuilder.greaterThanOrEqualTo(reservationEntityRoot.get("dateFrom"), dateTo);
		Predicate dateToPredicate = criteriaBuilder.greaterThanOrEqualTo(reservationEntityRoot.get("dateTo"), this.dateFrom);
		Predicate orPredicate = criteriaBuilder.or(dateFromPredicate, dateToPredicate);
		subquery.select(reservationSubJoin).where(orPredicate);
		return criteriaBuilder.not(criteriaBuilder.in(root).value(subquery));
	}
}
