package education.interfaces;

import java.util.List;
import org.hibernate.Session;
import education.bean.State;

public interface PropertiesInterface {
	
	void createstate(Session session,State bean);
	
	State editstate(Session session,Integer stateid);
	
	void updatestate(Session session,State bean);
		
	List showstates(Session session,int countryid);
	
	int countstates(Session session);
	
	void createcity(Session session);
	
	void editcity();
	
	void updatecity();
		
	List showcities(Session session,int stateid,int countryid,int pageno,int recordperPage);
	
	int countcities(Session session,int stateid,int countryid);
	
}
