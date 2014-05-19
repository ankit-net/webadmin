package education.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="institute")
public class Institute {

	private int id;
	private Member member_id;
	private int yearoffrom;
	private Institute_Type instype; 
	private City city_id;
	private State state_id;
	private Country country_id;
	private int is_active;
	private AdminUser created_by_id;
	private Date created_date;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name="member_id",referencedColumnName="id")
	public Member getMember_id() {
		return member_id;
	}
	public void setMember_id(Member member_id) {
		this.member_id = member_id;
	}
	public int getYearoffrom() {
		return yearoffrom;
	}
	public void setYearoffrom(int yearoffrom) {
		this.yearoffrom = yearoffrom;
	}
	
	@ManyToOne
	@JoinColumn(name="institute_type",referencedColumnName="id")
	public Institute_Type getInstype() {
		return instype;
	}
	public void setInstype(Institute_Type instype) {
		this.instype = instype;
	}
	
	@ManyToOne
	@JoinColumn(name="city_id",referencedColumnName="id")
	public City getCity_id() {
		return city_id;
	}
	public void setCity_id(City city_id) {
		this.city_id = city_id;
	}
	@ManyToOne
	@JoinColumn(name="state_id",referencedColumnName="id")
	public State getState_id() {
		return state_id;
	}
	public void setState_id(State state_id) {
		this.state_id = state_id;
	}
	@ManyToOne
	@JoinColumn(name="country_id",referencedColumnName="id")
	public Country getCountry_id() {
		return country_id;
	}
	public void setCountry_id(Country country_id) {
		this.country_id = country_id;
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
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
}