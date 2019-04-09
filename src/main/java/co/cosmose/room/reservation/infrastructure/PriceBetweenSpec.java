package co.cosmose.room.reservation.infrastructure;

import java.math.BigDecimal;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class PriceBetweenSpec implements Specification<RoomEntity> {
	private final BigDecimal dailyPriceFrom;
	private final BigDecimal dailyPriceTo;

	public PriceBetweenSpec(BigDecimal dailyPriceFrom, BigDecimal dailyPriceTo) {
		this.dailyPriceFrom = dailyPriceFrom;
		this.dailyPriceTo = dailyPriceTo;
	}

	@Override
	public Predicate toPredicate(Root<RoomEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (dailyPriceFrom == null && dailyPriceTo == null) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}

		BigDecimal dailyPriceFromToSearch = Optional.ofNullable(dailyPriceFrom)
				.orElse(BigDecimal.ZERO);

		BigDecimal dailyPriceToToSearch = Optional.ofNullable(dailyPriceTo)
				.orElse(BigDecimal.valueOf(Long.MAX_VALUE));

		return criteriaBuilder.between(root.get("dailyPrice"), dailyPriceFromToSearch, dailyPriceToToSearch);
	}
}
