package stay.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.WishListDto;
import stay.data.mapper.WishListMapper;

@Service
public class WishListService {
	@Autowired
	WishListMapper mapper;
	
	public void insertWishList(WishListDto wishDto) {
		mapper.insertWishList(wishDto);
	}
}