package com.ports.controller;

import com.ports.ws.ServerWsOperation;
import com.ports.ws.WebSocketConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class WebSocketController {
    private static Logger logger = LoggerFactory.getLogger("Websocket ");
    @Autowired
    ServerWsOperation ws;

    @RequestMapping(value = {"/ws"})
    public String ws() {

        return "ws/login";
    }

    @RequestMapping(value = {"/ws/login"}, method = RequestMethod.POST)
    public ModelAndView wsLogin(HttpSession session, @RequestParam String user, String password) {
        ModelAndView mav = new ModelAndView("ws/webSocket");
        mav.addObject("time", new Date());
        mav.getModel().put("wsUser", user);
        session.setAttribute(WebSocketConfig.SESSION_WEBSOCKET, user);
        logger.info("login:" + user);
        return mav;
    }

    @RequestMapping("/ws/login11111/{userId}")
    public @ResponseBody
    String login(HttpServletRequest request, HttpSession session, @PathVariable("userId") Integer userId) {
        session.setAttribute("userId", userId);
        System.out.println(session.getAttribute("userId"));

        return "success";
    }

    @RequestMapping(value = {"/ws/message"}, method = RequestMethod.GET)
    public String message() {
        return "ws/message";
    }
    @RequestMapping(value = {"/ws/message"}, method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(String message) {
        ws.sendMessageToUsers(new TextMessage(message));
        return "success";
    }
}
