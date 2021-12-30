package stay.data.dto;

import lombok.Data;

@Data
public class RoomWishResGcoDto {
	private RoomDto roomDto;
	private WishListDto wishDto;
	private ReservationDto resDto;
	private GuestCommentDto gcoDto;
	private AvgTotalDto atDto;
}