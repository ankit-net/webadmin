package education.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import education.service.InstituteListingService;

@Controller
public class ManagerOperatorController {

	@Autowired
	private InstituteListingService instservice;
	
	@RequestMapping(value="/userinterface",method=RequestMethod.GET)
	public void getinterface(ModelMap map){
		System.out.println("Entered request for userinterface");
		instservice.getInstituteListing(map);
		
		System.out.println("Exit request for userinterface");
	}
	@RequestMapping(value="/categorylist",method=RequestMethod.POST)
	public @ResponseBody void getChildCategories(@RequestParam(value="maincategory") Integer maincat,HttpServletResponse response,ModelMap map) 
			throws IOException {
		System.out.println("Entered child categories listing");
		System.out.println("maincategory entered=>"+maincat);
		
		instservice.getChildCategories(maincat,map);
		
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		mapper.writeValue(response.getWriter() , map);
		
		System.out.println("Exit child categories listing");
	}
	
	
	
	@RequestMapping(value="/details",method=RequestMethod.GET)
	public @ResponseBody void getInstituteDetails(@RequestParam(value="instid") Integer instituteid,
			ModelMap map,HttpServletResponse response) throws IOException{
		System.out.println("entered institute details ajax method");
		System.out.println("institute id=>"+instituteid);
		ObjectMapper mapper = new ObjectMapper();
		
		instservice.getDetails(instituteid,map);
		
		response.setContentType("application/json");
		
		mapper.writeValue(response.getWriter(), map);
		
		System.out.println("map size=>"+map.size());
		System.out.println("exit institute details ajax method");
	}
}
