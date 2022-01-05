package stay.data.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.WishListDto;

@Mapper
public interface WishListMapper {
	public void insertWishList(WishListDto wishDto);
	public void deleteWishList(HashMap<String, String> map);
	public List<WishListDto> getWishList(String guestId);
}