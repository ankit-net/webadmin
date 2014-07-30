package education.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import education.bean.AdminUser;
import education.bean.City;
import education.bean.Institute;
import education.bean.Institute_Course;
import education.bean.Institute_Course_Category;
import education.bean.Institute_LevelofEdu;
import education.bean.Institute_Type;
import education.bean.Institute_TypeofEdu;
import education.bean.LevelEdu;
import education.bean.Member;
import education.bean.PK_InstituteLevelEdu;
import education.bean.PK_Institute_Course_Category;
import education.bean.PK_Institute_TypeEdu;
import education.bean.State;
import education.bean.TypeEdu;
import education.bean.UserType;
import education.interfaces.OperatorInterface;
import education.util.Commons;

public class OperatorImplementation implements OperatorInterface{

	@Override
	public void addInstitute(Session session,HttpServletRequest request) {
		// TODO Auto-generated method stub
		 System.out.println("entered addinstitute dao");
		 System.out.println("institutename=>"+request.getParameter("name")+"\temail=>"+request.getParameter("email"));
		 System.out.println("usertype=>"+request.getParameter("usertype")+"\trating=>"+request.getParameter("rating"));
		 System.out.println("phone=>"+request.getParameter("usertype")+"\tinstitutetype=>"+request.getParameter("institutetype"));
		 System.out.println("year=>"+request.getParameter("year")+"\tuserid=>"+request.getAttribute("userid").toString());
		 System.out.println("typedu values=>"+request.getParameterValues("typeedu"));
		 System.out.println("leveledu values=>"+request.getParameterValues("leveledu"));
		 /*
		  * Preparing Member Bean
		  */
		 UserType usertypebean = new UserType();
		 usertypebean.setId(Integer.parseInt(request.getParameter("usertype")));
		 
		 Member member = new Member();
		 member.setName(request.getParameter("name"));
		 member.setEmail(request.getParameter("email"));
		 member.setUser_type_id(usertypebean);
		 member.setPhone(request.getParameter("phone"));
		 member.setCreated_date(new Date());
		 
		 /*
		  * Preparing Institute Bean
		  */
		 Institute_Type insttypebean = new Institute_Type();
		 insttypebean.setId(Integer.parseInt(request.getParameter("institutetype")));
		 
		 
		 State st = new State();
		 st.setId(Integer.parseInt(request.getParameter("allstates")));
		 
		 City ct = new City();
		 ct.setId(Integer.parseInt(request.getParameter("allcitieslist")));
		 
		 AdminUser userbean = new AdminUser();
		 userbean.setId(Integer.parseInt(request.getAttribute("userid").toString()));
		 
		 
		 Institute inst = new Institute();
		 inst.setJosh_rating(request.getParameter("rating"));
		 inst.setInstype(insttypebean);
		 inst.setYearoffrom(Integer.parseInt(request.getParameter("year")));
		 inst.setAbout(request.getParameter("about"));
		 inst.setState_id(st);
		 inst.setCity_id(ct);
		 inst.setKeyword(request.getParameter("keyword"));
		 inst.setSource(request.getParameter("source"));
		 inst.setAddress(request.getParameter("address"));
		 inst.setCreated_by_id(userbean);
		 inst.setCreated_date(new Date());
		 
		 /*
		  * Selecting max id from member table
		  */
		 String hql_maxid_member  = "select max(id) from Member";
		 Query query = session.createQuery(hql_maxid_member);
		 int memberid  = Integer.parseInt(query.uniqueResult().toString()) + 1;
		 Transaction tx  = null;
		 
		 System.out.println("next memberid is =>"+memberid);
		 try {
			 
			 /*
			  * Exceuting Insert Query for Member Table
			  */
			 member.setId(memberid);
			 tx = session.beginTransaction();
			 session.save(member);
	
			 
	
			 inst.setMember_id(member);
			 
			 /*
			  * Executing Insert Query for Institute Table
			  */
	
			 session.save(inst);
			 
			 /*
			  * Preparing for InstituteTypeofEdu
			  * Executing Insert Query for Institute TypeEdu
			  */
			 
			 String hql_maxinstid = "select max(id) from Institute";
			 query = session.createQuery(hql_maxinstid);
			 int instituteid = Integer.parseInt(query.uniqueResult().toString());
			 System.out.println("last inserted instituteid=>"+instituteid);
			 //inst.setId(instituteid);
			 
			 Institute newinstbean =new Institute();
			 newinstbean.setId(instituteid);
			 /*
			  *Preparaing Institute_TypeofEdu Bean 
			  */
			 
			 for(String current:request.getParameterValues("typeedu")){
				 System.out.println("current type edu id=>"+current);
				 
				 
				 TypeEdu typeedubean = new TypeEdu();
				 typeedubean.setId(Integer.parseInt(current));
				 
				 PK_Institute_TypeEdu pkbean = new PK_Institute_TypeEdu();
				 pkbean.setInstituteid(instituteid);
				 pkbean.setTypeduid(Integer.parseInt(current));
				 
				 Institute_TypeofEdu typeofedubean = new Institute_TypeofEdu();
				 typeofedubean.setPkid(pkbean);
				 
				 session.save(typeofedubean);
				 
			 }
			 /*
			  * Preparing Institute_LevelofEdu Bean
			  */
			 for(String current:request.getParameterValues("leveledu")){
				 System.out.println("current level edu id="+current);

				 PK_InstituteLevelEdu pklevelbean = new PK_InstituteLevelEdu();
				 pklevelbean.setInstituteid(instituteid);
				 
				 LevelEdu leveledubean = new LevelEdu();
				 leveledubean.setId(Integer.parseInt(current));
				 
				 pklevelbean.setLeveleduid(Integer.parseInt(current));
				 
				 Institute_LevelofEdu levelofedubean = new Institute_LevelofEdu();
				 levelofedubean.setPkinstleveledu(pklevelbean);
				 
				 session.save(levelofedubean);
			 }
			 
			 
			 
			 tx.commit();
		 }
		 catch (Exception ex ){
			 ex.printStackTrace();
			 tx.rollback();
		 }
		 System.out.println("exit addinstitute dao");
	}

	@Override
	public HashMap<String, Object> editInstitute(Session session,int instituteid) {
		// TODO Auto-generated method stub
		System.out.println("entered editinstitute dao");
		String query_institutedetails = "select inst.id,inst.member_id.email,inst.member_id.name,inst.josh_rating,inst.member_id.phone,inst.instype.id,inst.yearoffrom,inst.about,inst.city_id.id,inst.state_id.id,inst.member_id.user_type_id.id,inst.member_id.id,inst.keyword,inst.source,inst.address from Institute inst where inst.id=:instid";

		String query_institutetypedu = "select ite.pkid.typeduid  from Institute_TypeofEdu ite where ite.pkid.instituteid=:instid";
		HashMap<String, Object> instbean = new HashMap<String,Object>();
		Query query = session.createQuery(query_institutedetails);
		query.setParameter("instid",instituteid);
		
		List institutedetail = query.list();
		System.out.println("total records found=>"+institutedetail.size());
		for(Iterator itr = institutedetail.iterator();itr.hasNext();){
			Object[] institute = (Object[]) itr.next();
			System.out.println( "institute id"+institute[0]+"emailid=>"+institute[1]+"\n institutename=>"+institute[2]+"\njoshrating=>"+
			institute[3]+"\nphone=>"+institute[4]+"\ninstitutetype=>"+institute[5]+"\nyearoffrom=>"+institute[6]+"\n about=>"+institute[7]+
			"\n cityid"+institute[8]+"\n stateid"+institute[9]+"\nusertypeid=>"+institute[10]+"\nmemberid=>"+institute[11]
			+"\n keyword=>"+institute[12]+"\nsource=>"+institute[13]+"\naddress=>"+institute[14]);		
			
			instbean.put("instid", institute[0]);
			instbean.put("email", institute[1]);
			instbean.put("institutename", institute[2]);
			instbean.put("joshrating", institute[3]);
			instbean.put("phone", institute[4]);
			instbean.put("institutetype", institute[5]);
			instbean.put("yearoffrom", institute[6]);
			instbean.put("about", institute[7]);
			instbean.put("cityid", institute[8]);
			instbean.put("stateid", institute[9]);
			instbean.put("usertype", institute[10]);
			instbean.put("memberid", institute[11]);
			instbean.put("keyword", institute[12]);
			instbean.put("source", institute[13]);
			instbean.put("address", institute[14]);
		}
		
		
		Query query2 = session.createQuery(query_institutetypedu);
		query2.setParameter("instid", instituteid);
		List institutetypedu = query2.list();
		System.out.println("total institutetypeofedu found=>"+institutetypedu.size());
		
		
		List<Integer> typeedulist = new ArrayList<>();
		
		for(Iterator itr = institutetypedu.iterator();itr.hasNext();){
			Integer typeofedubean = (Integer) itr.next();
			
			typeedulist.add(typeofedubean);
		}
		System.out.println("typedu=>"+typeedulist.toString());
		instbean.put("typeedulist", typeedulist);
																											
		String query_leveledu= "select ile.pkinstleveledu.leveleduid  from Institute_LevelofEdu ile where ile.pkinstleveledu.instituteid=:instid";
		Query query3 = session.createQuery(query_leveledu);
		query3.setParameter("instid", instituteid);
		
		List instituteleveledu = query3.list();
		
		System.out.println("total instituteleveledu found=>"+instituteleveledu.size());
		
		List<Integer> leveledulist = new ArrayList<Integer>();
		
		for(Iterator itr = instituteleveledu.iterator();itr.hasNext();){
			Integer levelofedubean = (Integer) itr.next();
			
			leveledulist.add(levelofedubean);
		}
		System.out.println("leveledulist=>"+leveledulist.toString());
		instbean.put("leveledulist", leveledulist);
		System.out.println("exit editinstitute dao");
		return instbean;
	}

	@Override
	public void updateInstitute(Session session,HttpServletRequest request) {
		
		System.out.println("entered updateinstitute dao");
		 System.out.println("institutename=>"+request.getParameter("name")+"\temail=>"+request.getParameter("email"));
		 System.out.println("usertype=>"+request.getParameter("usertype")+"\trating=>"+request.getParameter("rating"));
		 System.out.println("phone=>"+request.getParameter("usertype")+"\tinstitutetype=>"+request.getParameter("institutetype"));
		 System.out.println("year=>"+request.getParameter("year")+"\tuserid=>"+request.getAttribute("userid").toString());
		 System.out.println("typedu values=>"+request.getParameterValues("typeedu"));
		 System.out.println("leveledu values=>"+request.getParameterValues("leveledu"));
		 System.err.println("member id=>"+request.getParameter("memid"));
		 System.out.println("instituteid=>"+request.getParameter("id"));
		/*Preparing several classes*/
		Transaction tx = session.beginTransaction();
		
		/*
		  * Preparing Member Bean
		  */
		 UserType usertypebean = new UserType();
		 usertypebean.setId(Integer.parseInt(request.getParameter("usertype")));
		 
		 Member member = new Member();
		 member.setId(Integer.parseInt(request.getParameter("memid")));
		 member.setName(request.getParameter("name"));
		 member.setEmail(request.getParameter("email"));
		 member.setUser_type_id(usertypebean);
		 member.setPhone(request.getParameter("phone"));
		 member.setCreated_date(new Date());
		 session.update(member);
		
		 
		 /*
		  * Preparing Institute Bean
		  */
		 
		 Institute_Type insttypebean = new Institute_Type();
		 insttypebean.setId(Integer.parseInt(request.getParameter("institutetype")));
		 
		 
		 State st = new State();
		 st.setId(Integer.parseInt(request.getParameter("allstates")));
		 
		 City ct = new City();
		 ct.setId(Integer.parseInt(request.getParameter("allcitieslist")));
		 
		 AdminUser userbean = new AdminUser();
		 userbean.setId(Integer.parseInt(request.getAttribute("userid").toString()));
		 
		 
		 Institute inst = new Institute();
		 inst.setMember_id(member);
		 inst.setId(Integer.parseInt(request.getParameter("id")));
		 inst.setJosh_rating(request.getParameter("rating"));
		 inst.setInstype(insttypebean);
		 inst.setYearoffrom(Integer.parseInt(request.getParameter("year")));
		 inst.setAbout(request.getParameter("about"));
		 inst.setState_id(st);
		 inst.setCity_id(ct);
		 inst.setKeyword(request.getParameter("keyword"));
		 inst.setSource(request.getParameter("source"));
		 inst.setAddress(request.getParameter("address"));
		 inst.setCreated_by_id(userbean);
		 inst.setCreated_date(new Date());
		 session.update(inst);
		 
		 try {
			 /*
			  * Deleting existing records for InstituteTypeofEdu for This InstituteID
			  */
			 String del_typeofedu = "delete  Institute_TypeofEdu ite where ite.pkid.instituteid=:instid";
			 Query qdel = session.createQuery(del_typeofedu);
			 qdel.setParameter("instid", inst.getId());
			 
			 qdel.executeUpdate();
			 
			 String del_levelofedu = "delete  Institute_LevelofEdu ile where ile.pkinstleveledu.instituteid=:instid";
			 Query qdel_level = session.createQuery(del_levelofedu);
			 qdel_level.setParameter("instid", inst.getId());
			 qdel_level.executeUpdate();
			 
			 
			 /*
			  *Preparaing Institute_TypeofEdu Bean 
			  */
			 
			 for(String current:request.getParameterValues("typeedu")){
				 System.out.println("current type edu id=>"+current);
				 
						 
				 
				 TypeEdu typeedubean = new TypeEdu();
				 typeedubean.setId(Integer.parseInt(current));
				 
				 PK_Institute_TypeEdu pkbean = new PK_Institute_TypeEdu();
				 pkbean.setInstituteid(Integer.parseInt(request.getParameter("id")));
				 pkbean.setTypeduid(Integer.parseInt(current));
				 
				 Institute_TypeofEdu typeofedubean = new Institute_TypeofEdu();
				 typeofedubean.setPkid(pkbean);
				 
				 session.saveOrUpdate(typeofedubean);
				 
			 }
			 /*
			  * Preparing Institute_LevelofEdu Bean
			  */
			 for(String current:request.getParameterValues("leveledu")){
				 System.out.println("current level edu id="+current);
				 
				 
				 PK_InstituteLevelEdu pklevelbean = new PK_InstituteLevelEdu();
				 pklevelbean.setInstituteid(Integer.parseInt(request.getParameter("id")));
				 
				 LevelEdu leveledubean = new LevelEdu();
				 leveledubean.setId(Integer.parseInt(current));
				 
				 pklevelbean.setLeveleduid(Integer.parseInt(current));
				 
				 Institute_LevelofEdu levelofedubean = new Institute_LevelofEdu();
				 levelofedubean.setPkinstleveledu(pklevelbean);
				 
				 session.saveOrUpdate(levelofedubean);
				 
			 }
			 tx.commit();
		}
		catch(Exception ex){
			tx.rollback();
			ex.printStackTrace();
		}
	
		System.out.println("exit updateinstitute dao");
	}
	

	@Override
	public List showInstitutes(Session session,int pageno, int recordperPage) {
		// TODO Auto-generated method stub
		System.out.println("entered showinstitutes dao");
		List institutecollection = new ArrayList();
		String hql_showinstitutes= "select inst.id as institute_id,inst.member_id.name as institute_name,inst.instype.name as institute_type,inst.city_id.name as city,inst.state_id.name as state,inst.created_by_id.username as createdby,inst.created_date from Institute inst";
		Query query = session.createQuery(hql_showinstitutes);
		query.setFirstResult((pageno - 1 ) * recordperPage);
		query.setMaxResults(recordperPage);
		List instituteslist = query.list();
		
		System.out.println("institutes found =>"+instituteslist.size());
		for(Iterator itr = instituteslist.iterator();itr.hasNext(); ) {
			Object[] instituteobject = (Object[]) itr.next();
			HashMap<String, Object> institutemap = new HashMap<String, Object>();
			institutemap.put("institute_id", instituteobject[0]);
			institutemap.put("institute_name", instituteobject[1]);
			institutemap.put("institute_type", instituteobject[2]);
			institutemap.put("city", instituteobject[3]);
			institutemap.put("state", instituteobject[4]);
			institutemap.put("createdby", instituteobject[5]);
			institutemap.put("created_date",Commons.changedateformat(instituteobject[6]));
			
			institutecollection.add(institutemap);
		}
		
		System.out.println("exit showinstitutes dao");
		return institutecollection;
	}
	
	@Override
	public int countInstitutes(Session session) {
		// TODO Auto-generated method stub
		System.out.println("entered count institutes dao");
		String hql_countinstitutes = "select count(*) from Institute";
		Query query = session.createQuery(hql_countinstitutes);
		List institutelist = query.list();		
		int count = Integer.parseInt(institutelist.get(0).toString());
		System.out.println("count of institutes =>"+count);
		
		System.out.println("exit count institutes dao");
		return count;
	}
	
	@Override
	public List showInstitutes_Filter(Session session,Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		System.out.println("Entered filter institutes dao");
		System.out.println("maincat=>"+parameters.get("maincategory")+"\tchildcat=>"+parameters.get("childcategories")+"\tstates=>"+parameters.get("stateid")+"\tcities=>"+parameters.get("cities")+"\tcp=>"+parameters.get("currentpage"));
		Integer maincategory = (Integer) parameters.get("maincategory");
		Integer[] childcategories = (Integer[]) parameters.get("childcategories");
		Integer stateid = (Integer) parameters.get("stateid");
		Integer[] cities = (Integer[]) parameters.get("cities");
		Integer rowsperpage = 10;
		Integer currentpage = (Integer) parameters.get("currentpage");
		
		
		StringBuilder institutes_filter = new StringBuilder("SELECT DISTINCT inst.id as institute_id, inst.member_id.name as institute_name, inst.instype.name as institute_type, inst.city_id.name as city, inst.state_id.name as state , inst.created_by_id.username as createdby,inst.created_date FROM Institute inst WHERE ");
		
		StringBuilder count_filter = new StringBuilder("SELECT COUNT(DISTINCT inst.id) FROM Institute inst WHERE ");
	//	Query query = session.createQuery("SELECT inst.id, inst.member_id.name, inst.institute_type, inst.city_id.name, inst.state_id.name , inst.country_id.name , inst.created_by_id.username , inst.verified_by_id.username FROM Institute inst WHERE inst.state_id.id = :states AND inst.city_id.id IN (:cities) ORDER BY inst.member_id.name ");
	//	query.setParameter("states", statesid);
	//	query.setParameterList("cities", citiesid); 
		int startrow =	((currentpage - 1)	* rowsperpage) ; 
		int endrow = (startrow + rowsperpage);
		int[] allcategories = null;
		if(childcategories[0] != -1){
			//child category is also selected with main category
			allcategories = new int[childcategories.length + 1]; 
		}
		else{
			//only main category is being selected
			allcategories  = new int[childcategories.length];
		}
		
		
		
		System.out.println("startrow=>"+startrow+"\tendrow=>"+endrow);
	
		System.out.println("query executed before checking=>"+institutes_filter.toString());
		
		if(maincategory == -1 && childcategories[0] == -1 && stateid == -1 && cities[0] == -1){
			//this means no filter had been selected
			System.out.println("no filter had been selected");
			institutes_filter.replace(institutes_filter.length() -7, institutes_filter.length(), "");
			count_filter.replace(count_filter.length() - 7, count_filter.length(), "");
		}
		else {
			
			/*if(childcategory[0] != -1){
				//some child category had been selected
				querybuilder.replace(querybuilder.length() -7, querybuilder.length(), "");
				querybuilder.append(" ,Institute_Course_Category icc where icc.pk.institute_id.id = inst.id AND icc.pk.category_id in (:child) ");
			}*/
			if(maincategory != -1){
				//some main category had been selected
				institutes_filter.replace(institutes_filter.length() - 7, institutes_filter.length(), "");
				
				count_filter.replace(count_filter.length() - 7, count_filter.length(), "");
				
				if(childcategories[0] != -1){
					//some child category also been selected
					for(int i=0;i< childcategories.length;i++){
						allcategories[i] = childcategories[i];
					}
					allcategories[childcategories.length] = maincategory; 
					
					institutes_filter.append(" ,Institute_Course_Category icc where icc.pk.institute_id.id = inst.id AND icc.pk.category_id IN (:categories) ");
					
					count_filter.append(" ,Institute_Course_Category icc where icc.pk.institute_id.id = inst.id AND icc.pk.category_id IN (:categories) ");
				}
				else {
					//only main category is being selected
					allcategories[0] = maincategory;
							
					//querybuilder.replace(querybuilder.length() -7, querybuilder.length(), "");
					institutes_filter.append(" ,Institute_Course_Category icc where icc.pk.institute_id.id = inst.id AND icc.pk.category_id IN (:categories) ");
					
					count_filter.append(" ,Institute_Course_Category icc where icc.pk.institute_id.id = inst.id AND icc.pk.category_id IN (:categories) ");
				}
			}
			/*if(cities[0] != -1){
				//some city had been selected
				institutes_filter.append(" inst.city_id.id IN (:cities) ");
				
			}
			else {
				//no city had been selected
				
			}*/
			if(stateid != -1){
				//some state had been selected
				if(cities[0] != -1){
					institutes_filter.append(" AND inst.state_id.id = :states AND inst.city_id.id IN (:cities)");
					
					count_filter.append(" AND inst.state_id.id = :states AND inst.city_id.id IN (:cities)");
				}
				else if(allcategories[0] != 0) {
					institutes_filter.append(" AND inst.state_id.id = :states");
					
					count_filter.append(" AND inst.state_id.id = :states");
				}
				else {
					institutes_filter.append(" inst.state_id.id = :states");
					
					count_filter.append(" inst.state_id.id = :states");
				}
				
			}
			else {
				
			}
		}
		
		institutes_filter.append(" ORDER BY inst.member_id.name ");
		count_filter.append(" ORDER BY inst.member_id.name ");
		
		System.out.println("query exceute for filters=>"+institutes_filter.toString());
		
		System.out.println();
		Query query = session.createQuery(institutes_filter.toString());
		
		Query query_count = session.createQuery(count_filter.toString());
		if(cities[0] != -1){
			query.setParameterList("cities", cities);
			
			query_count.setParameterList("cities", cities);
		}
		if(maincategory != -1){
			Set<Integer> categories_set = new HashSet<Integer>();
			for(Integer current:allcategories){
				categories_set.add(current);
			}
			
			query.setParameterList("categories",categories_set);
			
			query_count.setParameterList("categories",categories_set);
		}
		if(stateid != -1){
			query.setParameter("states", stateid);
			
			query_count.setParameter("states", stateid);
		}
		
		query.setFirstResult(startrow);
		query.setMaxResults(rowsperpage);
		List<Object> institutes_hql = query.list();
		ArrayList<Object> institutecollection = new ArrayList();
		for(Iterator itr= institutes_hql.iterator();itr.hasNext();){
			Object[] instituteResult = (Object[]) itr.next();
			HashMap<String, Object> currentinstitute = new HashMap<String, Object>();
			currentinstitute.put("id",(Integer)	instituteResult[0]);
			currentinstitute.put("name", (String) instituteResult[1]);
			currentinstitute.put("type", (String) instituteResult[2]);
			currentinstitute.put("city", (String) instituteResult[3]);
			currentinstitute.put("state", (String) instituteResult[4]);
			//currentinstitute.put("country", (String) instituteResult[5]);
			currentinstitute.put("createdby", (String) instituteResult[5]);
			
			institutecollection.add(currentinstitute);
		}
		
		//query.setParameterList(arg0, arg1)
		System.out.println("institutes_hql->"+institutes_hql.size());
		
		long totalcount = (long) query_count.list().get(0);
		
		System.out.println("total count for institutes filter=>"+totalcount);
		
		System.out.println("Exit filter institutes dao");
		institutecollection.add(0, totalcount);
		return institutecollection;
	}
	
	
	
	@Override
	public List showparentcategories(Session session) {
		System.err.println("Entered getParentCategories");
		List allcategories = new ArrayList();
		
		String hql_maincategories = "select ca.id,cl.name as categoryname from Category ca, Category_Language cl where  ca.id=cl.pkid.category_id.id and ca.parent_id.id is null";
		Query query = session.createQuery(hql_maincategories);
		List allcategories_raw = query.list();
		for(Iterator itr=allcategories_raw.iterator();itr.hasNext();){
			HashMap<String, Object> categoriesmap = new HashMap<String, Object>();
			Object[] categorybean  = (Object[]) itr.next();
			categoriesmap.put("id", categorybean[0]);
			categoriesmap.put("categoryname", categorybean[1]);
			
			allcategories.add(categoriesmap);
		}
		
		System.err.println("Exit getParentCategories");
		
		return allcategories;
	}
		
	public List showchildcategories(Session session,int categoryid){
		List childcategories = new ArrayList();
		String hql_childcategories = "select ca.id,cl.name as categoryname from Category ca, Category_Language cl where ca.id=cl.pkid.category_id.id and ca.parent_id.id=:parentid";
	
		Query query = session.createQuery(hql_childcategories);
		query.setInteger("parentid", categoryid);
		List childcategories_raw = query.list();
		for(Iterator itr = childcategories_raw.iterator();itr.hasNext();) {
			Object[] categorybean = (Object[]) itr.next();
			HashMap<String, Object> categorymap = new HashMap<String, Object>();
			
			categorymap.put("id", categorybean[0]);
			categorymap.put("categoryname", categorybean[1]);
			
			childcategories.add(categorymap);
		}
		
		
		return childcategories;
	}
	
	

	@Override
	public void addCourse(Session session,HashMap<String, Object> parameters) {
		// TODO Auto-generated method stub
		System.out.println("Entered AddCourses DAO");
		Transaction tx = session.beginTransaction();
		AdminUser userbean = new AdminUser();
		userbean.setId(Integer.parseInt(parameters.get("createdbyid").toString()));
		Institute inst = new Institute();
		inst.setId((Integer.parseInt(parameters.get("instituteid").toString())));
		TypeEdu tpedu = new TypeEdu();
		tpedu.setId(Integer.parseInt(parameters.get("coursetypedu").toString()));
		LevelEdu ledu = new LevelEdu();
		ledu.setId(Integer.parseInt(parameters.get("courseleveledu").toString()));
		
		try {
			Institute_Course icbean = new Institute_Course();
			icbean.setInstitute_id(inst);
			icbean.setName((String)parameters.get("coursename").toString());
			icbean.setKeyword((String) parameters.get("coursekeyword").toString());
			icbean.setIs_active(1);
			icbean.setCreated_by_id(userbean);
			icbean.setCreated_date(new Date());
			icbean.setType_edu_id(tpedu);
			icbean.setLevel_edu_id(ledu);
			session.save(icbean);
		
			
		/*
		 * Logic for select latest institute course id from Table(Institute_Course).
		 */
		String hql_courseid="select max(id) from Institute_Course";
		Query query = session.createQuery(hql_courseid);
		int courseid = Integer.parseInt(query.uniqueResult().toString());
		System.out.println("latest courseid inserted=>"+courseid);
		
		
		/*
		 * Logic for inserting maincategory into tables (Institute_Course,Institute_Course_Category)
		 */
		//tx = session.beginTransaction();
		Institute_Course coursebean_main = new Institute_Course();
		coursebean_main.setId(courseid);
		PK_Institute_Course_Category pkmaincategoryinsertion = new PK_Institute_Course_Category();
		pkmaincategoryinsertion.setCategory_id(Integer.parseInt(parameters.get("coursemaincat").toString()));
		pkmaincategoryinsertion.setCourse_id(coursebean_main);
		pkmaincategoryinsertion.setInstitute_id(inst);
		
		Institute_Course_Category iccbean = new Institute_Course_Category();
		iccbean.setPk(pkmaincategoryinsertion);
		iccbean.setCreated_by_id(userbean);
		iccbean.setCreated_date(new Date());
		iccbean.setIs_active(1);
		
		session.save(iccbean);
		
		
		
		
		/*
		* Logic for Inserting Child categories into Tables
		*/ 
		String[] childcategories = (String[]) parameters.get("coursechildcat");
		for(String current:childcategories){
			System.out.println("current childcategory=>"+current);
			Institute_Course_Category iccbeanmultiple= new Institute_Course_Category();
			PK_Institute_Course_Category pkicc = new PK_Institute_Course_Category();
			pkicc.setCategory_id(Integer.parseInt(current));
			pkicc.setInstitute_id(inst);
			pkicc.setCourse_id(coursebean_main);
			
			iccbeanmultiple.setPk(pkicc);
			iccbeanmultiple.setCreated_by_id(userbean);
			iccbeanmultiple.setCreated_date(new Date());
			iccbeanmultiple.setIs_active(1);
			session.save(iccbeanmultiple);
		}

			tx.commit();
		}
		catch (Exception ex) {
			System.out.println("exception occured=>"+ex.getMessage());
			tx.rollback();
			
			System.out.println("session is closed");
		}
		
		System.out.println("Exit AddCourses DAO");
		
	}

	@Override
	public void editCourse() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List showCourses(Session session,int institute_id) {
		// TODO Auto-generated method stub
		System.out.println("entered showcourses dao");
		System.out.println("institute_id=>"+institute_id);
		List courseslist = new ArrayList();
		String courses_query = "select ic.id,ic.name,ic.is_active from Institute_Course ic where ic.institute_id.id=:instituteid";
		
		Query query = session.createQuery(courses_query);
		query.setInteger("instituteid", institute_id);
		
		List courses = query.list();
		System.err.println("total courses found=>"+courses.size()+"\t for institute id=>"+institute_id);
		
		for(Iterator itr=courses.iterator();itr.hasNext();){
			Object[] coursebean = (Object[]) itr.next();
			HashMap<String, Object> coursemap= new HashMap<String, Object>();
			coursemap.put("courseid", coursebean[0]);
			coursemap.put("coursename", coursebean[1]);
			coursemap.put("isactive", coursebean[2]);
			courseslist.add(coursemap);
		}
		
		System.out.println("exit showcourses dao");
		return courseslist;
	}
	@Override
	public int countCourses(Session session, int institute_id) {
		// TODO Auto-generated method stub
		System.out.println("entered countcourses dao");
		String count_courses = "select count(*) from Institute_Course ic where ic.institute_id.id =:instituteid";

		Query query = session.createQuery(count_courses);
		query.setInteger("instituteid", institute_id);
		
		List courselist = query.list();
		int count = Integer.parseInt(courselist.get(0).toString());
		
		System.out.println("total courses found=>"+count);
		System.out.println("exit countcourses dao");
		return count;
	}

	@Override
	public void addDepartment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editDepartment() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List showDepartments(Session session,int institute_id) {
		// TODO Auto-generated method stub
		System.out.println("entered showdepartments dao");
		System.out.println("institute id=>"+institute_id);
		List deptlist = new ArrayList();
		String query_deptlist = "select ind.id,ind.name,ind.description,ind.is_active from Institute_Department ind where ind.institute_id.id=:instituteid";
		Query query = session.createQuery(query_deptlist);
		query.setInteger("instituteid", institute_id);
		List deptlist_raw = query.list();
		for(Iterator itr = deptlist_raw.iterator();itr.hasNext();){ 
			Object[] deptbean = (Object[]) itr.next();
			HashMap<String, Object> deptmap = new HashMap<String, Object>();
			deptmap.put("deptid", deptbean[0]);
			deptmap.put("deptname", deptbean[1]);
			deptmap.put("description", deptbean[2]);
					
			deptlist.add(deptmap);
		}
		
		System.out.println("exit showdepartments dao");
		
		return deptlist;
	}
	@Override
	public int countDeparments(Session session, int institute_id) {
		// TODO Auto-generated method stub
		String count_dept = "select count(*) from Institute_Department ic where ic.institute_id.id =:instituteid";

		Query query = session.createQuery(count_dept);
		query.setInteger("instituteid", institute_id);
		
		List courselist = query.list();
		int count = Integer.parseInt(courselist.get(0).toString());
		
		System.out.println("count for department=>"+count);
		return count;
	}

	@Override
	public void addMedia() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editMedia() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List showMediaList(Session session,int institute_id) {
		// TODO Auto-generated method stub
		System.out.println("Entered showmedia dao");
		System.out.println("institute_id=>"+institute_id);
		List medialist = new ArrayList();
		String query_medialist = "select im.id,im.media_type_id.name as mediatype,im.title,im.path  from Institute_Media im	where im.institute_id.id=:instituteid";
		Query query = session.createQuery(query_medialist);
		query.setInteger("instituteid", institute_id);
		
		List medialist_raw = query.list();
		
		for(Iterator itr = medialist_raw.iterator();itr.hasNext();){
			Object[] mediabean = (Object[]) itr.next();
			HashMap<String, Object> mediamap = new HashMap<String, Object>();
			mediamap.put("mediaid", mediabean[0]);
			mediamap.put("mediatype", mediabean[1]);
			mediamap.put("title", mediabean[2]);
			mediamap.put("path", mediabean[3]);
			
			medialist.add(mediamap);
		}
		System.out.println("Exit showmedia dao");
		
		return medialist;
	}
	@Override
	public int countMedia(Session session, int institute_id) {
		// TODO Auto-generated method stub
		String count_media = "select count(*) from Institute_Media ic where ic.institute_id.id =:instituteid";

		Query query = session.createQuery(count_media);
		query.setInteger("instituteid", institute_id);
		
		List courselist = query.list();
		int count = Integer.parseInt(courselist.get(0).toString());
		
		System.out.println("count for department=>"+count);

		return count;
	}
	
	@Override
	public void addCampus() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void editCampus() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List showCampuses(Session session,int institute_id) {
		// TODO Auto-generated method stub
		System.out.println("entered showcampuses dao");
		List campuslist = new ArrayList();
		String query_campuslist = "select ic.id,ic.name,ic.accomodation,ic.is_active from Institute_Campus ic where ic.institute_id.id=:instituteid";
		Query query = session.createQuery(query_campuslist);
		query.setInteger("instituteid", institute_id);
		
		List campuslist_raw = query.list();
		for(Iterator itr=campuslist_raw.iterator();itr.hasNext(); ){
			Object[] campusbean = (Object[]) itr.next();
			HashMap<String, Object> campusmap = new HashMap<String, Object>();
			campusmap.put("id", campusbean[0]);
			campusmap.put("campusname", campusbean[1]);
			campusmap.put("accomodation", campusbean[2]);
			campusmap.put("isactive", campusbean[3]);
			
			campuslist.add(campusmap);
		}
		System.out.println("exit showcampuses dao");
		
		return campuslist;
	}
	@Override
	public int countCampuses(Session session, int institute_id) {
		// TODO Auto-generated method stub
		String count_campuses = "select count(*) from Institute_Campus ic where ic.institute_id.id =:instituteid";

		Query query = session.createQuery(count_campuses);
		query.setInteger("instituteid", institute_id);
		
		List courselist = query.list();
		int count = Integer.parseInt(courselist.get(0).toString());
		
		System.out.println("count for department=>"+count);

		return count;
	}
	
}
