package com.ports.controller;

import com.ports.bean.PageModel;
import com.ports.bean.Users;
import com.ports.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UsersController {
	@Autowired
	private UsersServiceImpl service;

	@RequestMapping(value={"/users"},method = RequestMethod.GET)
	public ModelAndView get(@RequestParam(required=false) String name,
							@RequestParam(required=false)String sex,
							@RequestParam(required=false,defaultValue = "1")int go,
							@RequestParam(required=false,defaultValue = "20")int page,
							HttpServletRequest request) throws Exception{
		System.out.println(request.getCharacterEncoding());
		Map<String, String> pageMap = new HashMap<String, String>();
		pageMap.put("name", name);
		if (sex!=null)pageMap.put("sex", URLDecoder.decode(sex, "UTF-8"));

		int userCount = (int) service.userCount(pageMap);


		PageModel<Users> pageModel = new PageModel<Users>(userCount, page, pageMap);
		pageModel.setGotoPage(go);

		List<Users> users =service.selectPage(pageModel);
		ModelAndView model=new ModelAndView("/user/users");
		model.addObject("users",users);
		model.addObject("page",pageModel);
		return model;
	}

	//@RequestBody实现序列化注解
	@RequestMapping(value={"/user"},method = RequestMethod.POST)
	public ModelAndView post(@ModelAttribute  Users user){
			user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			service.add(user);
		return new ModelAndView("redirect:/users");
	}
	@RequestMapping(value={"/user/add"},method = RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView model = new ModelAndView("user/form");
		model.addObject("user",new Users());
		return model;
	}

	@RequestMapping(value={"/user/{id}"},method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("id") String id){
		ModelAndView model = new ModelAndView("user/form");
		model.addObject("user",service.queryById(id));
		return model;
	}
	
	@RequestMapping(value={"/user/{id}"},method = RequestMethod.POST)
	public ModelAndView update(@PathVariable("id") String id,Users user){

		if (user.getId()==null||user.getId().length()<1)user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		service.updateBySelective(user);
		return new ModelAndView("redirect:/users");
	}
	
	@RequestMapping(value={"/user/{id}"},method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") String id){
		service.deleteById(id);
		return new ModelAndView("redirect:/users");
	}
}
