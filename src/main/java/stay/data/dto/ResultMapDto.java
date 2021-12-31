package stay.data.dto;

import lombok.Data;

@Data
public class ResultMapDto {
	private RoomDto roomDto;
	private MemberDto memDto;
	private WishListDto wishDto;
	private ReservationDto resDto;
	private GuestCommentDto gcoDto;
	private CommentLikeDto likeDto;
	private AvgTotalDto atDto;
}