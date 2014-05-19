package education.controller;

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
	
	@RequestMapping(value="/details",method=RequestMethod.GET)
	public @ResponseBody void getInstituteDetails(@RequestParam(value="instid") Integer instituteid){
		System.out.println("entered institute details ajax method");
		System.out.println("institute id=>"+instituteid);
		
		instservice.getDetails(instituteid);
		
		
		System.out.println("exit institute details ajax method");
	}
}
