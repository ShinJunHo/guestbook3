package com.hanains.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanains.guestbook.dao.GuestBookDao;
import com.hanains.guestbook.vo.GuestBookVo;


@Controller
public class GuestBookController {

	@Autowired
	private GuestBookDao guestbookDao;
	//URL 보내기 초기화면. 
	@RequestMapping("/")
	public String index(Model model){
		List<GuestBookVo> list = guestbookDao.getList();
		model.addAttribute("list",list);
		
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping(value="/add",method= RequestMethod.POST)
	public String add(@ModelAttribute GuestBookVo vo){
//		settter getter 할때는 파라미터 네임이 같아야 한다.
		guestbookDao.insert(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestBookVo vo){
//		settter getter 할때는 파라미터 네임이 같아야 한다.
		guestbookDao.delete(vo);
		return "redirect:/";
	}
	//delete화면으로 옮기기.
	@RequestMapping("/deleteform")
	public String deleteform(){
		return "/WEB-INF/views/deleteform.jsp";
	}
}
