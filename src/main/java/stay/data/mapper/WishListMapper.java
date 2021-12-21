package stay.data.mapper;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.WishListDto;

@Mapper
public interface WishListMapper {
	public void insertWishList(WishListDto wishDto);
}