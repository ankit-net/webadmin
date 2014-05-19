package education.bean;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {

	private int id;
	private String name;
	private Country country_id;
	private State state_id;
	private int isactive;
	private Date createddate;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne
	@JoinColumn(name="country_id",referencedColumnName="id")
	public Country getCountry_id() {
		return country_id;
	}
	public void setCountry_id(Country country_id) {
		this.country_id = country_id;
	}
	
	@ManyToOne
	@JoinColumn(name="state_id",referencedColumnName="id")
	public State getState_id() {
		return state_id;
	}
	public void setState_id(State state_id) {
		this.state_id = state_id;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	
	
}
