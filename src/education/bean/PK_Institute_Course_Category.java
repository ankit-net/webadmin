package education.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PK_Institute_Course_Category implements Serializable{
	private Institute institute_id;
	private Institute_Course course_id;
	private int category_id;
	
	@ManyToOne
	@JoinColumn(name="institute_id",referencedColumnName="id")
	public Institute getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(Institute institute_id) {
		this.institute_id = institute_id;
	}
	@ManyToOne
	@JoinColumn(name="course_id",referencedColumnName="id")
	public Institute_Course getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Institute_Course course_id) {
		this.course_id = course_id;
	}
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
}
