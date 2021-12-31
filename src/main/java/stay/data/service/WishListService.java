package stay.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.ResultMapDto;
import stay.data.dto.WishListDto;
import stay.data.mapper.WishListMapper;

@Service
public class WishListService {
	@Autowired
	WishListMapper mapper;
	
	public void insertWishList(WishListDto wishDto) {
		mapper.insertWishList(wishDto);
	}
	
	public void deleteWishList(String roomId, String guestId) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("roomId", roomId);
		map.put("guestId", guestId);
		
		mapper.deleteWishList(map);
	}
	
	public List<WishListDto> getWishList(String guestId) {
		return mapper.getWishList(guestId);
	}
	
//	public List<ResultMapDto> getWishList(String guestId) {
//		return mapper.getWishList(guestId);
//	}
}