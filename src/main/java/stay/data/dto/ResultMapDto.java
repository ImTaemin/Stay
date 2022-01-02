package stay.data.dto;

import lombok.Data;

@Data
public class ResultMapDto {
	private MemberDto memDto;
	private RoomDto roomDto;
	private ReservationDto resDto;
	private CanReservationDto canDto;
	private JoinGuestDto joinDto;
	private GuestCommentDto gcoDto;
	private WishListDto wishDto;
	private CommentLikeDto likeDto;
	private AvgTotalDto atDto;
}