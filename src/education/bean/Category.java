package education.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {

	private int id; 
	private String identifier;
	private Category_Type cat_type_id;
	private Category parent_id; 
	private int is_active;
	private AdminUser created_by_id;
	private Date created_date;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	@ManyToOne
	@JoinColumn(name="cat_type_id",referencedColumnName="id")
	public Category_Type getCat_type_id() {
		return cat_type_id;
	}
	public void setCat_type_id(Category_Type cat_type_id) {
		this.cat_type_id = cat_type_id;
	}
	
	@ManyToOne
	@JoinColumn(name="parent_id",referencedColumnName="id",nullable=true)
	public Category getParent_id() {
		return parent_id;
	}
	public void setParent_id(Category parent_id) {
		this.parent_id = parent_id;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
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
