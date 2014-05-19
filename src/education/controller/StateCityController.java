package education.controller;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import education.bean.State;
import education.service.AdministratorService;

@Controller
public class StateCityController {

	@Autowired
	private AdministratorService service;
	
	@RequestMapping(value="/citylist.do",method=RequestMethod.POST)
	public @ResponseBody void citylisting(@RequestParam("stateid") int stateid,@RequestParam("currentpage") int currentpage
			,HttpServletRequest request,HttpServletResponse response){
		System.out.println("Entered citylisting"); 
		
		System.out.println("state->"+stateid);
		
		HashMap<String, Object> citiesmap= service.getcities(stateid,currentpage);
		
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> returnobj = new HashMap<String, Object>();
		returnobj.put("cities", citiesmap);
		response.setContentType("application/json");
		try {
			mapper.writeValue(response.getWriter(), returnobj);
		}
		catch (IOException ioex){
			ioex.printStackTrace();
		}
		
		System.out.println("Exit citylisting");
	}
	@RequestMapping(value="createstate",method=RequestMethod.GET)
	public String createstateget(ModelMap map){
		System.out.println("Entered create state get");
		State statebean = new State();
		map.addAttribute("state", statebean);
		
		System.out.println("Exit create state get");
		return "admin/statecreate";
	}
	
	@RequestMapping(value="createstate",method=RequestMethod.POST)
	public String createstatepost(@ModelAttribute(value="state") State state) {
		System.out.println("Entered create state post");
		
		service.addstate(state);
		
		System.out.println("Exit create state post");
		return "redirect:/admininterface.do";
	}
	
	@RequestMapping(value="editstate/{stateid}",method=RequestMethod.GET)
	public String editstate(@PathVariable(value="stateid") Integer stateid,ModelMap map){
		System.out.println("Entered editstate get method");
		State statebean = service.editstate(stateid);
		
		map.addAttribute("bean", statebean);
		
		System.out.println("Exit editstate get method");
		return "admin/stateedit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String updatestate(State state){
		System.out.println("Entered updatestate method");
		
		System.out.println("id=>"+state.getId()+"\tname=>"+state.getName()+"\tisactive=>"+state.getIsactive());
		
		service.updatestate(state);
		System.out.println("Exit updatestate method");
		return "redirect:/admininterface.do";
	}

	@RequestMapping(value="createcity",method=RequestMethod.GET)
	public String createcityget(ModelMap map){
		System.out.println("entered city get");
		map = service.getCreateCity(map);
		
		
		  
		System.out.println("exit city get");
		return "admin/citycreate";
	}
}
