package education.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PK_InstituteLevelEdu implements Serializable{
	
	/*private Institute institute_id;
	private LevelEdu level_edu_id;
	
	@ManyToOne
	@JoinColumn(name="Institute_ID",referencedColumnName="id")
	public Institute getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(Institute institute_id) {
		this.institute_id = institute_id;
	}
	@ManyToOne
	@JoinColumn(name="Level_EDU_ID",referencedColumnName="id")
	public LevelEdu getLevel_edu_id() {
		return level_edu_id;
	}
	public void setLevel_edu_id(LevelEdu level_edu_id) {
		this.level_edu_id = level_edu_id;
	}*/
	private int instituteid;
	private int leveleduid;
	
	@Column(name="Institute_ID")
	public int getInstituteid() {
		return instituteid;
	}
	public void setInstituteid(int instituteid) {
		this.instituteid = instituteid;
	}
	@Column(name="Level_EDU_ID")
	public int getLeveleduid() {
		return leveleduid;
	}
	public void setLeveleduid(int leveleduid) {
		this.leveleduid = leveleduid;
	}
}