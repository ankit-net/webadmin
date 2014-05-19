/**
 * 
 */
package education.interfaces;

import java.util.List;

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
	
	List allparentcategories(Session session);
	
	void showFilters();
	
	void searchInstitutes();
	
	void addCourse();
	
	void editCourse();
		
	void showCourses();
	
	void addDepartment();
	
	void editDepartment();
		
	void showDepartments();
	
	void addMedia();
	
	void editMedia();
	
	void showMediaList();
	
	void addCampus();
	
	void editCampus();
	
	void showCampuses();
}
