package education.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="INSTITUTE_COURSE")
public class Institute_Course {

	private int id;
	private Institute institute_id;
	private String name;
	private String keyword;
	private int is_active;
	private LevelEdu level_edu_id;
	private TypeEdu type_edu_id; 
	private AdminUser created_by_id;
	private Date created_date;
	
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public void setLevel_edu_id(LevelEdu level_edu_id) {
		this.level_edu_id = level_edu_id;
	}
	@ManyToOne
	@JoinColumn(name="level_edu_id",referencedColumnName="id")
	public LevelEdu getLevel_edu_id() {
		return level_edu_id;
	}
	public void setType_edu_id(TypeEdu type_edu_id) {
		this.type_edu_id = type_edu_id;
	}
	@ManyToOne
	@JoinColumn(name="type_edu_id",referencedColumnName="id")
	public TypeEdu getType_edu_id() {
		return type_edu_id;
	}
	public void setCreated_by_id(AdminUser created_by_id) {
		this.created_by_id = created_by_id;
	}
	@ManyToOne
	@JoinColumn(name="created_by_id",referencedColumnName="id")
	public AdminUser getCreated_by_id() {
		return created_by_id;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getCreated_date() {
		return created_date;
	}
}
