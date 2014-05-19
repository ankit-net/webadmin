package education.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Pk_Category_Language implements Serializable{
	private Category category_id;
	private Language language_id;
	
	@ManyToOne
	@JoinColumn(name="category_id",referencedColumnName="id")
	public Category getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Category category_id) {
		this.category_id = category_id;
	}
	@ManyToOne
	@JoinColumn(name="language_id",referencedColumnName="id")
	public Language getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(Language language_id) {
		this.language_id = language_id;
	}
	
	
}
