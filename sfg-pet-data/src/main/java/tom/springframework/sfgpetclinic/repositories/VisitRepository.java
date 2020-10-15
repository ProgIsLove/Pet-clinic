package tom.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import tom.springframework.sfgpetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
