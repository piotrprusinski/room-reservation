package co.cosmose.room.reservation.controller.room;

import co.cosmose.room.reservation.infrastructure.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoTransformer {

	public RoomDto transform(RoomEntity roomEntity) {
		RoomDto roomDto = new RoomDto();
		roomDto.setDailyPrice(roomEntity.getDailyPrice().toPlainString());
		roomDto.setHotelName(roomEntity.getHotel().getName());
		roomDto.setRoomId(roomEntity.getId());
		roomDto.setRoomName(roomEntity.getName());
		return roomDto;
	}
}
