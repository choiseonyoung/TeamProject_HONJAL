package com.honjal.honjal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honjal.honjal.model.CommentVO;
import com.honjal.honjal.model.ContentVO;
import com.honjal.honjal.model.GoodVO;
import com.honjal.honjal.model.MemberVO;
import com.honjal.honjal.service.CommentService;
import com.honjal.honjal.service.ContentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
	
	protected final ContentService contentService;
	protected final CommentService commentService;

	@RequestMapping(value={"/{menu}","/{menu}/"}, method=RequestMethod.GET)
	public String board(@PathVariable("menu") String menu, Model model, HttpSession session, 
						@RequestParam(value="pageNum", required=false, defaultValue="1") String pageNum) {
		
		String menu_str = menu.toUpperCase();
		String[] menu_arr = menu_str.split("-");
		// TIP-1 이면(말머리별 보기)  menu_arr 배열에 TIP, 1 이렇게 2개가 담김
		if(menu_arr.length > 1) menu_str = menu_arr[0];
		// /TIP-1로 넘어오면 menu_str에 TIP만 담김
		
		int intPageNum = Integer.valueOf(pageNum);
		if(intPageNum > 0) {
			model.addAttribute("PAGE_NUM", intPageNum);
		}
		contentService.contentMenuAllPage(menu, intPageNum, model);
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = sd.format(date);
		model.addAttribute("TODAY", curDate);
		model.addAttribute("MENU", menu_str);
		model.addAttribute("BODY", "BOARD_MAIN");
		return "home";
		
	}
	
	@RequestMapping(value="/{menu}/write", method=RequestMethod.GET)
	public String write( @PathVariable("menu") String menu,  Model model, HttpSession session) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute("MEMBER");
		
		ContentVO contentVO = ContentVO.builder().member_num(memberVO.getMember_num()).member_nname(memberVO.getMember_nname()).build();
		
		model.addAttribute("CONTENT", contentVO);
		model.addAttribute("BODY", "WRITE");
		model.addAttribute("MENU",menu.toUpperCase());
		return "home";
	}
	
	@RequestMapping(value="/{menu}/write", method=RequestMethod.POST)
	public String write(String bcode, ContentVO contentVO) throws Exception {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
		String curDate = sd.format(date);
		String curTime = st.format(date);
		
		contentVO.setBoard_code(bcode);
		contentVO.setContent_date(curDate);
		contentVO.setContent_time(curTime);
		contentVO.setContent_view(0);
		contentVO.setContent_good(0);
		
		contentService.insert(contentVO);
		return "redirect:/board/{menu}";
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String read(Integer content_num, Model model, HttpSession session) {
		ContentVO contentVO = contentService.findByIdContent(content_num);
		String bcode = contentVO.getBoard_code().substring(0, 3);
		
		MemberVO memberVO = (MemberVO) session.getAttribute("MEMBER");
		if (memberVO != null) {
			GoodVO goodVO = GoodVO.builder().content_num(content_num).member_num(memberVO.getMember_num()).build();
			model.addAttribute("GOOD", contentService.checkGood(goodVO));
		}
		
		contentVO = contentService.findByIdContent(content_num);
		model.addAttribute("CONTENT", contentVO);
		List<CommentVO> commentList = commentService.selectAll();
		log.debug("댓글리스트{}", commentList.toArray());
		model.addAttribute("COMMENT", commentList);
		model.addAttribute("BODY", "READ");
		
		contentService.view_count(contentVO);
		
		model.addAttribute("MENU",bcode);
		model.addAttribute("CONTENT",contentVO);
		model.addAttribute("BODY", "READ");
		return "home";
	}
	
	// @ResponseBody
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public String comment(Integer comment_num, Integer content_num, CommentVO commentVO, HttpSession session)
			throws Exception {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
		String curTime = st.format(date);
		commentVO.setComment_time(curTime);

		MemberVO memberVO = (MemberVO) session.getAttribute("MEMBER");
		commentVO.setComment_writer(memberVO.getMember_nname());

		commentService.insert(commentVO);
		return "redirect:/board/read?content_num=" + content_num;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(Integer content_num, Model model) {
		ContentVO contentVO = contentService.findByIdContent(content_num);
		
		
		model.addAttribute("CONTENT",contentVO);
		model.addAttribute("BODY", "UPDATE");
		return "home";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(ContentVO contentVO, Model model) throws Exception {
		contentService.update(contentVO);
		model.addAttribute("content_num", contentVO.getContent_num());
		return "redirect:/board/read";
	}
	
	@RequestMapping(value="/{menu}/delete", method=RequestMethod.GET)
	public String delete(Integer content_num, Model model) throws Exception {
		contentService.delete(content_num);
		return "redirect:/board/{menu}";
	}
	
//	@RequestMapping(value="/{menu}/search/{type}", method=RequestMethod.GET)
//	public String search(@PathVariable("menu") String menu, String search_word, Model model) throws Exception {
//		contentService.searchTitleContent(menu, search_word);
//		model.addAttribute("BODY", "BOARD_MAIN");
//		return "home";
//	}
	
	@ResponseBody
	@RequestMapping(value="/good/{content_num}", method=RequestMethod.GET)
	public String good(@PathVariable("content_num") Integer content_num, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute("MEMBER");
		if(memberVO == null) {
			return "NULL";			
		}
		
		GoodVO goodVO = GoodVO.builder().content_num(content_num).member_num(memberVO.getMember_num()).build();
		
		if(contentService.checkGood(goodVO) == 0) {
			contentService.insertGood(goodVO);
			return "INSERT";
		} else {
			contentService.deleteGood(goodVO);
			return "DELETE";
		}
	}
	
	
	
}