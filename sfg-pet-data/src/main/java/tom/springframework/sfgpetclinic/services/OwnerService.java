package tom.springframework.sfgpetclinic.services;

import java.util.Collection;

import tom.springframework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
	
	Owner findByLastName(String lastName);
	
	Collection<Owner> findAllByLastName(String lastName);

}
