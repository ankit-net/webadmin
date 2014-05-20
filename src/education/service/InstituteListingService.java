package education.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import education.dao.OperatorImplementation;

@Service
public class InstituteListingService {

	@Autowired
	private SessionFactory factory;
	
	public void getInstituteListing(ModelMap map){
		System.out.println("entered service method");
		Session session = factory.openSession();
		
		OperatorImplementation operatorimpl  = new OperatorImplementation();
		List institutelist =	operatorimpl.showInstitutes(session, 1, 10);
		
		int count = operatorimpl.countInstitutes(session);
		map.addAttribute("institutes", institutelist);
		map.addAttribute("count", count);
		
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
}
