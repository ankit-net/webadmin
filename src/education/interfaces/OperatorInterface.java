/**
 * 
 */
package education.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

/**
 * @author Ankit
 *
 */
public interface OperatorInterface {
	
	void addInstitute();
	
	void editInstitute();

	List showInstitutes(Session session,int pageno,int recordperpage);
	
	int countInstitutes(Session session);
	
	List showInstitutes_Filter(Session session,Map<String, Object> parameters);
		
	List showparentcategories(Session session);
	
	void showFilters();
	
	void searchInstitutes();
	
	void addCourse();
	
	void editCourse();
		
	List showCourses(Session session, int institute_id);
	
	int countCourses(Session session,int institute_id);
	
	void addDepartment();
	
	void editDepartment();
		
	List showDepartments(Session session,int institute_id);
	
	int countDeparments(Session session,int institute_id);
	
	void addMedia();
	
	void editMedia();
	
	List showMediaList(Session session,int institute_id);
	
	int countMedia(Session session,int institute_id);
	
	void addCampus();
	
	void editCampus();
	
	List showCampuses(Session session,int institute_id);
	
	int countCampuses(Session session,int institute_id);
}
