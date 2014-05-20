package education.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="institute_media")
public class Institute_Media {

	private int id;
	private Institute institute_id;
	private Media_Type media_type_id;
	private String title;
	private String path;
	private String source;
	private int is_active;
	private AdminUser created_by_id;
	
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
	@ManyToOne
	@JoinColumn(name="media_type_id",referencedColumnName="id")
	public Media_Type getMedia_type_id() {
		return media_type_id;
	}
	public void setMedia_type_id(Media_Type media_type_id) {
		this.media_type_id = media_type_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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

}
