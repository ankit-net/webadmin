package education.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import education.bean.AdminUser;
import education.bean.Country;
import education.bean.State;
import education.interfaces.AdministratorInterface;
import education.util.Commons;



public class AdminstratorImplementation implements AdministratorInterface{

	@Autowired
	private SessionFactory factory;
	
	@Override
	public void createstate(Session session,State bean) {
		// TODO Auto-generated method stub
		System.out.println("entered createstate dao");
		Transaction transaction = session.beginTransaction();
		
		Country country = new Country();
		country.setId(3);
		bean.setCountry_id(country);

		bean.setCreateddate(new Date());
		
		System.out.println("bean parameters=>id=>"+bean.getId()+"\tname=>"+bean.getName()+"\tcountryid=>"+bean.getCountry_id().getId()
				+"\tsisactive=>"+bean.getIsactive()+"\tcreateddate=>"+bean.getCreateddate());

		session.save(bean);
		
		transaction.commit();
		System.out.println("exit createstate dao");
	}

	@Override
	public State editstate(Session session,Integer stateid) {
		// TODO Auto-generated method stub
		System.out.println("Entered edit state dao");
		System.out.println("stateid=>"+stateid);
		
		String editstatehql = "select id,name,isactive from State where id=:state";

		Query query = session.createQuery(editstatehql);
		query.setParameter("state", stateid);
		
		List statelist = query.list();
		Object[] details =  (Object[]) statelist.get(0);
		
		State statebean = new State();
		statebean.setId((Integer) details[0]);
		statebean.setName((String) details[1]);
		statebean.setIsactive((Integer) details[2]);
		
		
		System.out.println("Exit edit state dao");
		return statebean;
	}

	@Override 
	public void updatestate(Session session,State statebean) {
		// TODO Auto-generated method stub
		System.out.println("entered updatestate dao");
		//String hqlupdate = "update AdminUser set username=:username,password=:password,email=:email,mobile=:mobile,isactive=:isactive where id=:id";	
		String updatestatehql = "update State set name=:statename,isactive=:isactive where id=:id";
		
		Transaction tx= session.beginTransaction();
		Query query = session.createQuery(updatestatehql);
		query.setInteger("id", statebean.getId());
		query.setString("statename", statebean.getName());
		query.setInteger("isactive", statebean.getIsactive());
		
		int updatestatus =	query.executeUpdate();
		System.out.println("updatestatus=>"+updatestatus);
		tx.commit();
		System.out.println("exit updatestate dao");
	}
	
	@Override
	public void createcity(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editcity() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updatecity() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void createuser(Session session,HashMap<String, Object> parameters) {
		// TODO Auto-generated method stub
		System.out.println("Entered create user dao ");
		Transaction trans = session.beginTransaction();
		AdminUser newuser = new AdminUser();
		newuser.setUsername(parameters.get("username").toString());
		newuser.setPassword(parameters.get("password").toString());
		newuser.setGroupname(parameters.get("groupname").toString());
		newuser.setEmail(parameters.get("email").toString());
		newuser.setMobile(parameters.get("mobile").toString());
		newuser.setIsactive(Integer.parseInt(parameters.get("isactive").toString()));
		
		
		session.save(newuser);
		
		trans.commit();
		
		
		System.out.println("Exit create user dao");
	}

	
	@Override
	public AdminUser edituser(Session session,Integer userid) {
		// TODO Auto-generated method stub
		System.out.println("entered edit user dao");
		String hqledit = "select id,username,password,groupname,email,mobile,isactive from AdminUser where id=:userid";
		Query query = session.createQuery(hqledit);
		query.setInteger("userid", userid);
		List userdetail = query.list();
		//	System.out.println("id=>"+userdetail.get(0).getId()+"\tusername=>"+userdetail.get(0).getUsername());
		Object[] details =  (Object[]) userdetail.get(0);
		
		
		//System.out.println("id=>"+details[0]+"\tname=>"+details[1]);
		AdminUser user = new AdminUser();
		user.setId((Integer)	details[0]);
		user.setUsername((String)	details[1]);
		user.setPassword((String)	details[2]);
		user.setGroupname((String) details[3] );
		user.setEmail((String) details[4] );
		user.setMobile((String) details[5] );
		user.setIsactive((int) details[6]);
		
		System.out.println("id=>"+user.getId()+"\t"+user.getUsername()+"\t"+user.getPassword());
		//System.out.println("id=>"+details.getId()+"\tusername=>"+details.getUsername());
		
		System.out.println("exit edit user dao");
		return user;
	}
	
	@Override
	public void updateuser(Session session, AdminUser bean) {
		// TODO Auto-generated method stub
		System.out.println("entered updateuser dao");
		String hqlupdate = "update AdminUser set username=:username,password=:password,email=:email,mobile=:mobile,isactive=:isactive where id=:id";
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hqlupdate);
		
		query.setParameter("username", bean.getUsername());
		query.setParameter("password", bean.getPassword());
		query.setParameter("email", bean.getEmail());
		query.setParameter("mobile", bean.getMobile());
		query.setParameter("isactive", bean.getIsactive());
		query.setParameter("id", bean.getId());
		
		int flag =	query.executeUpdate();
		
		tx.commit();
		
		System.out.println("update status=>"+flag);
		
		System.out.println("exit updateuser dao"); 
		
	}

	@Override
	public List showusers(Session session,int pageno,int recordperPage) {
		// TODO Auto-generated method stub
		System.out.println("entered show users");
		System.out.println("pageno=>"+pageno+"\trownum=>"+recordperPage);
		
		String usersquery = "select id,username,password,groupname,email,mobile,isactive from AdminUser order by username";
		
		Query query = session.createQuery(usersquery);
		query.setFirstResult((pageno - 1) * recordperPage);
		query.setMaxResults(recordperPage);
		
		List userslist = query.list();
		
		System.out.println("exit show users");
		return userslist;
	}

	@Override
	public int countusers(Session session) {
		// TODO Auto-generated method stub
		System.out.println("entered count users");
		String userscountquery = "select count(*) from AdminUser";
		Query query2 = session.createQuery(userscountquery);
		
		int countusers = Integer.parseInt(query2.uniqueResult().toString());
		System.err.println("total no of users=>"+countusers);

		System.out.println("exit count users");
		return countusers;
	}
	


	@Override
	public List showstates(Session session,int countryid) {
		// TODO Auto-generated method stub
		System.out.println("Entered show states");
		System.out.println("countryid=>"+countryid);
		String statesquery = "select st.id,st.name,st.isactive,st.createddate from State st where st.country_id.id = ?";
		Query query = session.createQuery(statesquery);
		query.setParameter(0, countryid);
		
		List states = query.list();
		
		List statescollection = new ArrayList();
		
		for(Iterator itr = states.iterator();itr.hasNext();){
			HashMap<String, Object> statesmap = new HashMap<>();
			Object[] stateobj = (Object[]) itr.next();
			statesmap.put("id", stateobj[0]);
			statesmap.put("name", stateobj[1]);
			statesmap.put("isactive", stateobj[2]);
			statesmap.put("createddate", stateobj[3]);
			
			statescollection.add(statesmap);
		}

		System.out.println("Exit show states");
		return statescollection;
	}
	
	@Override
	public int countstates(Session session) {
		// TODO Auto-generated method stub
		System.out.println("Entered countstates dao");
		String countstatesquery = "select count(*) from State where country_id.id=3";
		Query query = session.createQuery(countstatesquery);
		
		int count =	Integer.parseInt(query.uniqueResult().toString());
		
		System.out.println("total no of states found=>"+count);
				
		
		System.out.println("Exit countstates dao");
		return count;
	}

	@Override
	public List showcities(Session session,int stateid,int countryid,int pageno,int recordperPage) {
		// TODO Auto-generated method stub
		System.out.println("Entered show cities dao");
		System.out.println("stateid=>"+stateid+"\tcountry=>"+countryid);
		List cities = null;
		List citiescollection = new ArrayList();
		String querycities = "select ci.id,ci.name,ci.isactive,ci.createddate from City ci where ci.country_id.id=? and ci.state_id.id=? order by ci.name";
		Query query = session.createQuery(querycities);
		query.setInteger(0, countryid);
		query.setInteger(1, stateid);
		if(pageno != -1){
			query.setFirstResult((pageno - 1) * recordperPage);
			query.setMaxResults(recordperPage);
		}
		else {
			//this will load all cities available
		}
		
		cities  = query.list();
		
		for(Iterator itr = cities.iterator();itr.hasNext();){
			HashMap<String, Object> citiesmap = new HashMap<String,Object>();
			Object[] cityobj = (Object[]) itr.next();
			citiesmap.put("id", cityobj[0]);
			citiesmap.put("name", cityobj[1]);
			citiesmap.put("isactive", cityobj[2]);
			citiesmap.put("createddate", Commons.changedateformat(cityobj[3]));
			
			citiescollection.add(citiesmap);
					
		}
		System.out.println("Exit show cities dao");
		return citiescollection;
	}

	@Override
	public int countcities(Session session,int stateid,int countryid) {
		// TODO Auto-generated method stub
		System.out.println("entered count cities dao");
		System.out.println("stateid=>"+stateid+"\tcountryid=>"+countryid);
		String countcityquery = "select count(*) from City where country_id.id=3 and state_id.id=?";
		Query query = session.createQuery(countcityquery);
		query.setInteger(0, stateid);
		int count =	Integer.parseInt(query.uniqueResult().toString());
		
		System.out.println("exit count cities dao");
		return count;
	}
	
	@Override
	public List showInstitutetypes(Session session, int pageno,int recordperPage) {
		// TODO Auto-generated method stub
		System.out.println("entered showinsitutetypes dao");
		String query_show = "select id,name,isactive from Institute_Type";
		Query query = session.createQuery(query_show);
		List institutetypescollection = new ArrayList();
		if (pageno != -1) {
			query.setFirstResult((pageno - 1) * recordperPage);
			query.setMaxResults(recordperPage);
		}
		
		List institutetypes = query.list();
		
		for(Iterator itr = institutetypes.iterator();itr.hasNext();){
			Object[] insttype = (Object[]) itr.next();
			HashMap<String, Object> insttypemap = new HashMap<String,Object>();
			
			insttypemap.put("id", insttype[0]);
			insttypemap.put("name", insttype[1]);
			insttypemap.put("is_active", insttype[2]);
			institutetypescollection.add(insttypemap);
		}
		
		System.out.println("exit showinstitutetypes dao");
		return institutetypescollection;
	}
	
	@Override
	public int countInstitutetypes(Session session) {
		// TODO Auto-generated method stub
		System.out.println("entered countinstitutestypes dao");
		String countquery = "select count(*) from Institute_Type";
		Query query = session.createQuery(countquery);
		
		int count = Integer.parseInt(query.uniqueResult().toString());

		System.out.println("exit countinstitutetypes dao");
		return count;
	}

	@Override
	public List showusertypes(Session session, int pageno, int recordperPage) {
		// TODO Auto-generated method stub
		System.out.println("entered showusertypes");
		String query_usertype = "select id,name,is_active,created_by_id.username from UserType";
		Query query = session.createQuery(query_usertype);
		List usertypescollection = new ArrayList();
		if(pageno != -1){
			query.setFirstResult((pageno - 1) * recordperPage);
			query.setMaxResults(recordperPage);			
		}
		List usertypeslist = query.list();
		for(Iterator itr = usertypeslist.iterator();itr.hasNext();){
			HashMap<String, Object> usertypesmap = new HashMap<String,Object>();
			Object[] usertypeobj = (Object[]) itr.next();
			usertypesmap.put("id", usertypeobj[0]);
			usertypesmap.put("name", usertypeobj[1]);
			usertypesmap.put("is_active", usertypeobj[2]);
			usertypesmap.put("username", usertypeobj[3]);
			usertypescollection.add(usertypesmap);	
		}
		System.out.println("exit showusertypes");
		return usertypescollection;
	}
	
	@Override
	public int countusertypes(Session session) {
		// TODO Auto-generated method stub
		System.out.println("entered countusertypes dao");
		String countquery = "select count(*) from UserType";
		
		Query query = session.createQuery(countquery);
		int count = Integer.parseInt(query.uniqueResult().toString());
		System.out.println("exit countusertypes dao");
		return count;
	}

	@Override
	public List showlevelofeducation(Session session, int pageno,int recordperPage) {
		// TODO Auto-generated method stub
		System.out.println("entered showlevelofeducation dao");
		List leveleducollection = new ArrayList();
		String query_leveleducation = "select id,name,is_active,created_by_id.username,created_date from LevelEdu";
		Query query = session.createQuery(query_leveleducation);
		if(pageno != -1){
			query.setFirstResult((pageno - 1) * recordperPage);
			query.setMaxResults(recordperPage);			
		}
		List leveledulist  =  query.list();
		for(Iterator itr = leveledulist.iterator();itr.hasNext();) {
			Object[] levelbean = (Object[]) itr.next();
			HashMap<String, Object> leveledumap = new HashMap<String,Object>();
			leveledumap.put("id", levelbean[0]);
			leveledumap.put("name", levelbean[1]);
			leveledumap.put("isactive", levelbean[2]);
			leveledumap.put("username", levelbean[3]);
			leveledumap.put("createdate", Commons.changedateformat(levelbean[4]));
			
			leveleducollection.add(leveledumap);	
		}		
		System.out.println("exit showlevelofeducation dao");
		return leveleducollection;
	}
	
	@Override
	public int countlevelofeducation(Session session) {
		// TODO Auto-generated method stub
		System.out.println("entered countlevelofeducation");
		Query query = session.createQuery("select count(*) from LevelEdu");
		
		int result = Integer.parseInt(query.uniqueResult().toString());
		System.out.println("total levelofeducation found=>"+result);
		
		System.out.println("exit countlevelofeducation");
		return result;
	}
	
	@Override
	public List showtypeofeducation(Session session, int pageno,int recordperPage) {
		// TODO Auto-generated method stub
		System.out.println("entered typeof education");
		List typeeducollection = new ArrayList();
		String query_typedu= "select id,name,is_active,created_by_id.username,created_date from TypeEdu";
		Query query = session.createQuery(query_typedu);
		if(pageno != -1){
			query.setFirstResult((pageno - 1) * recordperPage);
			query.setMaxResults(recordperPage);			
		}
		List typeedulist = query.list();
		for(Iterator itr = typeedulist.iterator();itr.hasNext();){
			Object[] typedubean = (Object[]) itr.next();
			HashMap<String, Object> typeedumap = new HashMap<String,Object>();
			typeedumap.put("id", typedubean[0]);
			typeedumap.put("name", typedubean[1]);
			typeedumap.put("isactive", typedubean[2]);
			typeedumap.put("username", typedubean[3]);
			typeedumap.put("createddate", Commons.changedateformat(typedubean[4]));
			
			typeeducollection.add(typeedumap);
		}
		
		System.out.println("exit typeof education");
		return typeeducollection;
	}
	
	@Override
	public int counttypeofeducation(Session session) {
		// TODO Auto-generated method stub
		System.out.println("entered count typeofeudcation");
		String query_count = "select count(*) from TypeEdu";
		Query query = session.createQuery(query_count);
				
		int result =	Integer.parseInt(query.uniqueResult().toString());
		System.out.println("total records found=>"+result);
		System.out.println("exit count typeofeducation");
		return result;
	}
	
}
