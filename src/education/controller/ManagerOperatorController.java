package education.controller;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import education.bean.Institute;
import education.bean.Member;
import education.service.InstituteListingService;

@Controller
public class ManagerOperatorController {

	@Autowired
	private InstituteListingService instservice;
	
	@RequestMapping(value="/userinterface",method=RequestMethod.GET)
	public String getinterface(ModelMap map,HttpServletRequest request){
		System.out.println("Entered request for userinterface");
		
		HttpSession session  =request.getSession(false);
		instservice.getInstituteListing(map);
		
		
		System.out.println("Exit request for userinterface");
		
		return "operator/mainpage";
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
	
	/**
	 * This Controller called when user clicks on Submit Button after selecting various filters.
	 * @param maincat
	 * @param childcate
	 * @param stateid
	 * @param cities
	 * @param currentpage
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping(value="/filterInstitute",method=RequestMethod.GET)
	public @ResponseBody void filterController(
			@RequestParam(value="maincategory") Integer maincat,
			@RequestParam(value="childcategory") Integer[] childcate,
			@RequestParam(value="states") Integer stateid,
			@RequestParam(value="cities") Integer[] cities,
			@RequestParam(value="cp") Integer currentpage,
			HttpServletRequest request,
			HttpServletResponse response, 
			ModelMap modelMap){
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
	
	
	@RequestMapping(value="/addinstitute",method=RequestMethod.GET)
	public String getAddForm(ModelMap map){
		System.out.println("entered getaddForm controller");
		Institute instbean = new Institute();
		map.addAttribute("instbean", instbean);
		Member memberbean = new Member();
		map.addAttribute("membean", memberbean);
		
		instservice.getInstituteAddForm(map);
		
		System.out.println("exit getaddform controller");
		return "operator/addinst";
	
	}
	@RequestMapping(value="/addinstitute",method=RequestMethod.POST)
	public String submitAddForm(HttpServletRequest req,
			@ModelAttribute("instbean") Institute institute,
			@ModelAttribute("membean") Member member){
		System.out.println("entered submitadd form");
		System.out.println("instbean \naddress=>"+institute.getAddress()+"\nrating=>"+institute.getJosh_rating()+"\nsource=>"+institute.getSource()+"\nkeyword=>"+institute.getKeyword()+"\nabout=>"+institute.getAbout()+"\nyear=>"+institute.getYearoffrom());
		//System.out.println("institute stateid=>"+institute.getState_id().getId()+"\n cityid=>"+institute.getCity_id().getId());
		System.out.println("membean name=>"+member.getName());
		//System.out.println("institute=>"+institute);
		//System.out.println("member=>"+member);
		HttpSession session = req.getSession(false);
		
		if(session != null && session.getAttribute("userid") != null){
			String userid =  session.getAttribute("userid").toString();
			req.setAttribute("userid", userid);
			System.out.println("userid=>"+userid);
				
			instservice.submitInstituteAddForm(institute,member,Integer.parseInt(userid));
			
			return "redirect:/userinterface.do";
		}
		else {
			return "redirect:/login.do";
		}
		
	}
	
	@RequestMapping(value="/editInstitute",method=RequestMethod.GET)
	public String editInst(@RequestParam(value="instid") Integer instituteid,ModelMap map,HttpServletRequest request){
		System.out.println("Entered edit institute get request");
		System.out.println("instituteid=>"+instituteid);
		
		instservice.editInstituteForm(map, instituteid,request);
		
		System.out.println("Exit edit institute get request");
		return "operator/editinst";
	}
	
	@RequestMapping(value="/updateInstitute",method=RequestMethod.POST)
	public String updateInst(HttpServletRequest req){
		System.out.println("Entered update institute update request");
		
		HttpSession session = req.getSession(false);
		
		if(session != null && session.getAttribute("userid") != null){
			String userid =  session.getAttribute("userid").toString();
			req.setAttribute("userid", userid);
			System.out.println("userid=>"+userid);
				
			instservice.updateInstituteForm(req);
			
			return "redirect:/userinterface.do";
		}
		else  
			return "redirect:/login.do";
	}
	
	@RequestMapping(value="addcourse",method=RequestMethod.GET)
	public String getCoursesForm(@RequestParam(value="instid") Integer instituteid,ModelMap map) {
		System.out.println("Entered getcoursesform controller");
		
		instservice.getInstituteCoursesForm(map);
		map.addAttribute("instituteid", instituteid);
		System.out.println("Exit getcoursesform controller");
		return "operator/addcourse";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addCourses(HttpServletRequest request){
		System.out.println("Entered addcourses controller");
		String instituteid = request.getParameter("instituteid");
		String coursename = request.getParameter("coursename");
		String keyword = request.getParameter("coursekeyword");
		String courseleveledu =  request.getParameter("leveledu");
		String coursetypeedu = request.getParameter("typeedu");
		String coursemaincat = request.getParameter("maincat");
		String[] coursechildcat = request.getParameterValues("childcat");
		HttpSession session  = request.getSession(false);
		String userid = session.getAttribute("userid").toString();
		if(userid != null){
			System.out.println("instituteid=>"+instituteid+"coursename=>"+coursename+"\nkeyword=>"+keyword+"\ncourseleveledu=>"+courseleveledu);
			System.out.println("coursetypeedu=>"+coursetypeedu+"\ncoursemaincategory=>"+coursemaincat);
			System.out.println("coursechildcategories=>"+coursechildcat);
			System.out.println("userid=>"+userid);
			for(String current:coursechildcat){
				System.out.println(">>"+current);
			}
			HashMap<String, Object> parameters = new HashMap<String,Object>();
			parameters.put("instituteid", instituteid);
			parameters.put("coursename", coursename);
			parameters.put("coursekeyword", keyword);
			parameters.put("courseleveledu", courseleveledu);
			parameters.put("coursetypedu", coursetypeedu);
			parameters.put("coursemaincat", coursemaincat);
			parameters.put("coursechildcat", coursechildcat);
			parameters.put("createdbyid", userid);
			instservice.submitInstituteCourse(parameters);
			System.out.println("Exit addCourses controller");
			return "redirect:/userinterface.do";

		}
		else 
			return "redirect:/login.do";
	}
}
