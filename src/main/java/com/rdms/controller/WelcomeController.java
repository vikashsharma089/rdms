package com.rdms.controller;

import java.util.HashMap;
import java.util.Map;

import com.rdms.model.Users;
import com.rdms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public String login(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Map<String,Object> details = (Map<String, Object>) authentication.getDetails();
            Users users = (Users) details.get("userDetail");
            httpSession.setAttribute("emailId", users.getEmail());
            httpSession.setAttribute("sno", users.getId());
            httpSession.setAttribute("userId", users.getName());
            httpSession.setAttribute("village",users.getVillage().getVillageName());
            httpSession.setAttribute("profilePic", "");
            return "home";
        }else {
            modelAndView.setViewName("login");
        }
        return "login";
    }

    @RequestMapping(value={ "/home"}, method = RequestMethod.GET)
    public String welcome(HttpServletRequest request, ModelAndView modelAndView){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        try {
            Map<String,Object> details = (Map<String, Object>) auth.getDetails();
            Users users = (Users) details.get("userDetail");
            HttpSession httpsession =request.getSession(true);

            httpsession.setAttribute("emailId", users.getEmail());
            httpsession.setAttribute("sno", users.getId());
            httpsession.setAttribute("userId", users.getName());
            httpsession.setAttribute("name",users.getName());
            httpsession.setAttribute("village",users.getVillage().getVillageName());

            httpsession.setAttribute("profilePic", "");

            return "home";
        } catch (Exception e) {
            //logger.info("Exception in LoginController:" + e.getMessage());
            return "login";
        }
    }

    @RequestMapping(value={"/registraion"}, method = RequestMethod.GET)
    public String egister(HttpSession httpSession){
        return "registraion";
    }
    @RequestMapping(value = "/loadConfiguration", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadAll(){
		Map<String, Object> response = new HashMap();
		//response.put("data", rationCardService.getAllRationCardByVillageId(1));
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
}
