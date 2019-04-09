package co.cosmose.room.reservation.domain;

import lombok.Value;

@Value
public class Customer {
	private String name;
	private String lastName;
	private String email;
}
