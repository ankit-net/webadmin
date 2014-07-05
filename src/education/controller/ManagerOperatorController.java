package education.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import education.bean.AdminUser;
import education.bean.City;
import education.bean.Country;
import education.bean.Institute;
import education.bean.Institute_Type;
import education.bean.Member;
import education.bean.State;
import education.bean.UserType;
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
		
		instservice.getInstituteAddForm(map);
		
		System.out.println("exit getaddform controller");
		return "operator/addinst";
	}
	
	@RequestMapping(value="/addinstitute",method=RequestMethod.POST)
	public String submitAddForm(HttpServletRequest req){
		System.out.println("entered submitadd form");
		String usertype = req.getParameter("usertype");
		String email = req.getParameter("email");
		String institutename = req.getParameter("name");
		String rating = req.getParameter("rating");
		String phone = req.getParameter("phone");
		String insttype = req.getParameter("institutetype");
		String year  = req.getParameter("year");
		String about = req.getParameter("about");
		String state = req.getParameter("allstates");
		String city= req.getParameter("allcitieslist");
		HttpSession session = req.getSession(false);
		System.out.println("institute about=>"+about+"\ninstitute name=>"+institutename);
		if(session != null && session.getAttribute("userid") != null){
			String userid =  session.getAttribute("userid").toString();
			
			System.out.println("userid=>"+userid);
			
			Institute_Type typebean = new Institute_Type();
			typebean.setId(Integer.parseInt(insttype));
			
			State stbean = new State();
			stbean.setId(Integer.parseInt(state));
			
			City citybean = new City();
			citybean.setId(Integer.parseInt(city));
			
			Country ctbean = new Country();
			ctbean.setId(3);
			
			AdminUser userbean = new AdminUser();
			userbean.setId(Integer.parseInt(userid));
			
			Institute instbean = new Institute();
			instbean.setJosh_rating(rating);
			instbean.setInstype(typebean);
			instbean.setYearoffrom(Integer.parseInt(year));
			instbean.setAbout(about);
			instbean.setState_id(stbean);
			instbean.setCity_id(citybean);
			instbean.setCountry_id(ctbean);
			instbean.setCreated_date(new Date());
			instbean.setCreated_by_id(userbean);
			
			UserType utbean = new UserType();
			utbean.setId(Integer.parseInt(usertype));
			
			Member memberbean = new Member();
			memberbean.setUser_type_id(utbean);
			memberbean.setEmail(email);
			memberbean.setName(institutename);
			memberbean.setPhone(phone);
			memberbean.setCreated_date(new Date());
		
			instservice.submitInstituteAddForm(instbean,memberbean);
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("usertype=>"+usertype+"\temail=>"+email+"\tinstitutename=>"+institutename+"\trating=>"+rating);
			System.out.print("phone=>"+phone+"\tinsttype=>"+insttype+"\tyear=>"+year+"\tabout=>"+about+"\tstate=>"+state+"\tcity=>"+city);
					
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("exit submit add form");
			return "redirect:/userinterface.do";
		}
		else {
			return "redirect:/login.do";
		}
		
	}
	
	@RequestMapping(value="/editInstitute",method=RequestMethod.GET)
	public String editInst(@RequestParam(value="instid") Integer instituteid,ModelMap map){
		System.out.println("Entered edit institute get request");
		System.out.println("instituteid=>"+instituteid);
		
		instservice.editInstituteForm(map, instituteid);
		
		System.out.println("Exit edit institute get request");
		return "operator/editinst";
	}
	
	@RequestMapping(value="/updateInstitute",method=RequestMethod.POST)
	public String updateInst(HttpServletRequest req){
		System.out.println("Entered update institute update request");
		String instituteid = req.getParameter("id");
		String memberid = req.getParameter("memid");
		String usertype = req.getParameter("usertype");
		String email = req.getParameter("email");
		String institutename = req.getParameter("name");
		String rating = req.getParameter("rating");
		String phone = req.getParameter("phone");
		String insttype = req.getParameter("institutetype");
		String year  = req.getParameter("year");
		String about = req.getParameter("about");
		String state = req.getParameter("allstates");
		String city= req.getParameter("allcitieslist");
		
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("userid") != null){
			String userid =  session.getAttribute("userid").toString();
			
			System.out.println("userid=>"+userid+"\tinstituteid=>"+instituteid+"\tmemberid=>"+memberid);
			
			Institute_Type typebean = new Institute_Type();
			typebean.setId(Integer.parseInt(insttype));
			
			State stbean = new State();
			stbean.setId(Integer.parseInt(state));
			
			City citybean = new City();
			citybean.setId(Integer.parseInt(city));
			
			Country ctbean = new Country();
			ctbean.setId(3);
			
			AdminUser userbean = new AdminUser();
			userbean.setId(Integer.parseInt(userid));
			
			Institute instbean = new Institute();
			instbean.setId(Integer.parseInt(instituteid));
			instbean.setJosh_rating(rating);
			instbean.setInstype(typebean);
			instbean.setYearoffrom(Integer.parseInt(year));
			instbean.setAbout(about);
			instbean.setState_id(stbean);
			instbean.setCity_id(citybean);
			instbean.setCountry_id(ctbean);
			instbean.setCreated_date(new Date());
			instbean.setCreated_by_id(userbean);
			
			UserType utbean = new UserType();
			utbean.setId(Integer.parseInt(usertype));
			
			Member memberbean = new Member();
			memberbean.setId(Integer.parseInt(memberid));
			memberbean.setUser_type_id(utbean);
			memberbean.setEmail(email);
			memberbean.setName(institutename);
			memberbean.setPhone(phone);
			memberbean.setCreated_date(new Date());
		
			instbean.setMember_id(memberbean);
			instservice.updateInstituteForm(instbean, memberbean);
		}
		
		System.out.println("Exit update institute update request");
		return "redirect:/userinterface.do";
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
