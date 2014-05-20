package education.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="institute_campus")
public class Institute_Campus {
	 
	private int id;
	private Institute institute_id;
	private String name;
	private String description;
	private int accomodation;
	private int is_default;
	private String address;
	private int is_active;
	private AdminUser created_by_id;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="institute_id",referencedColumnName="id")
	public Institute getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(Institute institute_id) {
		this.institute_id = institute_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAccomodation() {
		return accomodation;
	}
	public void setAccomodation(int accomodation) {
		this.accomodation = accomodation;
	}
	public int getIs_default() {
		return is_default;
	}
	public void setIs_default(int is_default) {
		this.is_default = is_default;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	@ManyToOne
	@JoinColumn(name="created_by_id",referencedColumnName="id")
	public AdminUser getCreated_by_id() {
		return created_by_id;
	}
	public void setCreated_by_id(AdminUser created_by_id) {
		this.created_by_id = created_by_id;
	}
	
}
