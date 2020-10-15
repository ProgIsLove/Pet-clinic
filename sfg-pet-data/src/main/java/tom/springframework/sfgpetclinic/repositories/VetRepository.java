package tom.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import tom.springframework.sfgpetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {

}
