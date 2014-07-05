package education.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LEVEL_EDU")
public class LevelEdu {
	private int id;
	private String name;
	private Language language_id;
	private int is_active;
	private AdminUser created_by_id;
	private Date created_date;
	
	
	public void setId(int id) {
		this.id = id;
	}
	@Id
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setLanguage_id(Language language_id) {
		this.language_id = language_id;
	}
	@ManyToOne
	@JoinColumn(name="language_id",referencedColumnName="id")
	public Language getLanguage_id() {
		return language_id;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setCreated_by_id(AdminUser created_by_id) {
		this.created_by_id = created_by_id;
	}

	@ManyToOne
	@JoinColumn(name="CREATED_BY_ID",referencedColumnName="id")
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
