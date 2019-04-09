package co.cosmose.room.reservation.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<RoomEntity, Long>,
		JpaSpecificationExecutor<RoomEntity> {

	Page<RoomEntity> findAll(Specification<RoomEntity> spec, Pageable pageable);
}
