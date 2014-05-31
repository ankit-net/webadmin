package education.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
	public @ResponseBody void getChildCategories(@RequestParam(value="maincategory") Integer maincat,
												  HttpServletResponse response,
												  ModelMap map) throws IOException 
	{
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
	
	/*
	 * This Controller called when user clicks on Submit Button after selecting various filters.
	 */
	@RequestMapping(value="/filterInstitute",method=RequestMethod.GET)
	public @ResponseBody void filterController(
			@RequestParam(value="maincategory") Integer maincat,
			@RequestParam(value="childcategory") Integer[] childcate,
			@RequestParam(value="states") Integer stateid,
			@RequestParam(value="cities") Integer[] cities,
			@RequestParam(value="cp") Integer currentpage,
			
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		System.out.println("Entered filter Institutes");
		//System.out.println(request.getParameterMap().toString());
		System.out.println(maincat+"\t"+childcate+"\t"+stateid+"\t"+cities+"\t"+currentpage);
		
		HashMap<String, Object> allparameters = new HashMap<>();
		allparameters.put("maincategory", maincat);
		allparameters.put("childcategories", childcate);
		allparameters.put("stateid", stateid);
		allparameters.put("cities", cities);
		allparameters.put("currentpage", currentpage);
		ObjectMapper mapper = new ObjectMapper();
		
		response.setContentType("application/json");
		
		
		System.err.println(allparameters.toString());
		instservice.getInstituteListingDynamic(modelMap, allparameters);
		try {
			mapper.writeValue(response.getWriter(), modelMap);
		}
		catch (IOException iex){
			System.out.println(iex.toString());
		}
		System.out.println("Exit filter Institutes");
		
	}
	
}
