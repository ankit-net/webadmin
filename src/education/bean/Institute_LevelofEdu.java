package education.bean;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Institute_Levelofedu")
public class Institute_LevelofEdu {
	
	private PK_InstituteLevelEdu pkinstleveledu;
	
	
	@Id
	@Embedded
	public PK_InstituteLevelEdu getPkinstleveledu() {
		return pkinstleveledu;
	}
	public void setPkinstleveledu(PK_InstituteLevelEdu pkinstleveledu) {
		this.pkinstleveledu = pkinstleveledu;
	}
}

