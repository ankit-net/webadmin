package education.interfaces;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import education.bean.AdminUser;

public interface AdministratorInterface extends PropertiesInterface {

	void createuser(Session session,HashMap<String, Object> parameters);
	
	AdminUser edituser(Session session,Integer userid);
	
	void updateuser(Session session, AdminUser bean);
	
	List showusers(Session session,int pageno,int recordperPage);

	int countusers(Session session);
	
	List showusertypes(Session session,int pageno,int recordperPage);
	
	int countusertypes(Session session);
	
	List showInstitutetypes(Session session,int pageno,int recordperPage);
	
	int countInstitutetypes(Session session);
	
	List showlevelofeducation(Session session,int pageno,int recordperPage);
	
	int countlevelofeducation(Session session);
	
	List showtypeofeducation(Session session,int pageno,int recordperPage);
	
	int counttypeofeducation(Session session);
	
}
