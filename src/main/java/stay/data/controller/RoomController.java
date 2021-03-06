package stay.data.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.CommentLikeDto;
import stay.data.dto.GuestCommentDto;
import stay.data.dto.HostCommentDto;
import stay.data.dto.MemberDto;
import stay.data.dto.ResultMapDto;
import stay.data.dto.RoomDto;
import stay.data.dto.WishListDto;
import stay.data.service.CommentLikeService;
import stay.data.service.GuestCommentService;
import stay.data.service.HostCommentService;
import stay.data.service.MemberService;
import stay.data.service.RoomService;
import stay.data.service.WishListService;

@Controller
@RequestMapping("/room")
public class RoomController {
	@Autowired
	RoomService roomService;

	@Autowired
	GuestCommentService gcommentService;

	@Autowired
	MemberService memberService;

	@Autowired
	WishListService wishService;

	@Autowired
	CommentLikeService likeService;
	
	@Autowired
	HostCommentService hcommentService;

	@GetMapping("/main")
	public ModelAndView roomMain(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(defaultValue = "", required = false) String addr_load,
			@RequestParam(defaultValue = "", required = false) String from,
			@RequestParam(defaultValue = "", required = false) String to, HttpSession session) {
		ModelAndView mview = new ModelAndView();

		String myid = (String) session.getAttribute("myid");
		String loginok = (String) session.getAttribute("loginok");

		int totalCount = 0;

		if (addr_load.isEmpty()) {
			// ??? ?????? ??????
			totalCount = roomService.getRoomCount();
		} else {
			// ????????? ?????? ??????
			totalCount = roomService.getRoomSearchCount(addr_load, from, to);
		}

		// ??? ????????? ???
		int totalPage;
		// ??? ????????? ?????? ?????????
		int startPage;
		// ??? ????????? ??? ?????????
		int endPage;
		// ??? ???????????? ?????? ??????
		int start;

		// ??? ???????????? ????????? ?????? ??????
		int perPage = 5;
		// ??? ???????????? ???????????? ????????? ??????
		int perBlock = 5;

		// ??? ????????? ?????? ?????????
		totalPage = totalCount / perPage + (totalCount % perPage == 0 ? 0 : 1);

		// ??? ????????? ?????? ?????????
		startPage = (currentPage - 1) / perBlock * perBlock + 1;
		endPage = startPage + perBlock - 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// ??? ??????????????? ????????? ?????? ??????
		start = (currentPage - 1) * perPage;

		List<ResultMapDto> roomList = new ArrayList<ResultMapDto>();

		if (addr_load.isEmpty()) {
			// ??? ??????????????? ????????? ????????? ????????????
			roomList = roomService.getPageRoom(start, perPage);
			mview.setViewName("/room/roomMain");
		} else {
			// ????????? ??????????????? ????????? ??? ????????????
			roomList = roomService.getSearchPageRoom(start, perPage, addr_load, from, to);
			mview.setViewName("/room/roomMain");
			
		}

		for (ResultMapDto dto : roomList) {

			// ????????? ??????
			String photos[] = dto.getRoomDto().getPhotos().split(",");

			RoomDto roomDto = roomService.getRoom(dto.getRoomDto().getNo());
			roomDto.setPhotos(photos[0]);

			dto.setRoomDto(roomDto);
		}

		mview.addObject("totalCount", totalCount);
		mview.addObject("roomList", roomList);
		mview.addObject("totalPage", totalPage);
		mview.addObject("startPage", startPage);
		mview.addObject("endPage", endPage);
		mview.addObject("currentPage", currentPage);

		// ???????????????
		if (loginok != null) {
			List<WishListDto> wishList = wishService.getWishList(myid);

			mview.addObject("wishList", wishList);
		}

		return mview;
	}

	@GetMapping("/insertform")
	public String roomInsertForm() {
		return "/room/roomInsertForm";
	}

	@PostMapping("/insert")
	public String roomInsert(@ModelAttribute RoomDto roomDto, @RequestParam ArrayList<MultipartFile> upload,
			HttpSession session) {
		// ???????????? ?????? ??????
		String path = session.getServletContext().getRealPath("/photo/roomPhoto");

		String photo = "";

		if (upload.get(0).getOriginalFilename().equals("")) {
			photo = "no";
		} else {
			for (MultipartFile f : upload) {
				String fName = f.getOriginalFilename();
				photo += fName + ",";

				// ?????????
				try {
					f.transferTo(new File(path + "\\" + fName));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			photo = photo.substring(0, photo.length() - 1);
		}

		String myid = (String) session.getAttribute("myid");

		roomDto.setHost_id(myid);
		roomDto.setPhotos(photo);

		roomService.insertRoom(roomDto);

		int no = roomService.getRoomMaxNo();

		return "redirect:content?no=" + no;
	}

	@GetMapping("/content")
	public ModelAndView content(@RequestParam String no,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage, HttpSession session) {
		String myid = (String) session.getAttribute("myid");
		String loginok = (String) session.getAttribute("loginok");

		ModelAndView mview = new ModelAndView();

		ResultMapDto dto = roomService.getOneRoom(no);

		String photoList[] = dto.getRoomDto().getPhotos().split(",");

		// ????????? ??????
		String hostId = dto.getRoomDto().getHost_id();

		MemberDto memDto = memberService.getMember(hostId);

		// ???????????????
		if (loginok != null) {
			List<WishListDto> wishList = wishService.getWishList(myid);

			mview.addObject("wishList", wishList);
		}

		// ????????? ??????
		List<ResultMapDto> commentList = gcommentService.getRoomComment(no);

		for (ResultMapDto c : commentList) {
			// ?????? ??????
			String guestId = c.getGcoDto().getGuest_id();

			MemberDto gMemDto = memberService.getMember(guestId);

			c.setMemDto(gMemDto);

			// ????????? ??????
			String reserNo = c.getGcoDto().getNo();

			int likes = likeService.countLike(reserNo, guestId);

			GuestCommentDto gCoDto = gcommentService.getOneComment(reserNo);

			gCoDto.setCountLike(likes);

			c.setGcoDto(gCoDto);
			
			// ????????? ?????? 
			HostCommentDto hcommentDto = hcommentService.getOneHostComment(reserNo);
			
			c.setHcoDto(hcommentDto);
		}

		// ???????????? ?????? ?????????
		List<CommentLikeDto> likeList = likeService.getLike(myid);

		mview.addObject("currentPage", currentPage);
		mview.addObject("dto", dto);
		mview.addObject("photoList", photoList);
		mview.addObject("memDto", memDto);
		mview.addObject("commentList", commentList);
		mview.addObject("likeList", likeList);

		mview.setViewName("/room/roomDetail");

		return mview;
	}

	@GetMapping("/updateform")
	public ModelAndView roomUpdateForm(@RequestParam String no) {
		ModelAndView mview = new ModelAndView();

		RoomDto roomDto = roomService.getRoom(no);

		mview.addObject("roomDto", roomDto);

		mview.setViewName("/room/roomUpdateForm");

		return mview;
	}

	@PostMapping("/update")
	public String roomUpdate(@ModelAttribute RoomDto roomDto, @RequestParam String no,
			@RequestParam ArrayList<MultipartFile> upload, HttpSession session) {
		// ???????????? ?????? ??????
		String path = session.getServletContext().getRealPath("/photo/roomPhoto");

		String photo = "";

		if (upload.get(0).getOriginalFilename().equals("")) {
			photo = null;
		} else {
			String photos = roomService.getRoom(no).getPhotos();
			String photoArray[] = photos.split(",");

			for (int i = 0; i < photoArray.length; i++) {
				File file = new File(path + "/" + photoArray[i]);
				file.delete();
			}

			for (MultipartFile f : upload) {
				String fName = f.getOriginalFilename();
				photo += fName + ",";

				// ?????????
				try {
					f.transferTo(new File(path + "\\" + fName));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			photo = photo.substring(0, photo.length() - 1);
		}

		roomDto.setPhotos(photo);

		roomService.updateRoom(roomDto);

		int totalCount = roomService.getRoomCount();
		int perPage = 5;

		int totalPage = totalCount / perPage + (totalCount % perPage == 0 ? 0 : 1);
		
		session.setAttribute("mode", "guest");

		return "redirect:main?currentPage=" + totalPage;
	}

	@GetMapping("/list")
	public ModelAndView hostRoomList(HttpSession session) {
		ModelAndView mview = new ModelAndView();

		String myid = (String) session.getAttribute("myid");

		List<RoomDto> roomList = roomService.getRoomList(myid);

		mview.addObject("roomList", roomList);

		mview.setViewName("/room/hostRoomList");

		return mview;
	}
}