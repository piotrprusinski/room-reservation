package co.cosmose.room.reservation.controller.room;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomQueryResponse {
	List<RoomDto> rooms;
}
