package tom.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import tom.springframework.sfgpetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
