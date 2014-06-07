package education.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import education.bean.AdminUser;

@Service
public class LoginService {

	@Autowired
	private SessionFactory sessionFactory;
	
	public String validateUser(AdminUser userbean,HttpServletRequest request){
		System.out.println("Entered validateuser service");
		
		String hqlquery = "select groupname,id from AdminUser where username=:user and password=:pass";
		HttpSession httpSession = null;
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hqlquery);
		query.setParameter("user", userbean.getUsername());
		query.setParameter("pass", userbean.getPassword());
	
		@SuppressWarnings("rawtypes")
		List  myuser = 	query.list();
		String returnurl  =  null;
		
		if(myuser.size() > 0){
			httpSession = request.getSession();//this line will create a session object.
			Object[] myuserbean  = (Object[]) myuser.get(0);
			String groupname = (String) myuserbean[0];
			Integer userid = (Integer) myuserbean[1];
			System.out.println("groupname found=>"+groupname);
			httpSession.setAttribute("userid", userid);
			if(groupname.equalsIgnoreCase("manager")){
				//This user had rights to publishmedia,showactivitylog,downloadlisting apart from operator rights
				httpSession.setAttribute("usertype", "manager");
				
				returnurl = "userinterface";
			}
			else if(groupname.equalsIgnoreCase("administrator")){
				httpSession.setAttribute("usertype", "administrator");
				
				
				System.out.println("forwarding to admininterface jsp");
				returnurl = "admininterface";
			}
			else if(groupname.equalsIgnoreCase("operator")){
				//this user had rights to do operations for institutes,courses,departments,campuses
				httpSession.setAttribute("usertype", "operator");
				returnurl = "userinterface";
			}
			System.out.println("login is successful");
			
		}
		else {
			
			System.out.println("login is failed. There is no user account for=>"+userbean.getUsername());
			
		}
		System.out.println("Exit validateuser service");
		return returnurl;
	}
}
