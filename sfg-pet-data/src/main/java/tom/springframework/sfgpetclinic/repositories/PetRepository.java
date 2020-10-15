package tom.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import tom.springframework.sfgpetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
