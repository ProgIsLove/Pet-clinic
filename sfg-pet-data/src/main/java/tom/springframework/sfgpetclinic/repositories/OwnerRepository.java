package tom.springframework.sfgpetclinic.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import tom.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
	
	public Owner findByLastName(String lastName);
	
	@Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
	@Transactional(readOnly = true)
	public Collection<Owner> findAllByLastName(@Param("lastName")String lastName);
}
