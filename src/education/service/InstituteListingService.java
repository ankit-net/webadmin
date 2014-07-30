package education.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import education.bean.Institute;
import education.bean.Member;
import education.bean.TypeEdu;
import education.dao.AdminstratorImplementation;
import education.dao.OperatorImplementation;
import education.util.Commons;

@Service
public class InstituteListingService {

	@Autowired
	private SessionFactory factory;
	
	public void getInstituteListing(ModelMap map){
		System.out.println("entered service method");
		Session session = factory.openSession();
		AdminstratorImplementation adminimpl = new AdminstratorImplementation();
		OperatorImplementation operatorimpl  = new OperatorImplementation();
		List institutelist =	operatorimpl.showInstitutes(session, 1, 10);
		List maincat = operatorimpl.showparentcategories(session);
		List states = adminimpl.showstates(session, 3);
		
		
		
		int count = operatorimpl.countInstitutes(session);
		map.addAttribute("institutes", institutelist);
		map.addAttribute("count", count);
		map.addAttribute("maincategories", maincat);
		map.addAttribute("statelist", states);
		
		session.close();
		System.out.println("exit service method");
	}
	public void getInstituteListingDynamic(ModelMap map,Map<String, Object> parameters){
		System.out.println("entered service method");
		Session session = factory.openSession();
		OperatorImplementation optimpl = new OperatorImplementation();
		
		
		List institutes = optimpl.showInstitutes_Filter(session, parameters); 
		
		map.addAttribute("count", institutes.get(0));
		
		
		institutes.remove(0);
		map.addAttribute("institutes", institutes);
		session.close();
		System.out.println("exit service method");
	}
	
	
	public void getDetails(Integer instituteid,ModelMap map){
		System.out.println("entered service method");
		Session session = factory.openSession();
		OperatorImplementation operatorimpl = new OperatorImplementation();
		//operator impl will call showcourses,show departments,showmedia,showcampuses
	
		map.addAttribute("courses", operatorimpl.showCourses(session, instituteid));
		map.addAttribute("coursecount", operatorimpl.countCourses(session, instituteid));
		map.addAttribute("departments", operatorimpl.showDepartments(session, instituteid));
		map.addAttribute("departmentcount", operatorimpl.countDeparments(session, instituteid));
		map.addAttribute("campuses", operatorimpl.showCampuses(session, instituteid));
		map.addAttribute("campusescount", operatorimpl.countCampuses(session, instituteid));
		map.addAttribute("medialist", operatorimpl.showMediaList(session, instituteid));
		map.addAttribute("countmedia", operatorimpl.countMedia(session, instituteid));
		
		session.close();
		System.out.println("exit service method");
	}
	
	public void getChildCategories(Integer parentcatid,ModelMap map){
		 System.out.println("Entered getchildcategories service");
		 Session session = factory.openSession();
		 
		 OperatorImplementation operatorimpl = new OperatorImplementation();
		 map.addAttribute("childcat", operatorimpl.showchildcategories(session, parentcatid));
		 
		 session.close();
		 System.out.println("exit getchildcategories service");
	}
	
	public void getCitiesDynamic(Integer stateid,ModelMap map) {
		System.out.println("entered getcitiesdynamic service");
		AdminstratorImplementation adminimpl = new AdminstratorImplementation();
		Session session = factory.openSession();
		List cities =  adminimpl.showcities(session, stateid, 3, 1, -1);
		List citieslist = new ArrayList();
		for(Iterator itr = cities.iterator();itr.hasNext();){
			Object[] citybean =(Object[]) itr.next();
			HashMap<String, Object> citiesmap = new HashMap<String,Object>();
			citiesmap.put("id", citybean[0]);
			citiesmap.put("name", citybean[1]);
			
			citieslist.add(citiesmap);
		}
		
		map.addAttribute("cities", citieslist);
		
		System.out.println("exit getcitiesdynamic service");
	}

	public void getInstituteAddForm(ModelMap map) {
		System.out.println("entered institute get addform");
		Session session = factory.openSession();
		AdminstratorImplementation adminimpl = new AdminstratorImplementation();
		map.addAttribute("insttypes", adminimpl.showInstitutetypes(session, -1, -1));
		map.addAttribute("usertypes", adminimpl.showusertypes(session, -1, -1));
		map.addAttribute("states", adminimpl.showstates(session, 3));
		map.addAttribute("leveledu", adminimpl.showlevelofeducation(session, -1, -1));
		map.addAttribute("typeedu", adminimpl.showtypeofeducation(session, -1, -1));
		
		int currentyear =	Calendar.getInstance().get(Calendar.YEAR);
		List<Integer> years = new ArrayList<Integer>();
		for(int i= currentyear; i >= 1900; i--){
			years.add(i);
		}
		map.addAttribute("totalyears", years);
		
		session.close();
	}

	
	public void submitInstituteAddForm(HttpServletRequest request){
		System.out.println("entered submit institute addform service");
		Session session = factory.openSession(); 
		
		OperatorImplementation optimpl = new OperatorImplementation();
		optimpl.addInstitute(session, request);
		
		session.close();
		
		System.out.println("exit submit institute addform service");
	}

	public void editInstituteForm(ModelMap map,int instituteid,HttpServletRequest request){
		System.out.println("entered editinstitute form");
		Session session = factory.openSession();
		AdminstratorImplementation adminimpl = new AdminstratorImplementation();
		map.addAttribute("insttypes", adminimpl.showInstitutetypes(session, -1, -1));
		map.addAttribute("usertypes", adminimpl.showusertypes(session, -1, -1));
		map.addAttribute("states", adminimpl.showstates(session, 3));
		
		List finalleveledu = new ArrayList<>();
		List finaltypeedu = new ArrayList<>();
		
		List alltypeedu = adminimpl.showtypeofeducation(session, -1, -1);
		
		
		OperatorImplementation optimpl = new OperatorImplementation();
		HashMap<String, Object> institutedetailmap = optimpl.editInstitute(session, instituteid);
		
		List<Integer> mytypedu = (List<Integer>) institutedetailmap.get("typeedulist");
		
		for(Iterator itr=alltypeedu.iterator();itr.hasNext();){
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			HashMap<String, Object> typebean = (HashMap<String, Object>) itr.next();
			
			
			typebean.put("isactive", 0);
			
			if(mytypedu.contains(typebean.get("id")) ) {
				//this means checked typeedu is found on the collection.. :)
				typebean.put("isactive", 1);
			}
			System.out.println("id=>"+typebean.get("id")+"\tname=>"+typebean.get("name")+"\tisactive=>"+typebean.get("isactive"));
			finaltypeedu.add(typebean);
		}
		
		map.addAttribute("typeedu", finaltypeedu);
		
		List<Integer> myleveledu = (List<Integer>) institutedetailmap.get("leveledulist");
		List allleveledu = adminimpl.showlevelofeducation(session, -1, -1);
		for(Iterator itr = allleveledu.iterator();itr.hasNext();) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			HashMap<String, Object> levelbean = (HashMap<String, Object>) itr.next();
	

			levelbean.put("isactive", 0);
			
			if(myleveledu.contains(levelbean.get("id"))){
				//this means checked leveledu is found on the collection.. :)
				levelbean.put("isactive", 1);
			}
			System.out.println("id=>"+levelbean.get("id")+"\tname=>"+levelbean.get("name")+"\tisactive=>"+levelbean.get("isactive"));
			finalleveledu.add(levelbean);
		}
		map.addAttribute("leveledu", finalleveledu);
		
		int currentyear =	Calendar.getInstance().get(Calendar.YEAR);
		List<Integer> years = new ArrayList<Integer>();
		for(int i= currentyear; i >= 1900; i--){
			years.add(i);
		}
		map.addAttribute("totalyears", years);
		
		//selecting institute details and cities
		
		
		Integer selectedstate =Integer.parseInt(institutedetailmap.get("stateid").toString());
		map.addAttribute("cities",adminimpl.showcities(session, selectedstate, 3, -1, -1));
		map.addAttribute("institutedetails", institutedetailmap);
		//System.out.println(adminimpl.showcities(session, selectedstate, 3, -1, -1).toString());
		
		
		request.setAttribute("mytypeedu", mytypedu);
		
		session.close();
		System.out.println("exit editinstitute form");
	}
	public void updateInstituteForm(HttpServletRequest req){
		System.out.println("entered update service method");
		Session session = factory.openSession();
		
		OperatorImplementation optimpl = new OperatorImplementation();
		optimpl.updateInstitute(session, req);
		
		session.close();
		System.out.println("exit update service methods");
	}
	
	public void getInstituteCoursesForm(ModelMap map){
		System.out.println("entered courses service method");
		AdminstratorImplementation adminimpl = new AdminstratorImplementation();
		Session session = factory.openSession();
		map.addAttribute("typeedulist", adminimpl.showtypeofeducation(session, -1, 10));
		map.addAttribute("leveledulist", adminimpl.showlevelofeducation(session, -1, 10));
		
		OperatorImplementation optimpl  = new OperatorImplementation();
		map.addAttribute("parentcat", optimpl.showparentcategories(session));
		
		
		session.close();
		System.out.println("exit courses service method");
	}
	
	public void submitInstituteCourse(HashMap<String, Object> parameters){
		System.out.println("Entered submitInsituteCourse service");
		Session session = factory.openSession();
		OperatorImplementation optimpl = new OperatorImplementation();
		
		optimpl.addCourse(session, parameters);
		session.close();
		System.out.println("Exit submitInstituteCourse service");
	}
}
