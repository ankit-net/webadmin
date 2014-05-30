package education.bean;

import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="INSTITUTE_COURSE_CATEGORY")
public class Institute_Course_Category {
	
	private PK_Institute_Course_Category pk;
	private AdminUser created_by_id;
	private Date created_date;
	
	@Id
	@Embedded
	public PK_Institute_Course_Category getPk() {
		return pk;
	}
	public void setPk(PK_Institute_Course_Category pk) {
		this.pk = pk;
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
