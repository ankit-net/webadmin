package education.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import education.bean.AdminUser;
import education.bean.City;
import education.bean.State;
import education.dao.AdminstratorImplementation;
import education.util.Commons;


@Service
public class AdministratorService {
	
	@Autowired
	private SessionFactory factory;
	
	final int recordPerPage = 10;
	
	public ModelMap adminmodule(ModelMap map){
		System.out.println("Entered Admin Service");

		int currentPage = 1;
		Session session =  factory.openSession();
		AdminstratorImplementation adminmpl = new AdminstratorImplementation();
		int userscount = adminmpl.countusers(session);
		List states = adminmpl.showstates(session, 3); 
		List<Object> userslist =  adminmpl.showusers(session,currentPage,recordPerPage);
		HashMap<String, Object> mapforpagination = Commons.addPagination(currentPage, userscount, recordPerPage);

		map.addAttribute("totalPageusers",mapforpagination.get("totalpage"));
		map.addAttribute("totalRecord",userscount);
		map.addAttribute("currentpage", currentPage);
		
		List<Object> userscollection  = new ArrayList<Object>();
		
		
		
		for(Iterator<Object> itr = userslist.iterator();itr.hasNext();){
			Object[] currentuser = (Object[]) itr.next();
			HashMap<String, Object> usersmap = new HashMap<String, Object>();
			usersmap.put("id", currentuser[0]);
			usersmap.put("username", currentuser[1]);
			usersmap.put("password", currentuser[2]);
			usersmap.put("groupname", currentuser[3]); 
			usersmap.put("email", currentuser[4]);
			usersmap.put("mobile", currentuser[5]);
			usersmap.put("isactive", currentuser[6]);
			
			userscollection.add(usersmap);
		}
		
		
		
		map.addAttribute("users", userscollection);
		map.addAttribute("countusers", userscount);
		map.addAttribute("states", states);
		session.close();
		System.out.println("Exit Admin Service");
		return map;
	}
	
	public HashMap<String, Object> getusersdynamic(int currentpage){
		HashMap<String, Object> objectsmap = new HashMap<String, Object>();
		List<Object> userscollection  = new ArrayList<Object>();
		Session session = factory.openSession();		
		AdminstratorImplementation adminmpl = new AdminstratorImplementation();
		List users = adminmpl.showusers(session, currentpage, recordPerPage);
		for(Iterator itr = users.iterator();itr.hasNext();){
			Object[] currentuser = (Object[]) itr.next();
			HashMap<String, Object> usersmap = new HashMap<String, Object>();
			usersmap.put("id", currentuser[0]);
			usersmap.put("username", currentuser[1]);
			usersmap.put("password", currentuser[2]);
			usersmap.put("groupname", currentuser[3]); 
			usersmap.put("email", currentuser[4]);
			usersmap.put("mobile", currentuser[5]);
			usersmap.put("isactive", currentuser[6]);
	
			userscollection.add(usersmap);
			
		}
		
		int userscount = adminmpl.countusers(session);
		HashMap<String, Object> mapforpagination = Commons.addPagination(currentpage, userscount, recordPerPage);
				

		
		objectsmap.put("users", userscollection);
		objectsmap.put("userscount", userscount);
		objectsmap.put("totalpages", mapforpagination.get("totalpage"));
		objectsmap.put("currentpage", currentpage);
		session.close();
		return objectsmap;		
	}
	
	public void adduser(HashMap<String, Object> parameters) {
		System.out.println("Entered adduser service");
		Session session= factory.openSession();
		AdminstratorImplementation adminimpl = new AdminstratorImplementation();
	
		adminimpl.createuser(session,parameters);
	
		session.close();
		System.out.println("Exit adduser service");
	}
	
	public HashMap<String, Object> getcities(int stateid,int currentpage){
		HashMap<String, Object> citiesmapjson = new HashMap<String, Object>();
		
		System.out.println("Entered getcities service");
		Session session = factory.openSession();
		AdminstratorImplementation adminimpl = new AdminstratorImplementation();
		List citieslist = adminimpl.showcities(session, stateid, 3,currentpage,recordPerPage );
		HashMap<String, Object> paginationmap = null;
		
		int countcities  = adminimpl.countcities(session, stateid, 3);
		
		citiesmapjson.put("citieslists", citieslist);
		citiesmapjson.put("count", countcities);
		paginationmap = Commons.addPagination(currentpage, countcities, recordPerPage);
		citiesmapjson.put("totalpages", paginationmap.get("totalpage"));
		citiesmapjson.put("currentpage", currentpage);
		session.close();
		System.out.println("Exit getcities service");
		return citiesmapjson;
	}


	public AdminUser edituser(Integer userid){
		System.out.println("service method entered");
		System.out.println("user id=>"+userid);
		AdminstratorImplementation impl = new AdminstratorImplementation();
		Session session = factory.openSession();
		AdminUser userdetails = impl.edituser(session, userid);
		
		session.close();
		System.out.println("service  method exit");
		return userdetails;
	}
	
	public void updateuser(AdminUser adminuser){
		System.out.println("service method entered");
		AdminstratorImplementation impl = new AdminstratorImplementation();
		System.out.println("id=>"+adminuser.getId()+"\tusername=>"+adminuser.getUsername()+"\tpassword=>"+adminuser.getPassword()+"\tgroupname=>"+adminuser.getGroupname()+"\temail=>"+adminuser.getEmail()+"\tmobile=>"+adminuser.getMobile()+"\tisactive=>"+adminuser.getIsactive());
		Session session = factory.openSession();
		impl.updateuser(session, adminuser);
		
		session.close();
		System.out.println("service method exit");
	}
	

	public void addstate(State statebean){
		System.out.println("entered addstate service");
		Session session =factory.openSession();
		
		AdminstratorImplementation impl = new AdminstratorImplementation();
		impl.createstate(session,statebean);
		
		session.close();
		System.out.println("exit addstate service");
	}
	
	public State editstate(Integer stateid){
		System.out.println("Entered editstate service");
		Session session = factory.openSession();
		AdminstratorImplementation impl = new AdminstratorImplementation();
		State statebean = impl.editstate(session, stateid);
		
		session.close();
		System.out.println("Entered editstate service");
		return statebean;
	}
	public void updatestate(State bean){
		Session session = factory.openSession();
		
		AdminstratorImplementation impl = new AdminstratorImplementation();
		impl.updatestate(session, bean);
		
		
		session.close();
		
	}
	public ModelMap getCreateCity(ModelMap map){
		AdminstratorImplementation  impl = new AdminstratorImplementation();
		Session session = factory.openSession();   
		
		List states = impl.showstates(session,3);
		List<Object> statescollection = new ArrayList<Object>();
		for(Iterator<Object> itr = states.iterator();itr.hasNext();){
			Object[] currentstate = (Object[]) itr.next();
			HashMap<String, Object> statesmap = new HashMap<String, Object>();
			statesmap.put("id", currentstate[0]);
			statesmap.put("name", currentstate[1]);
			statesmap.put("isactive", currentstate[2]);
			statesmap.put("createddate",Commons.changedateformat(currentstate[3]));	
			statescollection.add(statesmap);
		}
		map.addAttribute("states", statescollection);
		City citybean = new City();
		map.addAttribute("city", citybean);
		
		session.close();
		return map;
	}
}

