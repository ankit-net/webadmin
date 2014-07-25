package education.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PK_Institute_TypeEdu implements Serializable{
	private Institute Institute_id;
	private TypeEdu Type_Edu_ID;

	@ManyToOne
	@JoinColumn(name="Institute_ID",referencedColumnName="id")
	public Institute getInstitute_id() {
		return Institute_id;
	}
	public void setInstitute_id(Institute institute_id) {
		Institute_id = institute_id;
	}
	@ManyToOne
	@JoinColumn(name="Type_Edu_ID",referencedColumnName="id")
	public TypeEdu getType_Edu_ID() {
		return Type_Edu_ID;
	}
	public void setType_Edu_ID(TypeEdu type_Edu_ID) {
		Type_Edu_ID = type_Edu_ID;
	}

}
