package co.cosmose.room.reservation.controller.room;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RoomNotFound extends RuntimeException {
	public RoomNotFound(Long roomId) {
		super(String.format("Room not found %s", roomId));
	}
}
