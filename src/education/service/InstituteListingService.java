package education.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import education.bean.Institute;
import education.bean.Member;
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
		
		int currentyear =	Calendar.getInstance().get(Calendar.YEAR);
		List<Integer> years = new ArrayList<Integer>();
		for(int i= currentyear; i >= 1900; i--){
			years.add(i);
		}
		map.addAttribute("totalyears", years);
		
		session.close();
	}

	public void submitInstituteAddForm(Institute inst,Member mem){
		System.out.println("entered submit institute addform service");
		Session session = factory.openSession();
		
		
		
		OperatorImplementation optimpl = new OperatorImplementation();
		optimpl.addInstitute(session, inst, mem);
		
		session.close();
		
		System.out.println("exit submit institute addform service");
	}

}
