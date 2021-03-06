package tom.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
	
	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}
	
	public PetType() {}

	public PetType(Long id, String name) {
		super(id);
		this.name = name;
	}



	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
	
	
	
	
}
