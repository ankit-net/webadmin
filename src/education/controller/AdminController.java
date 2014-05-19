package education.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import education.bean.AdminUser;
import education.service.AdministratorService;

@Controller
public class AdminController {
	
	@Autowired
	private AdministratorService adminservice;
	

	@RequestMapping(value="/admininterface")
	public String admincontroller(ModelMap map,HttpServletRequest request){
		System.out.println("entered admininterface controller");
		HttpSession  session = request.getSession(false);
		if(session.getAttribute("usertype") != null){
			System.out.println("usertype is found =>"+session.getAttribute("usertype"));
		}
		map  = adminservice.adminmodule(map);

		map.addAttribute("myuser", "ankit");
		
		//map.addAttribute("userlist", admimpl.showusers());
		
		System.out.println("exit adminterface controller");
		return "admin/adminview";
	}

	/*This Controller called for pagination on users listing page*/
	@RequestMapping(value="/userspagination")
	public @ResponseBody void callpagination(@RequestParam(value="cp") Integer currentpage,
			HttpServletResponse response) {
		System.out.println("Entered call pagination through AJAX");
		System.out.println("currentpage=>"+currentpage);
		
		HashMap<String, Object> jsonobj =  adminservice.getusersdynamic(currentpage);
	
		response.setContentType("application/json");
		
		ObjectMapper mapper  = new ObjectMapper();
		PrintWriter writer  = null; 
		try {
			writer = response.getWriter();
			mapper.writeValue(writer, jsonobj);
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Exit call pagination through AJAX");
	}
	
	@RequestMapping(value="/createuser",method=RequestMethod.GET)
	public String getcreateUser(ModelMap map){
		System.out.println("Entered create user");
		AdminUser user = new AdminUser();
		map.addAttribute("userobj", user);
		//ModelAndView mav = new ModelAndView();
		//mav.setViewName("createuser");
		//mav.addObject("userobj", user);
		System.out.println("Exit create user");
		//return mav;
		return "admin/createuser";
	}
	
	
	@RequestMapping(value="creatuser",method=RequestMethod.POST)
	public String adduser(@ModelAttribute(value="userobj") AdminUser adminUser,HttpServletRequest request){
		System.out.println("Entered adduser get method"); 
		System.err.println("username=>"+adminUser.getUsername()+"\tpassword=>"+adminUser.getPassword()
				+"\tgroupname=>"+adminUser.getGroupname()+"\temail=>"+adminUser.getEmail()+"\tmobile=>"+
		adminUser.getMobile()+"\tisactive=>"+adminUser.getIsactive());
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("username", adminUser.getUsername());
		parameters.put("password", adminUser.getPassword());
		parameters.put("groupname", adminUser.getGroupname());
		parameters.put("email", adminUser.getEmail());
		parameters.put("mobile", adminUser.getMobile());
		parameters.put("isactive", adminUser.getIsactive());
		
		adminservice.adduser(parameters);
		
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("mainpage");
		System.out.println("Exit adduser get method");
		
		return "redirect:/admininterface.do";
	}
	
	@RequestMapping(value="/edituser/{userid}",method=RequestMethod.GET)
	public String edituser(@PathVariable Integer userid,ModelMap modelMap){ 
		System.out.println("Entered editboard get");	
		AdminUser user = adminservice.edituser(userid);
		modelMap.addAttribute("userobj", user);
		
		System.out.println("Exit editboard get");
		return "admin/edituser";  
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String edituser(@ModelAttribute(value="userobj") AdminUser adminUser){
		System.out.println("Entered editboard post");
		
		adminservice.updateuser(adminUser);
		
		System.out.println("Exit editboard post");
		return "redirect:/admininterface.do";
	}
	
	
}
