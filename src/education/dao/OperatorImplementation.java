package education.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import education.interfaces.OperatorInterface;
import education.util.Commons;

public class OperatorImplementation implements OperatorInterface{

	@Override
	public void addInstitute() {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void editInstitute() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public List showInstitutes(Session session,int pageno, int recordperPage) {
		// TODO Auto-generated method stub
		System.out.println("entered showinstitutes dao");
		List institutecollection = new ArrayList();
		String hql_showinstitutes= "select inst.id as institute_id,inst.member_id.name as institute_name,inst.instype.name as institute_type,inst.city_id.name as city,inst.state_id.name as state,inst.country_id.name as country,inst.created_by_id.username as createdby,inst.created_date from Institute inst";
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
			institutemap.put("country", instituteobject[5]);
			institutemap.put("createdby", instituteobject[6]);
			institutemap.put("created_date",Commons.changedateformat(instituteobject[7]));
			
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
		
		
		StringBuilder institutes_filter = new StringBuilder("SELECT DISTINCT inst.id as institute_id, inst.member_id.name as institute_name, inst.instype.name as institute_type, inst.city_id.name as city, inst.state_id.name as state , inst.country_id.name as country, inst.created_by_id.username as createdby,inst.created_date FROM Institute inst WHERE ");
		
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
			currentinstitute.put("country", (String) instituteResult[5]);
			currentinstitute.put("createdby", (String) instituteResult[6]);
			
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
	public void showFilters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchInstitutes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCourse() {
		// TODO Auto-generated method stub
		
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
