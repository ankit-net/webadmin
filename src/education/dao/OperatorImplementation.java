package education.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
	public List allparentcategories(Session session) {
		System.err.println("Entered getParentCategories");
		List allcategories = new ArrayList();
		System.err.println("Exit getParentCategories");
		
		return allcategories;
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
	public void showCourses() {
		// TODO Auto-generated method stub
		
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
	public void showDepartments() {
		// TODO Auto-generated method stub
		
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
	public void showMediaList() {
		// TODO Auto-generated method stub
		
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
	public void showCampuses() {
		// TODO Auto-generated method stub
		
	}
	
	
}
