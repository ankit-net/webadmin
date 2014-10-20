package education.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {

	private int id;
	private String user_type_id;
	private String name;
	private int is_active;
	private String email;
	private String phone;
	private int created_by_id;
	private Date created_date;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	@ManyToOne
//	@JoinColumn(name="user_type_id",referencedColumnName="id")
	public String getUser_type_id() {
		return user_type_id;
	}
	public void setUser_type_id(String user_type_id) {
		this.user_type_id = user_type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getCreated_by_id() {
		return created_by_id;
	}
	public void setCreated_by_id(int created_by_id) {
		this.created_by_id = created_by_id;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "usertypeid->"+getUser_type_id()+"\nemailid=>"+getEmail()+"\nname=>"+getName()+"\nphone=>"+getPhone();
	}	
}
