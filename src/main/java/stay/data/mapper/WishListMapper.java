package stay.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.WishListDto;

@Mapper
public interface WishListMapper {
	public void insertWishList(WishListDto wishDto);
	public List<WishListDto> selectAllWish(String guest_id);
}