package education.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import education.bean.AdminUser;
import education.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public ModelAndView getloginpage(){
		System.out.println("Entered getloginpage");
		ModelAndView modelobj = new ModelAndView();
		modelobj.setViewName("login");
		modelobj.addObject("loginobj", new AdminUser());
		return modelobj;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submitloginpage(@ModelAttribute ("loginobj") AdminUser admin,ModelMap map,HttpServletRequest request){
		System.out.println("Entered submitloginpage");
		
		String returnurl = 	loginService.validateUser(admin,request);	
	
		if(returnurl == null){
			System.out.println("this means no user found");
			map.addAttribute("message", "Invalid UserName or Password");
	
			return "login";
		}
		else {
			 
			
			return "redirect:/"+returnurl+".do";
		} 
	}
}
