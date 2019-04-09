package co.cosmose.room.reservation.infrastructure;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {

	@Query(value = "select r from reservation r where r.room.id = :roomId and " +
			"((:dateTo < r.dateFrom) or (:dateFrom < r.dateTo))")
	List<ReservationEntity> findByRoomIdAndPeriods(@Param("roomId") Long roomId, @Param("dateFrom") LocalDate dateFrom,
			@Param("dateTo") LocalDate dateTo);

	List<ReservationEntity> findByCustomerId(Long customerId);
}
