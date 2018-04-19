package com.ports.controller;

import com.ports.ws.WebSocketConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
public class TestController {
	@RequestMapping(value={"/test/params"},method = RequestMethod.GET)
	public String params(@RequestParam int id, Map<String,Object> map){
		System.out.println("id: "+id);
		map.put("word", "word");
		return "test/hello";
	}
	//值传递我们使用这种方法 Model为传出的参数，当我们需要相应的request等时，只需定义参数就可以得到
	@RequestMapping(value={"/test/paramsModel"},method = RequestMethod.GET)
	public String paramsModel(HttpServletRequest request, Model model){
		System.out.println(request.getParameter("id"));
		model.addAttribute("word", "word");
		//传出Object类型的参数，取值方式就是对应类型作为key，这里是=> string
		model.addAttribute("object");
		model.addAttribute("size",100);
		return "test/hello";
	}

	@RequestMapping(value={"/test/ws/dddlogin"},method= RequestMethod.POST)
	public String wsLogin(HttpSession session, @RequestParam String user, String password, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("ws/message");
		mav.addObject("time", new Date());
		mav.getModel().put("wsUser", user);

		//request.setAttribute("wsUser",user);

		session.setAttribute(WebSocketConfig.SESSION_WEBSOCKET, user);
		System.out.println(session.getAttribute(WebSocketConfig.SESSION_WEBSOCKET));

		return "ws/message";
	}
}
