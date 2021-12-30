package stay.data.dto;

import lombok.Data;

@Data
public class RoomReserGcomDto {
	private RoomDto roomDto;
	private ReservationDto resDto;
	private GuestCommentDto gcoDto;
	private AvgTotalDto acDto;
}