package education.bean;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Institute_TypeofEdu")
public class Institute_TypeofEdu {

	private PK_Institute_TypeEdu pkid;
	
	
	public void setPkid(PK_Institute_TypeEdu pkid) {
		this.pkid = pkid;
	}
	@Id
	@Embedded
	public PK_Institute_TypeEdu getPkid() {
		return pkid;
	}
	
	
}

