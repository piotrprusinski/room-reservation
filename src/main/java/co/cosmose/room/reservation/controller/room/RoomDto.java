package co.cosmose.room.reservation.controller.room;

import lombok.Data;

@Data
public class RoomDto {
	private Long roomId;
	private String roomName;
	private String hotelName;
	private String dailyPrice;
}
