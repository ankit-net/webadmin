package education.bean;

import java.util.Date;
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
	private String josh_rating;
	private Institute_Type institute_type;
	private String about;
	private City city_id;
	private State state_id;
	private int is_active;
	private AdminUser created_by_id;
	private Date created_date;
	private String keyword;
	private String source;
	private String address;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name="member_id", referencedColumnName="id")
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
	public String getJosh_rating() {
		return josh_rating;
	}
	public void setJosh_rating(String josh_rating) {
		this.josh_rating = josh_rating;
	}
	
	@ManyToOne
	@JoinColumn(name="institute_type",referencedColumnName="id")
	public Institute_Type getInstitute_type() {
		return institute_type;
	}
	public void setInstitute_type(Institute_Type institute_type) {
		this.institute_type = institute_type;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}